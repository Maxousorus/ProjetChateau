package main.entities;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;

/**
 * The type Entity.
 */
public abstract class Entity implements CanBeInRoom {

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


    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        this.damage = Utils.randomInt(1, 50);
        this.pv = Utils.randomInt(50,100);
        this.nom = tabName[Utils.randomInt(1,6)];
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets pv.
     *
     * @return the pv
     */
    public int getPv() {
        return pv;
    }

    /**
     * Sets pv.
     *
     * @param pv the pv
     */
    public void setPv(int pv) {
        this.pv = pv;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
