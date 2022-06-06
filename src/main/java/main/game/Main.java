package main.game;

import main.options.GlobalStatistics;
import main.options.Parameters;
import main.visibles.Menu;
import main.visibles.Notification;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Parameters.initParameters();

        Game game;
        Game previous_game = null;
        GlobalStatistics global_statistics = new GlobalStatistics();

        boolean quit = false;
        while(!quit) {
            Menu menu;
            if(previous_game == null) {
                menu = new Menu("Welcome brave warrior !", new String[]{"Start", "Settings", "Statistics", "Credits", "Quit"});

                switch (menu.choose()) {
                    case 0 -> {
                        game = new Game();
                        previous_game = game;
                        game.run();
                        global_statistics.updateGlobalStatistics(game.getGame_statistics());
                    }
                    case 1 -> Parameters.parametersMenu();
                    case 2 -> global_statistics.showGlobalStatistics();
                    case 3 -> new Notification("This game has been created by : BOUDIER Maxime, BAYEN Maxime, FOURNIER Victor, DOSSA Josias !").choose();
                    case 4 -> quit = true;
                }
            }else {
                menu = new Menu("Welcome back !", new String[]{"Restart in a new Castle", "Visit previous castle", "Last Game Statistics", "Settings", "Statistics", "Credits", "Quit"});

                switch (menu.choose()) {
                    case 0 -> {
                        game = new Game();
                        previous_game = game;
                        game.run();
                        global_statistics.updateGlobalStatistics(game.getGame_statistics());
                    }
                    case 1 -> {
                        previous_game.run();
                    }
                    case 2 -> previous_game.getGame_statistics().showGameStatistics();
                    case 3 -> Parameters.parametersMenu();
                    case 4 -> global_statistics.showGlobalStatistics();
                    case 5 -> new Notification("This game has been created by : BOUDIER Maxime, BAYEN Maxime, FOURNIER Victor, DOSSA Josias !").choose();
                    case 6 -> quit = true;
                }
            }

        }
    }
}
