package main.locations;

import main.utils.Parameters;

public class Floor {

    private int floorSize = Parameters.FLOOR_SIZE;
    private Room[][] rooms = new Room[floorSize][floorSize];
    private Passage[][] horizontal_passages = new Passage[floorSize -1][floorSize];
    private Passage[][] vertical_passages = new Passage[floorSize][floorSize -1];

    /**
     * Constructor
     */

    public Floor() {
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < 3; j++) {
                rooms[i][j] = new Room();
            }
        }

        for (int i = 0; i < floorSize -1; i++) {
            for (int j = 0; j < floorSize; j++) {
                horizontal_passages[i][j] = new Passage();
                vertical_passages[j][i] = new Passage();
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
