package main.utils;

import main.entities.Monster;
import main.locations.Castle;
import main.objects.Item;
import main.objects.Potion;
import main.objects.Weapon;

import static java.lang.Math.random;

/**
 * This class is used to generate random objects and monsters.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public class Generate {

    /**
     * This method is used to generate a random castle.
     * Using Utils methods to set properties of the castle.
     *
     * @param nbFloors The number of floors of the castle.
     * @return The castle.
     * @see Castle,Utils
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
     * This method is used to generate a random Item.
     *
     * @return The item.
     * @see Item
     */
    public static Item item(int floorNumber) {
        if(random() < Parameters.CHANCE_OF_ITEM_IS_WEAPON) {
            return new Weapon(  );
        }
        return new Potion( floorNumber );
    }

    /**
     * This method is used to generate a random Monster.
     *
     * @return The monster.
     * @see Monster
     */
    public static Monster monster(int floorNumber) {
        return new Monster(  );
    }
}
