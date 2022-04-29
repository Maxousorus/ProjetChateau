package main.utils;

import main.locations.Castle;
import main.locations.Floor;
import main.locations.Room;

public class Utils {

    public static void setCastleSpawn(Castle castle) {
            Floor stage0 = castle.getFloors()[0];
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            Room[][] rooms = stage0.getRooms();
            int x = random/Parameters.FLOOR_SIZE;
            int y = random%Parameters.FLOOR_SIZE;
            rooms[x][y].setSpawn();
        }

    public static void setCastleStairs(Castle castle){
        Floor[] floors = castle.getFloors();
        for(int floor = 0; floor < castle.getFloors().length-1; floor++){

            int x = 0;
            int y = 0;
            do{
                int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
                x = random/Parameters.FLOOR_SIZE;
                y = random%Parameters.FLOOR_SIZE;
            }while(floors[floor].getRooms()[x][y].isSpawn() || floors[floor].getRooms()[x][y].isUpStairs());

            floors[floor].getRooms()[x][y].setDownStairs();
            floors[floor+1].getRooms()[x][y].setUpStairs();
        }
    }

    public static void setCastleExit(Castle castle){
        Floor[] floors = castle.getFloors();
        int dernieretage = floors.length-1;
        Floor laststage = floors[castle.getFloors().length-1];

        int x = 0;
        int y = 0;
        do{
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            x = random/Parameters.FLOOR_SIZE;
            y = random%Parameters.FLOOR_SIZE;
        }while(floors[dernieretage].getRooms()[x][y].isSpawn() || floors[dernieretage].getRooms()[x][y].isUpStairs());

        laststage.getRooms()[x][y].setExit(true);
    }
}
