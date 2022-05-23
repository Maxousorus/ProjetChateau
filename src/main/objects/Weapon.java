package main.objects;

import main.utils.Utils;

/**
 * The type Weapon.
 */
public class Weapon extends Item {

    private int damage;
    private int numberFloor;
    private String name;
    private String [] names = {
        "Sword",
        "Spear",
        "Boomerang",
        "Bow",
        "Dagger",
        "Cudgel"
    };
    /**
     * Instantiates a new Weapon.
     */
    public Weapon(int numberFloor) {
        super();
        this.damage = getWeapon(numberFloor);
        this.numberFloor = numberFloor;
        this.name = names[Utils.randomInt(0,names.length-1)];
    }

    public int getWeapon (int numberFloor){
        if(numberFloor <= 10){
            damage = Utils.randomInt(5,5 + (3 * numberFloor));
        }
        else{
            damage = Utils.randomInt(10,30);
        }
        return damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
