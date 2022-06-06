package main.locations;

import main.challenges.Trap;
import main.interfaces.CanBeInPassage;
import main.challenges.Sage;
import main.options.Parameters;

import java.util.ArrayList;

/**
 * The type Passage.
 */
public class Passage {
    private CanBeInPassage event;
    private boolean locked;
    private boolean visited = false;

    /**
     * Instantiates a new Passage.
     *
     * @param floor the floor
     */
    public Passage(Floor floor) {
        if(Math.random() < Parameters.CHANCE_OF_CHALLENGE_IN_PASSAGE) {
            if (Math.random() < Parameters.CHANCE_OF_CHALLENGE_IS_TRAP) {
                this.event = new Trap(floor.getFloorNumber());
            } else {
                this.event = new Sage();
            }
        }
        this.locked = Math.random() < Parameters.CHANCE_OF_LOCKED_PASSAGE;
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public CanBeInPassage getEvent() {
        return event;
    }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(CanBeInPassage event) {
        this.event = event;
    }

    /**
     * Sets visited.
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * This method permit to do a visual of the passage to print the map.
     *
     * @param horizontal the horizontal
     * @return the array list
     */
    public ArrayList<String> toStringList(boolean horizontal) {
        ArrayList<String> result = new ArrayList<>();
        if (horizontal) { //Print horizontal passage
            if (this.visited) {
                if (Parameters.ROOM_HEIGHT % 2 == 0) { //IDE find an error but it's not an error
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.RESET_COLOR + "    " );
                    result.add(Parameters.RESET_COLOR + "    ");
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                } else {
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.RESET_COLOR + "    ");
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                }
            } else {
                if (Parameters.ROOM_HEIGHT % 2 == 0) { //IDE find an error but it's not an error
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                } else {
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                }
            }
        } else { //Print vertical passage
            if (this.visited) {
                if (Parameters.ROOM_SIZE % 2 == 0) { //IDE find an error but it's not an error
                    result.add(Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR + "  " + Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR + "  " + Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR);
                } else {
                    result.add(Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR + " " + Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR + " " + Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR);
                }
            } else {
                if (Parameters.ROOM_SIZE % 2 == 0) { //IDE find an error but it's not an error
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "    " + Parameters.RESET_COLOR);
                } else {
                    result.add(Parameters.MAP_COLOR + "   " + Parameters.RESET_COLOR);
                    result.add(Parameters.MAP_COLOR + "   " + Parameters.RESET_COLOR);
                }
            }
        }
        return result;
    }
}
