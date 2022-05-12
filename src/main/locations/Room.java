package main.locations;

import static java.lang.Math.random;

import main.entities.Monster;
import main.entities.Player;
import main.interfaces.CanBeInRoom;
import main.utils.Generate;
import main.utils.Parameters;

import java.util.ArrayList;

/**
 * Room class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Room {

    private CanBeInRoom roomEvent;
    private boolean visited;
    private boolean upstairs;
    private boolean downstairs;
    private boolean spawn;
    private boolean exit;

    private Floor floor;

    /**
     * Constructor of the Room class
     * Instanciates a random new Room
     */

    public Room(Floor floor) {
        if (random() < Parameters.CHANCE_OF_ITEM_IN_ROOM) {
            //this.roomEvent = Generate.item();
        } else if (random() < Parameters.CHANCE_OF_CHALLENGE_IN_ROOM) {
            this.roomEvent = Generate.challenge();
        } else if (random() < Parameters.CHANCE_OF_MONSTER_IN_ROOM) {
            this.roomEvent = new Monster();
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

    public Floor getFloor() {
        return floor;
    }

    public CanBeInRoom getRoomEvent() {
        return roomEvent;
    }

    /**
     * This method set a room event in the room.
     * @param roomEvent
     */
    public void setRoomEvent(CanBeInRoom roomEvent) {
        this.roomEvent = roomEvent;
    }

    /**
     * This method returns if the room has been visited
     *
     * @return true if the room has been visited, false otherwise
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
     * This method set the room as spawn
     */
    public void setSpawn() {
        this.spawn = true;
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a spawn
     *
     * @return true if the room is a spawn, false otherwise
     */
    public boolean isSpawn() {
        return spawn;
    }

    /**
     * This method set room as a room with upstairs
     */
    public void setUpStairs() {
        this.upstairs = true;
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a room with upstairs
     *
     * @return true if the room is a room with upstairs, false otherwise
     */
    public boolean isUpStairs() {
        return upstairs;
    }

    /**
     * This method set the room as a room with downstairs
     */

    public void setDownStairs() {
        this.downstairs = true;
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is a room with downstairs
     *
     * @return true if the room is a room with downstairs, false otherwise
     */
    public boolean isDownStairs() {
        return downstairs;
    }

    /**
     * This method set the room as an exit
     *
     * @param exit true if the room is an exit, false otherwise
     */
    public void setExit(boolean exit) {
        this.exit = exit;
        this.roomEvent = null;
    }

    /**
     * This method returns if the room is an exit
     *
     * @return true if the room is an exit, false otherwise
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * This method returns coordinates of the room (x,y)
     * @return coordinates of the room (x,y)
     */
    public int[] getRoomCoordinates() {
        for(int x = 0 ; x < floor.getRooms().length ; x++) {
            for(int y = 0 ; y < floor.getRooms()[x].length ; y++) {
                if(floor.getRooms()[x][y] == this) {
                    return new int[] {x,y};
                }
            }
        }
        return new int[] {-1,-1};
    }

    /**
     * This method return a list of each line of the visible room like appears in the map.
     *
     * @return a list of each line of the visible room
     */
    public ArrayList<String> toStringList(Player player) {
        ArrayList<String> roomstring = new ArrayList<>();
        if (!isVisited()) {
            for (int i = 0; i < Parameters.ROOM_SIZE; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                    line += "▓";
                }
                roomstring.add(line);
            }
            return roomstring;
        }

        Room playerRoom = player.getRoom();
        int[] playerCoordinates = playerRoom.getRoomCoordinates();
        boolean isPlayerInRoom = playerRoom == this;

        if (downstairs) {
            String l1 = "";
            for (int i = 0; i < Parameters.ROOM_SIZE - 3; i++)
                l1 += " ";
            if(isPlayerInRoom) {
                roomstring.add("\033[32m┐  \033[0m" + l1);
                roomstring.add("\033[32m└─┐\033[0m" + l1);
                roomstring.add("\033[32m  └\033[0m" + l1);
            } else {
                roomstring.add("┐  " + l1);
                roomstring.add("└─┐" + l1);
                roomstring.add("  └" + l1);
            }
            for (int i = 0; i < Parameters.ROOM_HEIGHT - 3; i++) {
                String line = "";
                for (int j = 0; j < Parameters.ROOM_SIZE; j++)
                    line += " ";
                roomstring.add(line);
            }
            return roomstring;
        }
        if (upstairs) {
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
            if(isPlayerInRoom) {
                roomstring.add(l1 + "\033[32m┐  \033[0m");
                roomstring.add(l1 + "\033[32m└─┐\033[0m");
                roomstring.add(l1 + "\033[32m  └\033[0m");
            }else {
                roomstring.add(l1 + "┐  ");
                roomstring.add(l1 + "└─┐");
                roomstring.add(l1 + "  └");
            }
            return roomstring;
        }
        if (spawn) {
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
            if(isPlayerInRoom) {
                roomstring.add(l1 + "\033[32m╔══\033[0m");
                roomstring.add(l1 + "\033[32m╠═ \033[0m");
                roomstring.add(l1 + "\033[32m╚══\033[0m");
            }else {
                roomstring.add(l1 + "╔══");
                roomstring.add(l1 + "╠═ ");
                roomstring.add(l1 + "╚══");
            }
            return roomstring;
        }
        if (exit) {
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
            if(isPlayerInRoom) {
                roomstring.add(l1 + "\033[32m╔═╗\033[0m");
                roomstring.add(l1 + "\033[32m╚═╗\033[0m");
                roomstring.add(l1 + "\033[32m╚═╝\033[0m");
            }else {
                roomstring.add(l1 + "╔═╗");
                roomstring.add(l1 + "╚═╗");
                roomstring.add(l1 + "╚═╝");
            }
            return roomstring;
        }
        for (int i = 0; i < Parameters.ROOM_HEIGHT; i++) {
            String line = "";
            for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                if(isPlayerInRoom && i == Parameters.ROOM_HEIGHT/2 && j == Parameters.ROOM_SIZE/2)
                    line += "\033[32mX\033[0m";
                else
                    line += " ";
            }
            roomstring.add(line);
        }
        return roomstring;
    }
}
