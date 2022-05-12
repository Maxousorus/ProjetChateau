package main.visibles;

import main.entities.Player;
import main.locations.*;
import main.utils.Parameters;

import java.util.ArrayList;

/**
 * This class represents a map.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias
 */
public class Map {

    private Castle castle;

    private String sidewall = "▓▓";

    private String aroundPassage = "▓▓▓▓";

    /**
     * Constructor of the class Map.
     *
     * @param castle the castle
     */

    public Map(Castle castle) {
        this.castle = castle;
    }

    /**
     * This method returns the string of the updownwall.
     *
     * @return the string of the updownwall
     */
    private String updownwall() {
        String room = "";
        for (int i = 0; i < Parameters.ROOM_SIZE; i++) {
            room += "▓";
        }
        String updownwall = sidewall;
        for (int i = 0; i < Parameters.FLOOR_SIZE; i++) {
            updownwall += room;
            if (i < Parameters.FLOOR_SIZE - 1) {
                updownwall += aroundPassage;
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
    public void show(int floor, Player player) {
        show(castle.getFloors()[floor],player);
    }

    /**
     * This method print the map of a floor on console.
     *
     * @param floor the floor to show
     * @see Floor
     */
    public void show(Floor floor, Player player) {
        Room[][] rooms = floor.getRooms();
        System.out.println(updownwall());

        for (int row = 0; row < Parameters.FLOOR_SIZE; row++) {
            for (int nbline = 0; nbline < Parameters.ROOM_HEIGHT; nbline++) {
                String line = "";
                line += sidewall;
                for (int col = 0; col < Parameters.FLOOR_SIZE; col++) {
                    line += rooms[row][col].toStringList(player).get(nbline);
                    if (col < Parameters.FLOOR_SIZE - 1) {
                        Passage[][] h_passages = floor.getHorizontal_passages();
                        if (h_passages[row][col] != null) {
                            ArrayList<String> passage = floor.getHorizontal_passages()[row][col].toStringList(true);
                            if (Parameters.ROOM_HEIGHT % 2 == 1) {
                                if (nbline >= (Parameters.ROOM_HEIGHT / 2) - 1 && nbline <= (Parameters.ROOM_HEIGHT / 2) + 1) {
                                    line += passage.get(nbline - ((Parameters.ROOM_HEIGHT / 2) - 1));
                                } else line += "▓▓▓▓";
                            } else if (Parameters.ROOM_HEIGHT % 2 == 0) {
                                if (nbline >= (Parameters.ROOM_HEIGHT / 2) - 2 && nbline <= (Parameters.ROOM_HEIGHT / 2) + 1) {
                                    line += passage.get(nbline - ((Parameters.ROOM_HEIGHT / 2) - 2));
                                } else line += "▓▓▓▓";
                            } else {
                                line += "▓▓▓▓";
                            }
                        } else {
                            line += aroundPassage;
                        }
                    }
                }
                line += sidewall;
                System.out.println(line);
            }
            if (row < Parameters.FLOOR_SIZE - 1) {
                for (int i = 0; i < 2; i++) {
                    String line = sidewall;
                    Passage[][] v_passages = floor.getVertical_passages();
                    for (int col = 0; col < Parameters.FLOOR_SIZE; col++) {
                        if (v_passages[row][col] == null) {
                            for (int j = 0; j < Parameters.ROOM_SIZE; j++) {
                                line += "▓";
                            }
                        } else {
                            if (Parameters.ROOM_SIZE % 2 == 1) {
                                String nextToPassage = "";
                                for (int j = 0; j < (Parameters.ROOM_SIZE / 2) - 1; j++) {
                                    nextToPassage += "▓";
                                }
                                line += nextToPassage;
                                line += v_passages[row][col].toStringList(false).get(i);
                                line += nextToPassage;
                            } else {
                                String nextToPassage = "";
                                for (int j = 0; j < (Parameters.ROOM_SIZE / 2) - 2; j++) {
                                    nextToPassage += "▓";
                                }
                                line += nextToPassage;
                                line += v_passages[row][col].toStringList(false).get(i);
                                line += nextToPassage;
                            }
                        }
                        if (col < Parameters.FLOOR_SIZE - 1) {
                            line += "▓▓▓▓";
                        } else line += sidewall;
                    }
                    System.out.println(line);
                }
            } else {
                System.out.println(updownwall());
            }
        }
    }
}
