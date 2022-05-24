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
     *
     * @param numberFloor the number floor
     */
    public Trap(int numberFloor) {
        super();
        this.damage = damageTrap(numberFloor);
        this.numberFloor = numberFloor;
    }

    private int damageTrap(int numberFloor){
        numberFloor +=1;
        if(numberFloor <= 10){
            damage = Utils.randomInt(1,3*numberFloor);
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
}
