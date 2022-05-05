package main.visibles;

import main.locations.*;
import main.utils.Parameters;

public class Map {

    private Castle castle;

    private String sidewall = "##";
    private String updownwall = "## ########### #### ########### #### ########### ##"; //TODO retirer les " " quand Map fini

    /**
     * Constructor
     */

    public Map(Castle castle) {
        this.castle = castle;
    }

    public void show(int floor) {
        Room[][] rooms = castle.getFloors()[floor].getRooms();
        System.out.println(updownwall);
        System.out.println(updownwall);

        for(int row = 0; row < Parameters.FLOOR_SIZE; row++) {
            for(int nbline = 0; nbline < Parameters.ROOM_SIZE; nbline++) {
            }
        }
    }
}
