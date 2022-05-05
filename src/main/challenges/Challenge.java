package main.challenges;

import main.interfaces.CanBeInRoom;
import main.utils.Parameters;

import java.lang.reflect.Parameter;

/**
 * Challenge class
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public abstract class Challenge implements CanBeInRoom {

    private String name;

    /**
     * Constructor of the class Challenge
     * Instantiate a random new challenge
     */
    public Challenge(){
        if(Parameters.CHANCE_OF_CHALLENGE_IS_TRAP > Math.random()){
        }
    }

    /**
     * Getter of the name of the challenge
     * @return the name of the challenge
     */
    public String getName() {
        return name;
    }
}
