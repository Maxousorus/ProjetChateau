package main.visibles;

import main.locations.*;
import main.utils.Parameters;

public class Map {

    private Castle castle;

    private String sidewall = "##";

    private String aroundPassage = "####";

    /**
     * Constructor
     */

    public Map(Castle castle) {
        this.castle = castle;
    }

    private String updownwall() {
        String room = "";
        for(int i = 0; i < Parameters.ROOM_SIZE; i++) {
            room += "#";
        }
        String updownwall = sidewall + " "; //TODO retirer " " quand Map fini
        for (int i = 0; i < Parameters.FLOOR_SIZE; i++) {
            updownwall += room;
            updownwall += " "; //TODO retirer " " quand Map fini
            if (i < Parameters.FLOOR_SIZE - 1){
                updownwall += aroundPassage;
                updownwall += " "; //TODO retirer " " quand Map fini
            }
        }
        updownwall += sidewall;
        return updownwall;
    }
    public void show(int floor) {
        Room[][] rooms = castle.getFloors()[floor].getRooms();
        System.out.println(updownwall());
        System.out.println(updownwall());

        for(int row = 0; row < Parameters.FLOOR_SIZE; row++) {
            for(int nbline = 0; nbline < Parameters.ROOM_HEIGHT; nbline++) {
                String line = "";
                line += sidewall;
                line += " "; //TODO retirer " " quand Map fini
                for(int col = 0; col < Parameters.FLOOR_SIZE; col++) {
                    line += rooms[row][col].toStringList().get(nbline);
                    line += " "; //TODO retirer " " quand Map fini
                    if(col < Parameters.FLOOR_SIZE - 1){
                        line += aroundPassage;
                        line += " "; //TODO retirer " " quand Map fini
                    }
                }
                line += sidewall;
                System.out.println(line);
            }
            System.out.println(updownwall());
            System.out.println(updownwall());
        }
    }
}
