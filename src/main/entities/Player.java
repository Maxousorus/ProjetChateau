package main.entities;

import main.locations.Room;
import main.objects.*;

/**
 * Player class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Player extends Entity {

    private Weapon weapon;
    private Room room;

    /**
     * Player constructor
     * Instanciate a random new player
     */
    public Player() {
        super();
        this.weapon = weapon;
        this.room = room;
    }


    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
}
