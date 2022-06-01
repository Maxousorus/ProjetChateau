package main.entities;

import main.interfaces.CanBeInRoom;
import main.options.Parameters;
import main.utils.Utils;

/**
 * The type Entity.
 */
public class Entity implements CanBeInRoom {

    private int damage;
    private int pv;
    private String name;

    private String[] tabName = {
            "Witch",
            "Slime",
            "Baby Dragon",
            "Troll",
            "Goat",
            "Goblin",
            "Orc",
            "Skeleton",
            "Zombie",
            "Gollum",
            "Giant",
            "Dragon",
            "Vampire",
            "Werewolf",
            "Ghost",
            "Wizard",
            "Warrior",
            "Archer",
            "Priest",
            "Thief",
            "Knight",
            "Mage",
            "Warlock",
            "Bard",
            "Druid",
            "Monk",
            "Paladin",
            "Ranger",
            "Necromancer",
            "Assassin",
            "Berserk",
            "Sorcerer",
            "Ninja",
            "Samurai",
            "Dark Sasuke",
            "GodJonas",
            "Tigrou",
            "Dumbo",
            "Grizzly",
            "Poulpi",
            "Goat"
    } ;


    /**
     * Instantiates a new Entity.
     *
     * @param numberFloor the number floor
     */
    public Entity(int numberFloor) {
        this.damage = damageEntity(numberFloor);
        this.pv = pvEntity(numberFloor);
        this.name = tabName[Utils.randomInt(0,tabName.length-1)];
    }

    private int damageEntity (int numberFloor){
        if(numberFloor <= 10){
            return Utils.randomInt(Parameters.MONSTER_MIN_DAMAGE + numberFloor,Parameters.MONSTER_MIN_DAMAGE + Parameters.MONSTER_DAMAGE_FLOOR_MULTIPLIER * numberFloor);
        }
        else{
            return Utils.randomInt(Parameters.MONSTER_MIN_DAMAGE + 10,Parameters.MONSTER_MIN_DAMAGE + Parameters.MONSTER_DAMAGE_FLOOR_MULTIPLIER * 10);
        }
    }

    private int pvEntity (int numberFloor){
        if(numberFloor <= 10){
            return Utils.randomInt(Parameters.MONSTER_MIN_HEALTH + numberFloor,Parameters.MONSTER_MIN_HEALTH + Parameters.MONSTER_HEALTH_FLOOR_MULTIPLIER * numberFloor);
        }
        else{
            return Utils.randomInt(Parameters.MONSTER_MIN_HEALTH + 10,Parameters.MONSTER_MIN_HEALTH + Parameters.MONSTER_HEALTH_FLOOR_MULTIPLIER * 10);
        }
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
