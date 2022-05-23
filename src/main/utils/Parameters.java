package main.utils;

/**
 * Class that contains the parameters of the program
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public class Parameters {

    /**
     * The constant FLOOR_SIZE.
     */
//This parameter need to be superior or equals to 2 ( >= 3 is better to play)
    public static final int FLOOR_SIZE = 3;
    /**
     * The constant ROOM_SIZE.
     */
//Size of a room in the map (need to be <3 but 10 is recommended)
    public static final int ROOM_SIZE = 10;
    /**
     * The constant ROOM_HEIGHT.
     */
    public static final int ROOM_HEIGHT = (int) (ROOM_SIZE * 0.6);
    /**
     * The constant CHANCE_OF_LOCKED_PASSAGE.
     */
//Chance to passage is locked (between 0 and 1 includes)
    public static final double CHANCE_OF_LOCKED_PASSAGE = 0.2;
    /**
     * The constant CHANCE_OF_ITEM_IN_ROOM.
     */
//The sum of the next two parameters need to be inferior or equals to 1
    public static final double CHANCE_OF_ITEM_IN_ROOM = 0.3;
    /**
     * The constant CHANCE_OF_MONSTER_IN_ROOM.
     */
    public static final double CHANCE_OF_MONSTER_IN_ROOM = 0.2;

    /**
     * The constant CHANCE_OF_CHALLENGE_IN_PASSAGE.
     */
//Chance for a challenge to be in the passage
    public static final double CHANCE_OF_CHALLENGE_IN_PASSAGE = 0.5;

    /**
     * The constant CHANCE_OF_CHALLENGE_IS_TRAP.
     */
//Chance for a challenge to be a trap (the chance for a challenge to be a sage is 1 - 'theNextParam')
    public static final double CHANCE_OF_CHALLENGE_IS_TRAP = 0.5;

    /**
     * The constant CHANCE_OF_ITEM_IS_WEAPON.
     */
//Chance for a item to be a Weapon (the chance for an item to be a potion is 1 - 'theNextParam')
    public static final double CHANCE_OF_ITEM_IS_WEAPON = 0.5;

    public static final int PLAYER_MAX_HP = 100;

    public static final int PLAYER_HAND_DAMAGE = 5;
}
