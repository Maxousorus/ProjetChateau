package main.entities;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;

/**
 * The type Entity.
 */
public class Entity implements CanBeInRoom {

    private int damage;
    private int pv;
    private int numberFloor;
    private String name;

    private String[] tabName = {
            "Witch",
            "Slime",
            "Baby Dragon",
            "Troll",
            "Goat"};


    /**
     * Instantiates a new Entity.
     *
     * @param numberFloor the number floor
     */
    public Entity(int numberFloor) {
        this.damage = damageEntity(numberFloor);
        this.pv = pvEntity(numberFloor);
        this.name = tabName[Utils.randomInt(0,tabName.length-1)];
        this.numberFloor = numberFloor;
    }

    private int damageEntity (int numberFloor){
        numberFloor +=1;
        if(numberFloor <= 10){
            damage = Utils.randomInt(2,5 + (2 * numberFloor));
        }
        else{
            damage = Utils.randomInt(10,30);
        }
        return damage;
    }

    private int pvEntity (int numberFloor){
        numberFloor +=1;
        if(numberFloor <= 10){
            pv = Utils.randomInt(20, 8 * numberFloor);
        }
        else{
            pv = Utils.randomInt(60,90);
        }
        return pv;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getName() {
        return name;
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
