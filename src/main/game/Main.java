package main.game;

import main.utils.Parameters;
import main.visibles.Menu;
import main.visibles.Notification;
import main.visibles.Popup;

import java.io.IOException;

/**
 * The type Main.
 */
public class Main {

    /**
     * The main method who run the Game.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        Parameters.initParameters();

        Game game;
        Game previous_game = null;

        boolean quit = false;
        while(!quit) {
            Menu menu;
            if(previous_game == null) {
                menu = new Menu("Welcome brave warrior !", new String[]{"Start", "Settings", "Credits", "Quit"});

                switch (menu.choose()) {
                    case 0 -> {
                        game = new Game();
                        previous_game = game;
                        game.run();
                    }
                    case 1 -> Parameters.parametersMenu();
                    case 2 -> new Notification("This game has been created by : BOUDIER Maxime, BAYEN Maxime, FOURNIER Victor, DOSSA Josias !").choose();
                    case 3 -> quit = true;
                }
            }else {
                menu = new Menu("Welcome back !", new String[]{"Restart in a new Castle", "Visit previous castle", "Settings", "Credits", "Quit"});

                switch (menu.choose()) {
                    case 0 -> {
                        game = new Game();
                        previous_game = game;
                        game.run();
                    }
                    case 1 -> {
                        previous_game.run();
                    }
                    case 2 -> Parameters.parametersMenu();
                    case 3 -> new Notification("This game has been created by : BOUDIER Maxime, BAYEN Maxime, FOURNIER Victor, DOSSA Josias !").choose();
                    case 4 -> quit = true;
                }
            }

        }
    }
}
