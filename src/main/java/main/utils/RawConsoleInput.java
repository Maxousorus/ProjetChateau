package main.utils;

import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.List;
import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * The type Raw console input.
 */
public class RawConsoleInput {
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    private static final int invalidKey = 0xFFFE;
    private static final String invalidKeyStr = String.valueOf((char) invalidKey);

    private static boolean initDone;
    private static boolean stdinIsConsole;
    private static boolean consoleModeAltered;

    /**
     * Read int.
     *
     * @param wait the wait
     * @return the int
     * @throws IOException the io exception
     */
    public static int read(boolean wait) throws IOException {
        int key;
        if (isWindows) {
            key = readWindows(wait);
        } else {
            key = readUnix(wait);
        }
        // Exit if the user presses Ctrl+C.
        if (key == 3) {
            System.exit(0);
        }
        return key;
    }

    /**
     * Reset console mode.
     *
     * @throws IOException the io exception
     */
    public static void resetConsoleMode() throws IOException {
        if (isWindows) {
            resetConsoleModeWindows();
        } else {
            resetConsoleModeUnix();
        }
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                shutdownHook();
            }
        });
    }

    private static void shutdownHook() {
        try {
            resetConsoleMode();
        } catch (Exception e) {
        }
    }

    // --- Windows
    // ------------------------------------------------------------------

    // The Windows version uses _kbhit() and _getwch() from msvcrt.dll.

    private static Msvcrt msvcrt;
    private static Kernel32 kernel32;
    private static Pointer consoleHandle;
    private static int originalConsoleMode;

    private static int readWindows(boolean wait) throws IOException {
        initWindows();
        if (!stdinIsConsole) {
            int c = msvcrt.getwchar();
            if (c == 0xFFFF) {
                c = -1;
            }
            return c;
        }
        consoleModeAltered = true;
        setConsoleMode(consoleHandle, originalConsoleMode & ~Kernel32Defs.ENABLE_PROCESSED_INPUT);
        // ENABLE_PROCESSED_INPUT must remain off to prevent Ctrl-C from beeing
        // processed by the system
        // while the program is not within getwch().
        if (!wait && msvcrt._kbhit() == 0) {
            return -2;
        } // no key available
        return getwch();
    }

    private static int getwch() {
        int c = msvcrt._getwch();
        if (c == 0 || c == 0xE0) { // Function key or arrow key
            c = msvcrt._getwch();
            if (c >= 0 && c <= 0x18FF) {
                return 0xE000 + c;
            } // construct key code in private Unicode range
            return invalidKey;
        }
        if (c < 0 || c > 0xFFFF) {
            return invalidKey;
        }
        return c;
    } // normal key

    private static synchronized void initWindows() throws IOException {
        if (initDone) {
            return;
        }
        msvcrt = Native.load("msvcrt", Msvcrt.class);
        kernel32 = Native.load("kernel32", Kernel32.class);
        try {
            consoleHandle = getStdInputHandle();
            originalConsoleMode = getConsoleMode(consoleHandle);
            stdinIsConsole = true;
        } catch (IOException e) {
            stdinIsConsole = false;
        }
        if (stdinIsConsole) {
            registerShutdownHook();
        }
        initDone = true;
    }

    private static Pointer getStdInputHandle() throws IOException {
        Pointer handle = kernel32.GetStdHandle(Kernel32Defs.STD_INPUT_HANDLE);
        if (Pointer.nativeValue(handle) == 0 || Pointer.nativeValue(handle) == Kernel32Defs.INVALID_HANDLE_VALUE) {
            throw new IOException("GetStdHandle(STD_INPUT_HANDLE) failed.");
        }
        return handle;
    }

    private static int getConsoleMode(Pointer handle) throws IOException {
        IntByReference mode = new IntByReference();
        int rc = kernel32.GetConsoleMode(handle, mode);
        if (rc == 0) {
            throw new IOException("GetConsoleMode() failed.");
        }
        return mode.getValue();
    }

    private static void setConsoleMode(Pointer handle, int mode) throws IOException {
        int rc = kernel32.SetConsoleMode(handle, mode);
        if (rc == 0) {
            throw new IOException("SetConsoleMode() failed.");
        }
    }

    private static void resetConsoleModeWindows() throws IOException {
        if (!initDone || !stdinIsConsole || !consoleModeAltered) {
            return;
        }
        setConsoleMode(consoleHandle, originalConsoleMode);
        consoleModeAltered = false;
    }

    private static interface Msvcrt extends Library {
        /**
         * Kbhit int.
         *
         * @return the int
         */
        int _kbhit();

        /**
         * Getwch int.
         *
         * @return the int
         */
        int _getwch();

        /**
         * Gets .
         *
         * @return the
         */
        int getwchar();
    }

    private static class Kernel32Defs {
        /**
         * The Std input handle.
         */
        static final int STD_INPUT_HANDLE = -10;
        /**
         * The Invalid handle value.
         */
        static final long INVALID_HANDLE_VALUE = (Native.POINTER_SIZE == 8) ? -1 : 0xFFFFFFFFL;
        /**
         * The Enable processed input.
         */
        static final int ENABLE_PROCESSED_INPUT = 0x0001;
        /**
         * The Enable line input.
         */
        static final int ENABLE_LINE_INPUT = 0x0002;
        /**
         * The Enable echo input.
         */
        static final int ENABLE_ECHO_INPUT = 0x0004;
        /**
         * The Enable window input.
         */
        static final int ENABLE_WINDOW_INPUT = 0x0008;
    }

    private static interface Kernel32 extends Library {
        /**
         * Get console mode int.
         *
         * @param hConsoleHandle the h console handle
         * @param lpMode         the lp mode
         * @return the int
         */
        int GetConsoleMode(Pointer hConsoleHandle, IntByReference lpMode);

        /**
         * Set console mode int.
         *
         * @param hConsoleHandle the h console handle
         * @param dwMode         the dw mode
         * @return the int
         */
        int SetConsoleMode(Pointer hConsoleHandle, int dwMode);

        /**
         * Get std handle pointer.
         *
         * @param nStdHandle the n std handle
         * @return the pointer
         */
        Pointer GetStdHandle(int nStdHandle);
    }

    // --- Unix
    // ---------------------------------------------------------------------

    // The Unix version uses tcsetattr() to switch the console to non-canonical
    // mode,
    // System.in.available() to check whether data is available and System.in.read()
    // to read bytes from the console.
    // A CharsetDecoder is used to convert bytes to characters.

    private static final int stdinFd = 0;
    private static Libc libc;
    private static CharsetDecoder charsetDecoder;
    private static Termios originalTermios;
    private static Termios rawTermios;
    private static Termios intermediateTermios;

    private static int readUnix(boolean wait) throws IOException {
        initUnix();
        if (!stdinIsConsole) { // STDIN is not a console
            return readSingleCharFromByteStream(System.in);
        }
        consoleModeAltered = true;
        setTerminalAttrs(stdinFd, rawTermios); // switch off canonical mode, echo and signals
        try {
            if (!wait && System.in.available() == 0) {
                return -2;
            } // no input available
            return readSingleCharFromByteStream(System.in);
        } finally {
            setTerminalAttrs(stdinFd, intermediateTermios);
        }
    } // reset some console attributes

    private static Termios getTerminalAttrs(int fd) throws IOException {
        Termios termios = new Termios();
        try {
            int rc = libc.tcgetattr(fd, termios);
            if (rc != 0) {
                throw new RuntimeException("tcgetattr() failed.");
            }
        } catch (LastErrorException e) {
            throw new IOException("tcgetattr() failed.", e);
        }
        return termios;
    }

    private static void setTerminalAttrs(int fd, Termios termios) throws IOException {
        try {
            int rc = libc.tcsetattr(fd, LibcDefs.TCSANOW, termios);
            if (rc != 0) {
                throw new RuntimeException("tcsetattr() failed.");
            }
        } catch (LastErrorException e) {
            throw new IOException("tcsetattr() failed.", e);
        }
    }

    private static int readSingleCharFromByteStream(InputStream inputStream) throws IOException {
        byte[] inBuf = new byte[4];
        int inLen = 0;
        while (true) {
            if (inLen >= inBuf.length) { // input buffer overflow
                return invalidKey;
            }
            int b = inputStream.read(); // read next byte
            if (b == -1) { // EOF
                return -1;
            }
            inBuf[inLen++] = (byte) b;
            int c = decodeCharFromBytes(inBuf, inLen);
            if (c != -1) {
                return c;
            }
        }
    }

    // (This method is synchronized because the charsetDecoder must only be used by
    // a single thread at once.)
    private static synchronized int decodeCharFromBytes(byte[] inBytes, int inLen) {
        charsetDecoder.reset();
        charsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
        charsetDecoder.replaceWith(invalidKeyStr);
        ByteBuffer in = ByteBuffer.wrap(inBytes, 0, inLen);
        CharBuffer out = CharBuffer.allocate(1);
        charsetDecoder.decode(in, out, false);
        if (out.position() == 0) {
            return -1;
        }
        return out.get(0);
    }

    private static synchronized void initUnix() throws IOException {
        if (initDone) {
            return;
        }
        libc = Native.load("c", Libc.class);
        stdinIsConsole = libc.isatty(stdinFd) == 1;
        charsetDecoder = Charset.defaultCharset().newDecoder();
        if (stdinIsConsole) {
            originalTermios = getTerminalAttrs(stdinFd);
            rawTermios = new Termios(originalTermios);
            rawTermios.c_lflag &= ~(LibcDefs.ICANON | LibcDefs.ECHO | LibcDefs.ECHONL | LibcDefs.ISIG);
            intermediateTermios = new Termios(rawTermios);
            intermediateTermios.c_lflag |= LibcDefs.ICANON;
            // Canonical mode can be switched off between the read() calls, but echo must
            // remain disabled.
            registerShutdownHook();
        }
        initDone = true;
    }

    private static void resetConsoleModeUnix() throws IOException {
        if (!initDone || !stdinIsConsole || !consoleModeAltered) {
            return;
        }
        setTerminalAttrs(stdinFd, originalTermios);
        consoleModeAltered = false;
    }

    /**
     * The type Termios.
     */
    protected static class Termios extends Structure { // termios.h
        /**
         * The C iflag.
         */
        public int c_iflag;
        /**
         * The C oflag.
         */
        public int c_oflag;
        /**
         * The C cflag.
         */
        public int c_cflag;
        /**
         * The C lflag.
         */
        public int c_lflag;
        /**
         * The C line.
         */
        public byte c_line;
        /**
         * The Filler.
         */
        public byte[] filler = new byte[64]; // actual length is platform dependent

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("c_iflag", "c_oflag", "c_cflag", "c_lflag", "c_line", "filler");
        }

        /**
         * Instantiates a new Termios.
         */
        Termios() {
        }

        /**
         * Instantiates a new Termios.
         *
         * @param t the t
         */
        Termios(Termios t) {
            c_iflag = t.c_iflag;
            c_oflag = t.c_oflag;
            c_cflag = t.c_cflag;
            c_lflag = t.c_lflag;
            c_line = t.c_line;
            filler = t.filler.clone();
        }
    }

    private static class LibcDefs {
        /**
         * The constant ISIG.
         */
// termios.h
        static final int ISIG = 0000001;
        /**
         * The Icanon.
         */
        static final int ICANON = 0000002;
        /**
         * The Echo.
         */
        static final int ECHO = 0000010;
        /**
         * The Echonl.
         */
        static final int ECHONL = 0000100;
        /**
         * The Tcsanow.
         */
        static final int TCSANOW = 0;
    }

    private static interface Libc extends Library {
        /**
         * Tcgetattr int.
         *
         * @param fd      the fd
         * @param termios the termios
         * @return the int
         * @throws LastErrorException the last error exception
         */
// termios.h
        int tcgetattr(int fd, Termios termios) throws LastErrorException;

        /**
         * Tcsetattr int.
         *
         * @param fd      the fd
         * @param opt     the opt
         * @param termios the termios
         * @return the int
         * @throws LastErrorException the last error exception
         */
        int tcsetattr(int fd, int opt, Termios termios) throws LastErrorException;

        /**
         * Isatty int.
         *
         * @param fd the fd
         * @return the int
         */
// unistd.h
        int isatty(int fd);
    }
}
