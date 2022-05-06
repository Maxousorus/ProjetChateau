package main.game;

import main.locations.Castle;
import main.utils.Generate;
import main.visibles.Map;

public class Main {

    /**
     * The main method.
     *
     * @param args
     */

    public static void main(String[] args) {

        Castle castle = Generate.castle(3);
        Map map = new Map(castle);
        map.show(1);
    }
}
