package main.objects;

import main.options.Parameters;
import main.utils.Utils;

/**
 * The type Weapon.
 */
public class Weapon extends Item {

    private final int damage;
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
        this.damage = createDamage(numberFloor);
        this.name = names[Utils.randomInt(0,names.length-1)];
    }

    /**
     * Sets damage.
     *
     * @param numberFloor the number floor
     */
    public int createDamage(int numberFloor){
        if(numberFloor <= 10)
            return Utils.randomInt(Parameters.WEAPON_MIN_DAMAGE + numberFloor,Parameters.WEAPON_MIN_DAMAGE + Parameters.WEAPON_DAMAGE_FLOOR_MULTIPLIER * numberFloor);
        else
            return Utils.randomInt(Parameters.WEAPON_MIN_DAMAGE + 10,Parameters.WEAPON_MIN_DAMAGE + Parameters.WEAPON_DAMAGE_FLOOR_MULTIPLIER * 10);
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
