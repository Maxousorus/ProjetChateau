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
                System.out.println(roomsLine(rooms[row],nbline));
            }
            printBetweenRows(row);
        }
    }

    private String roomsLine(Room[] rooms,int nbline) {
        String line = sidewall;
        for(int room = 0; room < rooms.length; room++) {
            System.out.println(" "); //TODO retirer cette ligne quand Map fini
            if(rooms[room].isVisited()) {
                for(int i = 0; i < Parameters.ROOM_SIZE; i++) {
                    line += " ";
                }
            }
            else {
                for(int i = 0; i < Parameters.ROOM_SIZE; i++) {
                    line += "#";
                }
            }
            if(room < rooms.length - 1) {
                printPassage(); //TODO printPasssage
            }
        }
        line += sidewall;
        return line;
    }

    private void printBetweenRows(int upperrow) {
        int lowerrow = upperrow + 1;
        String line = sidewall;
        for(int room = 0; room < Parameters.FLOOR_SIZE; room++) {

        }
    }

    private void printPassage() {

    }
}
