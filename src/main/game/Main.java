package main.game;

import main.locations.Castle;
import main.utils.Generate;
import main.utils.RawConsoleInput;
import main.visibles.Map;
import main.visibles.Menu;

import java.io.IOException;

public class Main {

    /**
     * The main method who run th.
     *
     * @param args
     */

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println(RawConsoleInput.read(true));
        }
    }
}
