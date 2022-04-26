package main.challenges;

import main.interfaces.CanBeInRoom;

public abstract class Challenge implements CanBeInRoom {

    private String name;

    public String getName() {
        return name;
    }
}
