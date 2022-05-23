package main.objects;

import main.utils.Utils;

/**
 * Potion class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Potion extends Item {
    private int pv;

    /**
     * Constructor for the Potion class
     * Instantiate a random new Potion
     */
    public Potion() {
        super();
        this.pv = Utils.randomInt(0, 500);
    }

    private int healPotion (int numberFloor){
        if(numberFloor <= 10){
            pv = Utils.randomInt(1,4*numberFloor);
        }
        else{
            pv = Utils.randomInt(10,40);
        }
        return pv;
    }

    public int getPv() {
        return pv;
    }
}
