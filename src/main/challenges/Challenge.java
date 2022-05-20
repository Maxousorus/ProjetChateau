package main.challenges;

import main.interfaces.CanBeInPassage;

/**
 * Challenge class.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public abstract class Challenge implements CanBeInPassage {

    private String name;

    /**
     * Constructor of the class Challenge.
     * Instantiate a random new challenge.
     */
    public Challenge() {
    }

    /**
     * Getter of the name of the challenge.
     *
     * @return the name of the challenge.
     */
    public String getName() {
        return name;
    }
}
