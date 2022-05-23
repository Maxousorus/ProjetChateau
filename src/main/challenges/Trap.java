package main.challenges;

import main.locations.*;
import main.utils.Utils;

/**
 * Trap class.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Trap extends Challenge {

    private int damage;
    private int numberFloor;
    /**
     * Instantiates a new Trap.
     */
    public Trap(int numberFloor) {
        super();
        this.damage = damageTrap(numberFloor);
        this.numberFloor = numberFloor;
    }

    private int damageTrap(int numberFloor){
        if(numberFloor <= 10){
            damage = Utils.randomInt(1,3*numberFloor);
        }
        else{
            damage = Utils.randomInt(10,30);
        }
        return damage;
    }

    public int getDamage() {
        return damage;
    }
}
