package main.objects;

import main.utils.Utils;

/**
 * Weapon class
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Weapon extends Item {

    private int damage;

    /**
     * Constructor of the Weapon class
     * Instantiate a random new Weapon
     */
    public Weapon() {
        super();
        this.damage = Utils.randomInt(1,500);
    }
}
