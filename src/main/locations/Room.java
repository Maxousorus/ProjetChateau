package main.locations;

import static java.lang.Math.random;
import main.interfaces.CanBeInRoom;
import main.interfaces.VisibleInMap;
import main.utils.Generate;
import main.utils.Parameters;

import java.util.ArrayList;

public class Room implements VisibleInMap {

    private CanBeInRoom roomEvent;
    private boolean visited;
    private boolean upstairs;
    private boolean downstairs;
    private boolean spawn;
    private boolean exit;

    /**
     * Constructor
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
     * isVisited method : returns true if the room has been visited
     * @return boolean
     */

    public boolean isVisited() {
        return this.visited;
    }

    public void setSpawn(){
        this.spawn = true;
    }

    public boolean isSpawn(){
        return spawn;
    }

    public void setUpStairs(){
        this.upstairs = true;
    }

    public boolean isUpStairs(){
        return upstairs;
    }

    public void setDownStairs(){
        this.downstairs = true;
    }

    public boolean isDownStairs(){
        return downstairs;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    @Override
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
            roomstring.add("_  " + l1);
            roomstring.add(" |_" + l1);
            roomstring.add("  |" + l1);
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

            roomstring.add(l1 + "_  ");
            roomstring.add(l1 + " |_");
            roomstring.add(l1 + "  |");
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
