package main.objects;

import main.utils.Parameters;
import main.utils.Utils;

/**
 * Potion class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Potion extends Item {
    private final int pv;

    /**
     * Constructor for the Potion class
     * Instantiate a random new Potion
     *
     * @param numberFloor the number floor
     */
    public Potion(int numberFloor) {
        super();
        this.pv = healPotion(numberFloor);
    }

    private int healPotion (int numberFloor){
        if(numberFloor <= 10){
            return Utils.randomInt(Parameters.POTION_MIN_HEALTH + numberFloor,Parameters.POTION_MIN_HEALTH + 4*numberFloor);
        }
        else{
            return Utils.randomInt(Parameters.POTION_MIN_HEALTH + 10,Parameters.POTION_MIN_HEALTH + Parameters.POTION_HEALTH_FLOOR_MULTIPLIER * 10);
        }
    }

    /**
     * Gets pv.
     *
     * @return the pv
     */
    public int getPv() {
        return pv;
    }
}
