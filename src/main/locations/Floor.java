package main.locations;

import main.utils.Parameters;

/**
 * Floor class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Floor {

    private int floorSize = Parameters.FLOOR_SIZE;
    private Room[][] rooms = new Room[floorSize][floorSize];
    private Passage[][] horizontal_passages = new Passage[floorSize][floorSize - 1];
    private Passage[][] vertical_passages = new Passage[floorSize - 1][floorSize];
    private int floorNumber;

    /**
     * Constructor of the Floor class
     * Instantiate all the rooms and passages of the floor.
     */

    public Floor(int floorNumber) {
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < floorSize; j++) {
                rooms[i][j] = new Room(this);
            }
        }
        this.floorNumber = floorNumber;
    }

    /**
     * This method is used to get the floor number.
     * @return floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * This method is used to get the rooms list of the floor.
     *
     * @return rooms list of the floor
     */
    public Room[][] getRooms() {
        return rooms;
    }

    /**
     * This method is used to set the horizontal passages list of the floor.
     *
     * @param horizontal_passages horizontal passages list of the floor
     */
    public void setHorizontal_passage(Passage[][] horizontal_passages) {
        this.horizontal_passages = horizontal_passages;
    }

    /**
     * This method is used to get horizontal passages between rooms.
     * @return horizontal passages list of the floor
     */
    public Passage[][] getHorizontal_passages() {
        return horizontal_passages;
    }

    /**
     * This method is used to set the vertical passages list of the floor.
     *
     * @param vertical_passages vertical passages list of the floor
     */
    public void setVertical_passage(Passage[][] vertical_passages) {
        this.vertical_passages = vertical_passages;
    }

    /**
     * This method is used to get the vertical passages list of the floor.
     * @return vertical passages list of the floor
     */
    public Passage[][] getVertical_passages() {
        return vertical_passages;
    }

    /**
     * This method is used to get the coordinates of a room in the floor.
     * @param room
     * @return coordinates of the room
     */
    public int[] getRoomCoordinates(Room room) {
        int[] coordinates = new int[2];
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < floorSize; j++) {
                if (rooms[i][j] == room) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }
        coordinates[0] = -1;
        coordinates[1] = -1;
        return coordinates;
    }

    /**
     * This method is used to get Passages connected to a room.
     * @param room
     * @return Passages connected to a room.
     */
    public Passage[] getPassageOfRoom(Room room) {
        int roomX = getRoomCoordinates(room)[0];
        int roomY = getRoomCoordinates(room)[1];
        Passage[] passages = new Passage[4];
        if (roomY > 0) {
            passages[0] = horizontal_passages[roomX][roomY-1]; //ouest
        }
        if (roomY < floorSize - 1) {
            passages[1] = horizontal_passages[roomX][roomY]; //est
        }
        if (roomX > 0) {
            passages[2] = vertical_passages[roomX-1][roomY]; //nord
        }
        if (roomX < floorSize - 1) {
            passages[3] = vertical_passages[roomX][roomY]; //sud
        }
        return passages;
    }
}
