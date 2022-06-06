package main.locations;

import main.entities.Player;
import main.exception.RoomCoordinateException;
import main.interfaces.CanBeInRoom;
import main.options.Parameters;
import main.utils.Generate;

import java.util.ArrayList;

/**
 * The type Room.
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
     * Instantiates a new Room.
     *
     * @param floor the floor
     */
    public Room(Floor floor) {
        if (Math.random() < Parameters.CHANCE_OF_ITEM_IN_ROOM) {
            this.roomEvent = Generate.item(floor.getFloorNumber());
        }  else if (Math.random() < Parameters.CHANCE_OF_MONSTER_IN_ROOM) {
            this.roomEvent = Generate.monster(floor.getFloorNumber());
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
     * Gets floor.
     *
     * @return the floor
     */
    public Floor getFloor() {
        return floor;
    }

    /**
     * Gets room event.
     *
     * @return the room event
     */
    public CanBeInRoom getRoomEvent() {
        return roomEvent;
    }

    /**
     * Sets room event.
     *
     * @param roomEvent the room event
     */
    public void setRoomEvent(CanBeInRoom roomEvent) {
        this.roomEvent = roomEvent;
    }

    /**
     * Is visited boolean.
     *
     * @return the boolean
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * Sets visited.
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * Sets spawn.
     */
    public void setSpawn() {
        this.spawn = true; //Spawn is a room with no event
        this.roomEvent = null;
    }

    /**
     * Is spawn boolean.
     *
     * @return the boolean
     */
    public boolean isSpawn() {
        return spawn;
    }

    /**
     * Sets upstairs.
     */
    public void setUpStairs() {
        this.upstairs = true; //Upstairs is a room with no event
        this.roomEvent = null;
    }

    /**
     * Is upstairs boolean.
     *
     * @return the boolean
     */
    public boolean isUpStairs() {
        return upstairs;
    }

    /**
     * Sets downstairs.
     */
    public void setDownStairs() {
        this.downstairs = true; //Downstairs is a room with no event
        this.roomEvent = null;
    }

    /**
     * Is downstairs boolean.
     *
     * @return the boolean
     */
    public boolean isDownStairs() {
        return downstairs;
    }

    /**
     * Sets exit.
     *
     * @param exit the exit
     */
    public void setExit(boolean exit) {
        this.exit = exit; //Exit is a room with no event
        this.roomEvent = null;
    }

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Get room coordinates int [ ].
     *
     * @return the int [ ]
     */
    public int[] getRoomCoordinates() throws RoomCoordinateException {
        for(int x = 0 ; x < floor.getRooms().length ; x++) { //For each room
            for(int y = 0 ; y < floor.getRooms()[x].length ; y++) {
                if(floor.getRooms()[x][y] == this) { //If the room is the one we are looking for
                    return new int[] {x,y}; //Return the coordinates
                }
            }
        }
        throw new RoomCoordinateException();
    }

    /**
     * This method permit to do a visual of the room to print the map.
     *
     * @param player the player
     * @return the array list
     */
    public ArrayList<String> toStringList(Player player) {
        ArrayList<String> roomstring = new ArrayList<>(); //Create a list of strings
        if (!isVisited()) { //If the room is not visited
            for (int i = 0; i < Parameters.ROOM_SIZE; i++) {
                String line = ""; //Create a line
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) { //For each character in the line
                    line += Parameters.MAP_COLOR + " " + Parameters.RESET_COLOR; //Add a wall
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
                String line = Parameters.RESET_COLOR;
                for (int j = 0; j < Parameters.ROOM_SIZE; j++)
                    line += " ";
                roomstring.add(line);
            }
            return roomstring; //Return the list
        }
        if (upstairs) { //If the room is a room with upstairs
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = Parameters.RESET_COLOR;
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = Parameters.RESET_COLOR;
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
                String line = Parameters.RESET_COLOR;
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = Parameters.RESET_COLOR;
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
                String line = Parameters.RESET_COLOR;
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = Parameters.RESET_COLOR;
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
            String line = Parameters.RESET_COLOR;
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
