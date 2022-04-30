package main.entities;

import main.interfaces.CanBeInRoom;

public class Entity implements CanBeInRoom {

    private int damage;
    private int pv;
    private String nom;

    public Entity(int damage, int pv, String nom) {
        this.damage = damage;
        this.pv = pv;
        this.nom = nom;
    }

    public Entity() {

    }

    public Entity(Entity entity) {
    }
}
