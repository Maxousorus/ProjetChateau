package main.challenges;

import main.interfaces.CanBeInRoom;
import main.utils.Parameters;

import java.lang.reflect.Parameter;

public abstract class Challenge implements CanBeInRoom {

    private String name;

    public String getName() {
        return name;
    }

    public Challenge(){
        if(Parameters.CHANCE_OF_CHALLENGE_IS_TRAP > Math.random()){
        }
    }
}
