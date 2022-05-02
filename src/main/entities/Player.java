package main.entities;

import main.locations.Room;
import main.objects.*;

public class Player extends Entity{

    private Weapon weapon;
    //private Room room;

    public Player(Weapon weapon /*Room room*/) {
        super();
        this.weapon = weapon;
        //this.room = room;
    }

    public Player() {

    }
}
