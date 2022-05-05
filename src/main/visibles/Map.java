package main.visibles;

import main.locations.*;
import main.utils.Parameters;

/**
 * This class represents a map.
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias
 */
public class Map {

    private Castle castle;

    private String sidewall = "##";

    private String aroundPassage = "####";

    /**
     * Constructor of the class Map.
     * @param castle the castle
     */

    public Map(Castle castle) {
        this.castle = castle;
    }

    /**
     * This method returns the string of the updownwall.
     * @return the string of the updownwall
     */
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

    /**
     * This method print the map of a floor on console.
     *
     * @param floor the index of the floor to show
     */
    public void show(int floor) {
        show(castle.getFloors()[floor]);
    }

    /**
     * This method print the map of a floor on console.
     * @param floor the floor to show
     * @see Floor
     */
    public void show (Floor floor) {
        Room[][] rooms = floor.getRooms();
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
