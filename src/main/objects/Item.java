package main.objects;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;

/**
 * Item class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Item implements CanBeInRoom {

    private String name;
    private String[] tabName = {"GodJonas", "Tigrou", "Dumbo", "Grizzly", "Poulpi", "Chèvre"};


    /**
     * Constructor of the class Item
     * Instantiate a random new item
     */
    public Item() {
        this.name = tabName[Utils.randomInt(1, 6)];
    }
}
