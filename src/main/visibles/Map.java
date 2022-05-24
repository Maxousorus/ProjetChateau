package main.visibles;

import main.entities.Player;
import main.locations.*;
import main.utils.Parameters;

import java.util.ArrayList;

/**
 * This class represents a map.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias.
 */
public class Map {

    private final Player player; // The player of the map.

    private final String sidewall = "\u2593\u2593"; //Wall on side (left and right) of the map.

    private final String aroundPassage = "\u2593\u2593\u2593\u2593"; //Wall around the passage and between rooms

    /**
     * Constructor of the class Map.
     *
     * @param player the player.
     * @see Player
     */
    public Map(Player player) {
        this.player = player;
    }

    /**
     * This method returns the player of the map.
     *
     * @return the player of the map.
     * @see Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method returns the current floor of the map.
     *
     * @return the floor number of the map.
     */
    public int getFloor() {
        return player.getRoom().getFloor().getFloorNumber();
    }

    /**
     * This method returns the string of the updownwall.
     *
     * @return the string of the updownwall.
     */
    private String updownwall() { //Wall on top and bottom of the map.
        String room = "";
        for (int i = 0; i < Parameters.ROOM_SIZE; i++) {
            room += "\u2593";
        }
        String updownwall = sidewall;
        for (int i = 0; i < Parameters.FLOOR_SIZE; i++) {
            updownwall += room;
            if (i < Parameters.FLOOR_SIZE - 1) {
                updownwall += aroundPassage;
            }
        }
        updownwall += sidewall;
        return updownwall;
    }

    /**
     * This method print the map.
     */
    public void show() {
        System.out.println(this); //Print the map.
    }

    /**
     * This method returns the string of the map.
     * @return the string of the map.
     */
    @Override
    public String toString() {
        Floor floor = player.getRoom().getFloor();
        Room[][] rooms = floor.getRooms();
        String map = Parameters.MAP_COLOR; //Color of the map.
        map += updownwall() + "\n";//Add the top wall

        for (int row = 0; row < Parameters.FLOOR_SIZE; row++) { //For each row of rooms
            for (int nbline = 0; nbline < Parameters.ROOM_HEIGHT; nbline++) { //for each line of the row of rooms
                String line = Parameters.MAP_COLOR;
                line += sidewall; //Put sidewall on the left of the line
                for (int col = 0; col < Parameters.FLOOR_SIZE; col++) { //For each room of the column
                    line += rooms[row][col].toStringList(player).get(nbline); //Add the line of the room
                    line += Parameters.MAP_COLOR;
                    if (col < Parameters.FLOOR_SIZE - 1) { //If it's not the last room of the column
                        Passage[][] h_passages = floor.getHorizontal_passages();
                        if (h_passages[row][col] != null) { //If there is a passage between the room and the next
                            ArrayList<String> passage = floor.getHorizontal_passages()[row][col].toStringList(true); //Put the passage between the room and the next
                            if (Parameters.ROOM_HEIGHT % 2 == 1) { //If the number of lines of the passage is not pair
                                if (nbline >= (Parameters.ROOM_HEIGHT / 2) - 1 && nbline <= (Parameters.ROOM_HEIGHT / 2) + 1) { //If the line of the passage is in the middle of the passage
                                    line += passage.get(nbline - ((Parameters.ROOM_HEIGHT / 2) - 1)); //Add the line of the passage
                                } else line += "\u2593\u2593\u2593\u2593"; //If the line of the passage is not in the middle of the passage, put a wall
                            } else if (Parameters.ROOM_HEIGHT % 2 == 0) { //If the number of lines of the passage is pair
                                if (nbline >= (Parameters.ROOM_HEIGHT / 2) - 2 && nbline <= (Parameters.ROOM_HEIGHT / 2) + 1) { //If the line of the passage is in the middle of the passage
                                    line += passage.get(nbline - ((Parameters.ROOM_HEIGHT / 2) - 2)); //Add the line of the passage
                                } else line += "\u2593\u2593\u2593\u2593";  //If the line of the passage is not in the middle of the passage, put a wall
                            } else { //If the number of lines of the passage is not a number
                                line += "\u2593\u2593\u2593\u2593"; //If the number of lines of the passage is not even, put a wall
                            }
                        } else { //If there is no passage between the room and the next
                            line += aroundPassage; //If there is no passage between the room and the next, put a wall
                        }
                    }
                }
                line += sidewall; //Put sidewall on the right of the line
                map += line + "\n"; //Add the line
            }
            if (row < Parameters.FLOOR_SIZE - 1) { //If it's not the last row of the floor
                for (int i = 0; i < 2; i++) { //For each line of the passage between the row of rooms and the next
                    String line = sidewall; //Put sidewall on the left of the line
                    Passage[][] v_passages = floor.getVertical_passages();
                    for (int col = 0; col < Parameters.FLOOR_SIZE; col++) { //For each room of the column
                        if (v_passages[row][col] == null) { //If there is no passage between the room and the next
                            for (int j = 0; j < Parameters.ROOM_SIZE; j++) { //For each line of the room
                                line += "\u2593"; //If there is no passage between the room and the next, put a wall
                            }
                        } else { //If there is a passage between the room and the next
                            String nextToPassage = "";//Add the line of the passage after the middle of the passage
                            if (Parameters.ROOM_SIZE % 2 == 1) { //If the number of lines of the passage is not pair
                                for (int j = 0; j < (Parameters.ROOM_SIZE / 2) - 1; j++) { //For each line of the passage before the middle of the passage
                                    nextToPassage += "\u2593"; //Put a wall
                                }
                            } else {
                                for (int j = 0; j < (Parameters.ROOM_SIZE / 2) - 2; j++) { //For each line of the passage before the middle of the passage
                                    nextToPassage += "\u2593"; //Put a wall
                                }
                            }
                            line += nextToPassage; //Add the line of the passage before the middle of the passage
                            line += v_passages[row][col].toStringList(false).get(i); //Add the line of the passage
                            line += nextToPassage; //Add the line of the passage after the middle of the passage
                        }
                        if (col < Parameters.FLOOR_SIZE - 1) { //If it's not the last room of the column
                            line += "\u2593\u2593\u2593\u2593"; //Put a wall between the room and the next
                        } else line += sidewall; //Put sidewall on the right of the line
                    }
                    map += line + "\n"; //Add the line
                }
            } else {
                map += updownwall(); //Add the last line of the floor
            }
        }
        map += Parameters.RESET_COLOR;
    return map;
    }
}
