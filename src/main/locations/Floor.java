package main.locations;

import main.utils.Parameters;

public class Floor {

    private int floorSize = Parameters.FLOOR_SIZE;
    private Room[][] rooms = new Room[floorSize][floorSize];
    private Passage[][] horizontal_passages = new Passage[floorSize][floorSize-1];
    private Passage[][] vertical_passages = new Passage[floorSize-1][floorSize];

    /**
     * Constructor
     */

    public Floor() {
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < 3; j++) {
                rooms[i][j] = new Room();
            }
        }
    }

    /**
     * getRooms method : Get rooms of the Floor
     * @return Room[][]
     */

    public Room[][] getRooms() {
        return rooms;
    }
}
