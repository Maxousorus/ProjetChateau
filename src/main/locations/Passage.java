package main.locations;

import main.entities.*;
import main.challenges.*;
import main.utils.*;

import java.util.ArrayList;

/**
 * Passage class represents a passage between two rooms.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Passage {

    private int challenge_type; //Monster or Sage or Trap
    private Entity monster;
    private Sage sage;
    private Trap trap;
    private boolean locked;

    private boolean visited = false;

    private static final int MONSTER = 0;
    private static final int SAGE = 1;
    private static final int TRAP = 2;

    /**
     * Constructor of the Passage class.
     * Instanciate a random new Passage.
     */
    public Passage() {
        challenge_type = (int) (Math.random() * 3);
        if (challenge_type == MONSTER) {
            this.monster = new Entity();
        } else if (challenge_type == SAGE) {
            this.sage = new Sage();
        } else if (challenge_type == TRAP) {
            this.trap = new Trap();
        }
        this.locked = Math.random() < Parameters.CHANCE_OF_LOCKED_PASSAGE;
    }

    public void setVisited() {
        this.visited = true;
    }

    /**
     * This method return a list of each line of the visible passage like appear in the map.
     *
     * @return a list of each line of the visible passage.
     */
    public ArrayList<String> toStringList(boolean horizontal) {
        ArrayList<String> result = new ArrayList<>();
        if (horizontal) { //Print horizontal passage
            if (this.visited) {
                if (Parameters.ROOM_HEIGHT % 2 == 0) { //IDE find an error but it's not an error
                    result.add("▓▓▓▓");
                    result.add("    ");
                    result.add("    ");
                    result.add("▓▓▓▓");
                } else {
                    result.add("▓▓▓▓");
                    result.add("    ");
                    result.add("▓▓▓▓");
                }
                return result;
            } else {
                if (Parameters.ROOM_HEIGHT % 2 == 0) { //IDE find an error but it's not an error
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                } else {
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                }
                return result;
            }
        } else { //Print vertical passage
            if (this.visited) {
                if (Parameters.ROOM_SIZE % 2 == 0) { //IDE find an error but it's not an error
                    result.add("▓  ▓");
                    result.add("▓  ▓");
                } else {
                    result.add("▓ ▓");
                    result.add("▓ ▓");
                }
            } else {
                if (Parameters.ROOM_SIZE % 2 == 0) { //IDE find an error but it's not an error
                    result.add("▓▓▓▓");
                    result.add("▓▓▓▓");
                } else {
                    result.add("▓▓▓");
                    result.add("▓▓▓");
                }
            }
            return result;
        }
    }
}
