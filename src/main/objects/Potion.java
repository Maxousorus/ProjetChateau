package main.objects;

import main.utils.Utils;

/**
 * Potion class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Potion extends Item {
    private int pv;
    private int numberFloor;

    /**
     * Constructor for the Potion class
     * Instantiate a random new Potion
     *
     * @param numberFloor the number floor
     */
    public Potion(int numberFloor) {
        super();
        this.pv = healPotion(numberFloor);
        this.numberFloor = numberFloor;
    }

    private int healPotion (int numberFloor){
        numberFloor +=1;
        if(numberFloor <= 10){
            pv = Utils.randomInt(1,4*numberFloor);
        }
        else{
            pv = Utils.randomInt(10,40);
        }
        return pv;
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
