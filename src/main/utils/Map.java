package main.utils;

import main.locations.*;

public class Map {

    private Castle castle;

    /**
     * Constructor
     */

    public Map(Castle castle) {
        this.castle = castle;
    }

    public void show(Floor floor) {
        Room[][] rooms = floor.getRooms();
        System.out.println("## ########### #### ########### #### ########### ##");
        System.out.println("## ########### #### ########### #### ########### ##");
        for(int i = 0; i <= 5; i++){
            System.out.print("## ");

            if(!rooms[0][0].isVisited()) {
                System.out.print("###########");
            } else {
                System.out.print("           ");
            }

            System.out.print(" #### ");

            if(!rooms[0][1].isVisited()) {
                System.out.print("###########");
            } else {
                System.out.print("           ");
            }

            System.out.print(" #### ");

            if(!rooms[0][1].isVisited()) {
                System.out.print("###########");
            } else {
                System.out.print("           ");
            }

            System.out.println(" ##");
        }
    }
}
