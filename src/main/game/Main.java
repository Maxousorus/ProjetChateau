package main.game;

import com.sun.jna.LastErrorException;
import main.locations.Castle;
import main.utils.Generate;
import main.utils.RawConsoleInput;
import main.visibles.Map;
import main.visibles.Menu;

import java.io.IOException;

/**
 * The type Main.
 */
public class Main {

    /**
     * The main method who run th.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
         Game game = new Game();
         game.run();
    }
}
