package main.objects;

import main.interfaces.CanBeInRoom;
import main.utils.Utils;

public class Item implements CanBeInRoom {

    private String name;
    private String [] tabName = {
            "GodJonas",
            "Tigrou",
            "Dumbo",
            "Grizzly",
            "Poulpi",
            "Chèvre"};



    public Item() {
        this.name = tabName[Utils.randomInt(1,6)];
    }
}
