package main.objects;

import main.utils.Utils;

/**
 * Potion class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Potion extends Item {
    private int pv;

    /**
     * Constructor for the Potion class
     * Instantiate a random new Potion
     */
    public Potion() {
        super();
        this.pv = Utils.randomInt(0, 500);
    }
}
