package main.utils;

import static java.lang.Math.random;

import main.challenges.Challenge;
import main.challenges.Sage;
import main.challenges.Trap;
import main.entities.Monster;
import main.locations.*;
import main.objects.*;

/**
 * This class is used to generate random objects and monsters.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Generate {

    /**
     * This method is used to generate a random castle.
     * Using Utils methods to set properties of the castle.
     *
     * @param nbFloors The number of floors of the castle.
     * @return The castle.
     */
    public static Castle castle(int nbFloors) {
        Castle castle = new Castle(nbFloors); // Create the castle
        Utils.setCastleSpawn(castle); // Set the castle spawn
        Utils.setCastleStairs(castle); // Set the castle stairs
        Utils.setCastleExit(castle); // Set the castle exit
        Utils.setCastlePassages(castle); // Set the castle passages using Maze Algorithm

        return castle;
    }

    /**
     * This method is used to generate a random Challenge.
     *
     * @return The challenge.
     */
    public static Challenge challenge() {
        if (random() > 0.5) {
            return new Sage();
        }
        return new Trap();
    }

    /*public static Item item() {
        if(random()> 0.5) {
            return new Potion();
        }
        return new Item();
    }*/
}
