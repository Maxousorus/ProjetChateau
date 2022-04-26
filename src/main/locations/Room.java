package main.locations;

import static java.lang.Math.random;
import main.interfaces.CanBeInRoom;
import main.objects.Item;
import main.utils.Generate;
import main.utils.Parameters;

public class Room {

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
            this.roomEvent = Generate.item();
        }else if(random() < Parameters.CHANCE_OF_CHALLENGE_IN_ROOM){
            this.roomEvent = Generate.challenge(); //TODO Generate.challenge()
        }else if(random() < Parameters.CHANCE_OF_MONSTER_IN_ROOM){
            this.roomEvent = Generate.monster(); //TODO Generate.monster()
        }else{
            this.roomEvent = null;
        }
        this.visited = false;
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
}
