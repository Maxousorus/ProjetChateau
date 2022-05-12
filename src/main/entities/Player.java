package main.entities;

import main.locations.Castle;
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
     * Instanciate new player
     */

    public Player() {
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void spawn(Castle castle) {
        Room[][] rooms  = castle.getFloors()[0].getRooms();
        for(int x = 0; x < rooms.length; x++) {
            for(int y = 0; y < rooms[x].length; y++) {
                if(rooms[x][y].isSpawn()) {
                    this.room = rooms[x][y];
                    return;
                }
            }
        }
    }

    public void showStats() {
        System.out.println("Information about your character :");
        System.out.println("HP: " + this.getPv() + " - DMG: " + this.getDamage());
    }
}
