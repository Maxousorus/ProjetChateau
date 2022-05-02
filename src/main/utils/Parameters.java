package main.utils;

public class Parameters {

    //This parameter need to be superior or equals to 2 ( >= 3 is better to play)
    public static final int FLOOR_SIZE = 3;
    //Chance to passage is locked (between 0 and 1 includes)
    public static final double CHANCE_OF_LOCKED_PASSAGE = 0.2;
    //The sum of the next three parameters need to be inferior or equals to 1
    public static final double CHANCE_OF_ITEM_IN_ROOM = 0.3;
    public static final double CHANCE_OF_MONSTER_IN_ROOM = 0.2;
    public static final double CHANCE_OF_CHALLENGE_IN_ROOM = 0.2;
    public static final double CHANCE_OF_CHALLENGE_IS_TRAP = 0.5;
}
