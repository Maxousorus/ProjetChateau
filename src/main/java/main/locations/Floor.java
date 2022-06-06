package main.locations;

import main.options.Parameters;

/**
 * The type Floor.
 */
public class Floor {

    private final int floorSize = Parameters.FLOOR_SIZE;
    private final Room[][] rooms = new Room[floorSize][floorSize];
    private Passage[][] horizontal_passages = new Passage[floorSize][floorSize - 1];
    private Passage[][] vertical_passages = new Passage[floorSize - 1][floorSize];
    private int floorNumber;

    /**
     * Instantiates a new Floor.
     *
     * @param floorNumber the floor number
     */
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < floorSize; j++) {
                rooms[i][j] = new Room(this);
            }
        }
    }

    /**
     * Gets floor number.
     *
     * @return the floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Get rooms room [ ] [ ].
     *
     * @return the room [ ] [ ]
     */
    public Room[][] getRooms() {
        return rooms;
    }

    /**
     * Sets horizontal passage.
     *
     * @param horizontal_passages the horizontal passages
     */
    public void setHorizontal_passage(Passage[][] horizontal_passages) {
        this.horizontal_passages = horizontal_passages;
    }

    /**
     * Get horizontal passages passage [ ] [ ].
     *
     * @return the passage [ ] [ ]
     */
    public Passage[][] getHorizontal_passages() {
        return horizontal_passages;
    }

    /**
     * Sets vertical passage.
     *
     * @param vertical_passages the vertical passages
     */
    public void setVertical_passage(Passage[][] vertical_passages) {
        this.vertical_passages = vertical_passages;
    }

    /**
     * Get vertical passages passage [ ] [ ].
     *
     * @return the passage [ ] [ ]
     */
    public Passage[][] getVertical_passages() {
        return vertical_passages;
    }

    /**
     * Get room coordinates int [ ].
     *
     * @param room the room
     * @return the int [ ]
     */
    public int[] getRoomCoordinates(Room room) {
        int[] coordinates = new int[2]; // x, y
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < floorSize; j++) {
                if (rooms[i][j] == room) { // if the room is found
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates; // return the coordinates
                }
            }
        }
        coordinates[0] = -1;
        coordinates[1] = -1;
        return coordinates; // return -1 if the room is not found
    }

    /**
     * Get passage of room passage [ ].
     *
     * @param room the room
     * @return the passage [ ]
     */
    public Passage[] getPassageOfRoom(Room room) {
        int roomX = getRoomCoordinates(room)[0]; // get the x coordinate of the room
        int roomY = getRoomCoordinates(room)[1]; // get the y coordinate of the room
        Passage[] passages = new Passage[4];
        if (roomY > 0) { // if the room is not on the left border
            passages[0] = horizontal_passages[roomX][roomY-1]; // get the horizontal passage to the left
        }
        if (roomY < floorSize - 1) { // if the room is not on the right border
            passages[1] = horizontal_passages[roomX][roomY]; // get the horizontal passage to the right
        }
        if (roomX > 0) { // if the room is not on the top border
            passages[2] = vertical_passages[roomX-1][roomY]; // get the vertical passage above
        }
        if (roomX < floorSize - 1) { // if the room is not on the bottom border
            passages[3] = vertical_passages[roomX][roomY]; // get the vertical passage below
        }
        return passages; // return the passages
    }
}
