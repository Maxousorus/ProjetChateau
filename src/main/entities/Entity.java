package main.entities;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;

public class Entity implements CanBeInRoom {

    private int damage;
    private int pv;
    private String nom;

    private String[] tabName = {
            "GodJonas",
            "Tigrou",
            "Dumbo",
            "Grizzly",
            "Poulpi",
            "Chèvre"};


    public Entity() {
        this.damage = Utils.randomInt(1, 50);
        this.pv = Utils.randomInt(50,100);
        this.nom = tabName[Utils.randomInt(1,6)];
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
