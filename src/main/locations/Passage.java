package main.locations;

import main.challenges.*;
import main.interfaces.CanBeInPassage;
import main.utils.*;

import java.util.ArrayList;

/**
 * Passage class represents a passage between two rooms.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Passage {
    private CanBeInPassage event;
    private boolean locked;
    private boolean visited = false;

    /**
     * Constructor of the Passage class.
     * Instantiate a random new Passage.
     */
    public Passage(Castle castle) {
        if(Math.random() < Parameters.CHANCE_OF_CHALLENGE_IN_PASSAGE) {
            if (Math.random() < Parameters.CHANCE_OF_CHALLENGE_IS_TRAP) {
                this.event = new Trap(castle.getFloorOfPassage(this).getFloorNumber());
            } else {
                this.event = new Sage();
            }
        }
        this.locked = Math.random() < Parameters.CHANCE_OF_LOCKED_PASSAGE;
    }

    /**
     * This method return the event of the passage.
     *
     * @return the event of the passage.
     * @see CanBeInPassage
     */
    public CanBeInPassage getEvent() {
        return event;
    }

    /**
     * This method return the event of the passage.
     *
     * @param event the event
     * @see CanBeInPassage
     */
    public void setEvent(CanBeInPassage event) {
        this.event = event;
    }

    /**
     * This method set the passage as visited.
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * This method return a list of each line of the visible passage like appear in the map.
     *
     * @param horizontal the horizontal
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
        }
        return result;
    }
}
