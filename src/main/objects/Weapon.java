package main.objects;

import main.utils.Utils;

public class Weapon extends Item {

    private int damage;

    public Weapon() {
        super();
        this.damage = Utils.randomInt(1,500);
    }
}
