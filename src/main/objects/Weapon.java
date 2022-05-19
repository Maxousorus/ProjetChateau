package main.objects;

import main.utils.Utils;

/**
 * The type Weapon.
 */
public class Weapon extends Item {

    private int damage;

    /**
     * Instantiates a new Weapon.
     */
    public Weapon() {
        super();
        this.damage = Utils.randomInt(1,500);
    }
}
