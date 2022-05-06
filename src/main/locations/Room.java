package main.locations;

import static java.lang.Math.random;
import main.interfaces.CanBeInRoom;
import main.utils.Generate;
import main.utils.Parameters;

import java.util.ArrayList;

/**
 * Room class
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Room {

    private CanBeInRoom roomEvent;
    private boolean visited;
    private boolean upstairs;
    private boolean downstairs;
    private boolean spawn;
    private boolean exit;

    /**
     * Constructor of the Room class
     * Instanciates a random new Room
     */

    public Room() {
        if(random() < Parameters.CHANCE_OF_ITEM_IN_ROOM) {
            //this.roomEvent = Generate.item();
        }else if(random() < Parameters.CHANCE_OF_CHALLENGE_IN_ROOM){
            this.roomEvent = Generate.challenge();
        }else if(random() < Parameters.CHANCE_OF_MONSTER_IN_ROOM){
            this.roomEvent = Generate.monster();
        }else{
            this.roomEvent = null;
        }
        this.visited = true; // TODO true juste pour le test enlever à la fin des tests
        this.upstairs = false;
        this.downstairs = false;
        this.spawn = false;
        this.exit = false;
    }

    /**
     * This method returns if the room has been visited
     * @return true if the room has been visited, false otherwise
     */

    public boolean isVisited() {
        return this.visited;
    }

    /**
     * This method set the room as spawn
     */
    public void setSpawn(){
        this.spawn = true;
    }

    /**
     * This method returns if the room is a spawn
     * @return true if the room is a spawn, false otherwise
     */
    public boolean isSpawn(){
        return spawn;
    }

    /**
     * This method set room as a room with upstairs
     */
    public void setUpStairs(){
        this.upstairs = true;
    }

    /**
     * This method returns if the room is a room with upstairs
     * @return true if the room is a room with upstairs, false otherwise
     */
    public boolean isUpStairs(){
        return upstairs;
    }

    /**
     * This method set the room as a room with downstairs
     */

    public void setDownStairs(){
        this.downstairs = true;
    }

    /**
     * This method returns if the room is a room with downstairs
     * @return true if the room is a room with downstairs, false otherwise
     */
    public boolean isDownStairs(){
        return downstairs;
    }

    /**
     * This method set the room as an exit
     * @param exit true if the room is an exit, false otherwise
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * This method return a list of each line of the visible room like appears in the map.
     * @return a list of each line of the visible room
     */

    public ArrayList<String> toStringList(){
        ArrayList<String> roomstring = new ArrayList<>();
        if(!isVisited()){
            for(int i = 0; i < Parameters.ROOM_SIZE; i++){
                String line = "";
                for(int j = 0; j < Parameters.ROOM_SIZE;j++){
                    line += "#";
                }
                roomstring.add(line);
            }
            return roomstring;
        }
        if(downstairs){
            String l1 = "";
            for(int i = 0; i < Parameters.ROOM_SIZE-3;i++)
                l1 += " ";
            roomstring.add("┐  " + l1);
            roomstring.add("└─┐" + l1);
            roomstring.add("  └" + l1);
            for(int i = 0; i < Parameters.ROOM_HEIGHT-3; i++){
                String line = "";
                for(int j = 0; j < Parameters.ROOM_SIZE;j++)
                    line += " ";
                roomstring.add(line);
            }
            return roomstring;
        }
        if(upstairs){
            for(int i = 0; i < Parameters.ROOM_HEIGHT-3; i++){
                String line = "";
                for(int j = 0; j < Parameters.ROOM_SIZE;j++){
                    line += " ";
                }
                roomstring.add(line);
            }
            String l1 = "";
            for(int i = 0; i < Parameters.ROOM_SIZE-3;i++)
                l1 += " ";

            roomstring.add(l1 + "┐  ");
            roomstring.add(l1 + "└─┐");
            roomstring.add(l1 + "  └");
            return roomstring;
        }
        for(int i = 0; i < Parameters.ROOM_HEIGHT; i++){
            String line = "";
            for(int j = 0; j < Parameters.ROOM_SIZE;j++){
                line += " ";
            }
            roomstring.add(line);
        }
        return roomstring;
    }
}
