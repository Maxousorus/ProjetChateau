package main.challenges;

import main.utils.Parameters;
import main.utils.Utils;

/**
 * Trap class.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Trap extends Challenge {

    private final int damage;

    /**
     * Instantiates a new Trap.
     *
     * @param numberFloor the number floor
     */
    public Trap(int numberFloor) {
        super();
        this.damage = damageTrap(numberFloor);
    }

    private int damageTrap(int numberFloor){
        if(numberFloor <= 10){
            return Utils.randomInt(Parameters.TRAP_MIN_DAMAGE + numberFloor, Parameters.TRAP_MIN_DAMAGE + Parameters.TRAP_DAMAGE_FLOOR_MULTIPLIER*numberFloor);
        }
        else{
            return Utils.randomInt(Parameters.TRAP_MIN_DAMAGE + 10,Parameters.TRAP_MIN_DAMAGE + Parameters.TRAP_DAMAGE_FLOOR_MULTIPLIER * 10);
        }
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
