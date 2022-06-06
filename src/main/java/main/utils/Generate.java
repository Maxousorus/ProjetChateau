package main.utils;

import main.entities.Entity;
import main.objects.Potion;
import main.options.Parameters;
import main.locations.Castle;
import main.objects.Item;
import main.objects.Weapon;

import static java.lang.Math.random;

/**
 * The type Generate.
 */
public class Generate {

    /**
     * Generate a castle.
     *
     * @param nbFloors the nb floors
     * @return the castle
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
     * Generate an item.
     *
     * @param floorNumber the floor number
     * @return the item
     */
    public static Item item(int floorNumber) {
        if(random() < Parameters.CHANCE_OF_ITEM_IS_WEAPON) {
            return new Weapon( floorNumber );
        }
        return new Potion( floorNumber );
    }

    /**
     * Generate a monster.
     *
     * @param floorNumber the floor number
     * @return the entity
     */
    public static Entity monster(int floorNumber) {
        return new Entity( floorNumber );
    }
}
