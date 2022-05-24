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
     *
     * @param numberFloor the number floor
     */
    public Weapon(int numberFloor) {
        super();
        this.damage = getWeapon(numberFloor);
        this.numberFloor = numberFloor;
        this.name = names[Utils.randomInt(0,names.length-1)];
    }

    /**
     * Get weapon int.
     *
     * @param numberFloor the number floor
     * @return the int
     */
    public int getWeapon (int numberFloor){
        numberFloor +=1;
        if(numberFloor <= 10){
            damage = Utils.randomInt(5,5 + (3 * numberFloor));
        }
        else{
            damage = Utils.randomInt(10,30);
        }
        return damage;
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
