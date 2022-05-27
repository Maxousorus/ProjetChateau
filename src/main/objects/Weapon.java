package main.objects;

import main.utils.Utils;

/**
 * The type Weapon.
 */
public class Weapon extends Item {

    private int damage;
    private final String name;
    private final String [] names = {
            "Sword",
            "Spear",
            "Boomerang",
            "Bow",
            "Dagger",
            "Cudgel",
            "Axe",
            "Mace",
            "Staff",
            "Wand",
            "Scythe",
            "Knife",
            "Claw",
            "Saber",
            "Halberd",
            "Sling",
            "Shuriken",
            "Dart",
            "Javelin",
            "Crossbow",
            "Thanos Gauntlet"
    };

    /**
     * Instantiates a new Weapon.
     *
     * @param numberFloor the number floor
     */
    public Weapon(int numberFloor) {
        super();
        setDamage(numberFloor);
        this.name = names[Utils.randomInt(0,names.length-1)];
    }

    /**
     * Sets damage.
     *
     * @param numberFloor the number floor
     */
    public void setDamage(int numberFloor){
        if(numberFloor <= 10)
            damage = Utils.randomInt((5 + numberFloor),(5 + (3 * (numberFloor+1))));
        else
            damage = Utils.randomInt(10,35);
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
