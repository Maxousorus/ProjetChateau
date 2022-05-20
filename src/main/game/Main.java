package main.game;

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
         Game game = new Game();

         //TODO Menu principale (lancer le jeu, paramètres, credits, quitter)

         game.run();
    }
}
