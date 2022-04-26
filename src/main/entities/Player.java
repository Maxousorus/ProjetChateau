package main.entities;

import main.locations.Room;
import main.objects.*;

public class Player extends Entity{

    private Weapon weapon;
    private Room room;

    public Player(Weapon weapon, Room room, int damage, int pv, String nom) {
        super(damage, pv, nom);
        this.weapon = weapon;
        this.room = room;
    }

    public Player(Weapon weapon, Room room, Entity entity) {
        super(entity);
        this.weapon = weapon;
        this.room = room;
    }
}
