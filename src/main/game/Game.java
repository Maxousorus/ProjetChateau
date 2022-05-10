package main.game;

import main.entities.Player;
import main.locations.Castle;
import main.utils.Generate;
import main.utils.Parameters;

public class Game {
    public void run() {

        Castle castle = Generate.castle(Parameters.FLOOR_SIZE);
        Player player = new Player();

        player.spawn(castle);

        while (!player.getRoom().isExit()){

        }
    }
}
