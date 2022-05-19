package main.locations;

import main.entities.Player;
import main.interfaces.CanBeInRoom;
import main.utils.Generate;
import main.utils.Parameters;

import java.util.ArrayList;

/**
 * This class represents a room in the game.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public class Room {

    private CanBeInRoom roomEvent;
    private boolean visited;
    private boolean upstairs;
    private boolean downstairs;
    private boolean spawn;
    private boolean exit;
    private final Floor floor;

    /**
     * Constructor of the Room class.
     * Instantiates a random new Room.
     */

    public Room(Floor floor) {
        if (Math.random() < Parameters.CHANCE_OF_ITEM_IN_ROOM) {
            this.roomEvent = Generate.item();
        }  else if (Math.random() < Parameters.CHANCE_OF_MONSTER_IN_ROOM) {
            this.roomEvent = Generate.monster();
        } else {
            this.roomEvent = null;
        }
        this.visited = false;
        this.upstairs = false;
        this.downstairs = false;
        this.spawn = false;
        this.exit = false;
        this.floor = floor;
    }

    /**
     * This method returns the floor of the room.
     * @return the floor of the room.
     * @see Floor
     */
    public Floor getFloor() {
        return floor;
    }

    /**
     * This method returns the event of the room.
     * @return the event of the room.
     * @see CanBeInRoom
     */
    public CanBeInRoom getRoomEvent() {
        return roomEvent;
    }

    /**
     * This method set a room event in the room.
     * @param roomEvent the room event to set.
     *
     * @see CanBeInRoom
     */
    public void setRoomEvent(CanBeInRoom roomEvent) {
        this.roomEvent = roomEvent;
    }

    /**
     * This method returns if the room has been visited.
     *
     * @return true if the room has been visited, false otherwise.
     */

    public boolean isVisited() {
        return this.visited;
    }

    /**
     * This method set the room as visited.
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * This method set the room as spawn.
     */
    public void setSpawn() {
        this.spawn = true; //Spawn is a room with no event
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a spawn.
     *
     * @return true if the room is a spawn, false otherwise.
     */
    public boolean isSpawn() {
        return spawn;
    }

    /**
     * This method set room as a room with upstairs.
     */
    public void setUpStairs() {
        this.upstairs = true; //Upstairs is a room with no event
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a room with upstairs.
     *
     * @return true if the room is a room with upstairs, false otherwise.
     */
    public boolean isUpStairs() {
        return upstairs;
    }

    /**
     * This method set the room as a room with downstairs.
     */

    public void setDownStairs() {
        this.downstairs = true; //Downstairs is a room with no event
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a room with downstairs.
     *
     * @return true if the room is a room with downstairs, false otherwise.
     */
    public boolean isDownStairs() {
        return downstairs;
    }

    /**
     * This method set the room as an exit.
     *
     * @param exit true if the room is an exit, false otherwise.
     */
    public void setExit(boolean exit) {
        this.exit = exit; //Exit is a room with no event
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is an exit.
     *
     * @return true if the room is an exit, false otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * This method returns coordinates of the room (x,y).
     * @return coordinates of the room (x,y).
     */
    public int[] getRoomCoordinates() {
        for(int x = 0 ; x < floor.getRooms().length ; x++) { //For each room
            for(int y = 0 ; y < floor.getRooms()[x].length ; y++) {
                if(floor.getRooms()[x][y] == this) { //If the room is the one we are looking for
                    return new int[] {x,y}; //Return the coordinates
                }
            }
        }
        return new int[] {-1,-1}; //If the room is not found, return (-1,-1)
    }

    /**
     * This method return a list of each line of the visible room like appears in the map.
     * @param player the player.
     * @see Player
     * @return a list of each line of the visible room.
     */
    public ArrayList<String> toStringList(Player player) {
        ArrayList<String> roomstring = new ArrayList<>(); //Create a list of strings
        if (!isVisited()) { //If the room is not visited
            for (int i = 0; i < Parameters.ROOM_SIZE; i++) {
                String line = ""; //Create a line
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) { //For each character in the line
                    line += "▓"; //Add a wall
                }
                roomstring.add(line); //Add the line to the list
            }
            return roomstring; //Return the list
        }

        Room playerRoom = player.getRoom();
        boolean isPlayerInRoom = playerRoom == this;

        if (downstairs) { //If the room is a room with downstairs
            String l1 = ""; //Create a line
            for (int i = 0; i < Parameters.ROOM_SIZE - 3; i++)
                l1 += " ";
            if(isPlayerInRoom) { //If the player is in the room
                roomstring.add("\033[32m┐  \033[0m" + l1); //Make stairs green
                roomstring.add("\033[32m└─┐\033[0m" + l1);
                roomstring.add("\033[32m  └\033[0m" + l1);
            } else { //If the player is not in the room
                roomstring.add("┐  " + l1); //Make stairs normal
                roomstring.add("└─┐" + l1);
                roomstring.add("  └" + l1);
            }
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++)
                    line += " ";
                roomstring.add(line);
            }
            return roomstring; //Return the list
        }
        if (upstairs) { //If the room is a room with upstairs
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = "";
            for (int i = 0; i < Parameters.ROOM_SIZE - 3; i++)
                l1 += " ";
            if(isPlayerInRoom) { //If the player is in the room
                roomstring.add(l1 + "\033[32m┐  \033[0m"); //Make stairs green
                roomstring.add(l1 + "\033[32m└─┐\033[0m");
                roomstring.add(l1 + "\033[32m  └\033[0m");
            }else { //If the player is not in the room
                roomstring.add(l1 + "┐  "); //Make stairs normal
                roomstring.add(l1 + "└─┐");
                roomstring.add(l1 + "  └");
            }
            return roomstring; //Return the list
        }
        if (spawn) { //If the room is a room with spawn
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = "";
            for (int i = 0; i < Parameters.ROOM_SIZE - 3; i++)
                l1 += " ";
            if(isPlayerInRoom) { //If the player is in the room
                roomstring.add(l1 + "\033[32m╔══\033[0m"); //Make enter green
                roomstring.add(l1 + "\033[32m╠═ \033[0m");
                roomstring.add(l1 + "\033[32m╚══\033[0m");
            }else { //If the player is not in the room
                roomstring.add(l1 + "╔══"); //Make enter normal
                roomstring.add(l1 + "╠═ ");
                roomstring.add(l1 + "╚══");
            }
            return roomstring; //Return the list
        }
        if (exit) { //If the room is a room with exit
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = "";
            for (int i = 0; i < Parameters.ROOM_SIZE - 3; i++)
                l1 += " ";
            if(isPlayerInRoom) { //If the player is in the room
                roomstring.add(l1 + "\033[32m╔═╗\033[0m"); //Make exit green
                roomstring.add(l1 + "\033[32m╚═╗\033[0m");
                roomstring.add(l1 + "\033[32m╚═╝\033[0m");
            }else { //If the player is not in the room
                roomstring.add(l1 + "╔═╗"); //Make exit normal
                roomstring.add(l1 + "╚═╗");
                roomstring.add(l1 + "╚═╝");
            }
            return roomstring; //Return the list
        }
        //If the room is not a room with stairs, spawn or exit
        for (int i = 0; i < Parameters.ROOM_HEIGHT; i++) {
            String line = "";
            for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                if(isPlayerInRoom && i == Parameters.ROOM_HEIGHT/2 && j == Parameters.ROOM_SIZE/2) //If the player is in the room
                    line += "\033[32mX\033[0m"; //Make the player in the room green
                else //If the player is not in the room
                    line += " "; //Don't make the player in the room
            }
            roomstring.add(line);
        }
        return roomstring; //Return the list
    }
}
