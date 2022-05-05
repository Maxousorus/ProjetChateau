package main.locations;

import main.entities.*;
import main.challenges.*;
import main.interfaces.VisibleInMap;
import main.utils.*;

import java.util.ArrayList;

/**
 * Passage class represents a passage between two rooms.
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Passage implements VisibleInMap {

    private int challenge_type; //Monster or Sage or Trap
    private Entity monster;
    private Sage sage;
    private Trap trap;
    private boolean locked;

    private static final int MONSTER = 0;
    private static final int SAGE = 1;
    private static final int TRAP = 2;

    /**
     * Constructor of the Passage class.
     * Instanciate a random new Passage.
     */
    public Passage(){
        challenge_type = (int) (Math.random() * 3);
        if (challenge_type == MONSTER) {
            this.monster = new Entity();
        } else if (challenge_type == SAGE) {
            this.sage = new Sage();
        } else if (challenge_type == TRAP) {
            this.trap = new Trap();
        }

        this.locked = (Math.random() < Parameters.CHANCE_OF_LOCKED_PASSAGE);
    }

    /**
     * This method return a list of each line of the visible passage like appear in the map.
     * @return a list of each line of the visible passage.
     */
    @Override
    public ArrayList<String> toStringList() {
        return null; //TODO A faire
    }
}
