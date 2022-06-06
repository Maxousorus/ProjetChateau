package main.challenges;

import main.options.Parameters;
import main.utils.Utils;

/**
 * The class Trap.
 * Is a subclass of the Challenge class.
 */
public class Trap extends Challenge {

    private final int damage;

    /**
     * Constructor of Trap.
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
     * Get damage by the Trap.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }
}
