package main.objects;

import main.interfaces.CanBeInRoom;

public class Item implements CanBeInRoom {

    private String name;

    /**
     *
     * @param name
     */
    public Item(String name) {
        this.name = name;
    }

    public Item() {

    }
}
