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
     * Instanciate a random new player
     */
    public Player() {
        super();
        this.weapon = weapon;
        this.room = room;
    }


    /**
     * This method permits to get the player's weapon
     * @return the player's weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * This method permits to set the player's weapon
     * @param weapon the player's weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * This method permits to get the player's room
     * @return the player's room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * This method permits to set the player's room
     * @param room the player's room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * This method permits to place the player at the enter of the castle.
     * @param castle the castle
     */
    public void spawn(Castle castle) {
        Room[][] rooms  = castle.getFloors()[0].getRooms();
        for (Room[] row : rooms) {
            for (Room room : row) {
                if (room.isSpawn()) {
                    this.room = room;
                    return;
                }
            }
        }
    }

    /**
     * This method show player's stats
     */
    public void showStats() {
        System.out.println("Information about your character :");
        System.out.println("HP: " + this.getPv() + " - DMG: " + this.getDamage());
    }
}
