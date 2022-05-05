package main.entities;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;
import java.util.ArrayList;

/**
 * Entity class
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Entity implements CanBeInRoom {

    private int damage;
    private int pv;
    private String nom;

    private String [] tabName = {
            "GodJonas",
            "Tigrou",
            "Dumbo",
            "Grizzly",
            "Poulpi",
            "Chèvre"};

    /**
     * Constructor of the class Entity
     * Instantiate a randomw new entity
     */
    public Entity() {
        this.damage = Utils.randomInt(1, 50);
        this.pv = Utils.randomInt(50,100);
        this.nom = tabName[Utils.randomInt(1,6)];
    }

    /**
     * Get the name of the entity
     * @return the name of the entity
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the current health of the entity
     * @return the current health of the entity
     */
    public int getPv() {
        return pv;
    }

    /**
     * Set the current health of the entity
     * @param pv the current health of the entity
     */
    public void setPv(int pv) {
        this.pv = pv;
    }

    /**
     * Get the damage of the entity
     * @return the damage of the entity
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Set the damage of the entity
     * @param damage the damage of the entity
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
