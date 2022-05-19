package main.locations;

import main.utils.Parameters;

/**
 * This class represents a floor in the castle.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public class Floor {

    private final int floorSize = Parameters.FLOOR_SIZE;
    private final Room[][] rooms = new Room[floorSize][floorSize];
    private Passage[][] horizontal_passages = new Passage[floorSize][floorSize - 1];
    private Passage[][] vertical_passages = new Passage[floorSize - 1][floorSize];
    private final int floorNumber;

    /**
     * Constructor of the Floor class.
     * Instantiate all the rooms and passages of the floor.
     *
     * @param floorNumber the floor number
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
     *
     * @return floor number.
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * This method is used to get the rooms list of the floor.
     *
     * @return rooms list of the floor.
     * @see Room
     */
    public Room[][] getRooms() {
        return rooms;
    }

    /**
     * This method is used to set the horizontal passages list of the floor.
     *
     * @param horizontal_passages horizontal passages list of the floor.
     * @see Passage
     */
    public void setHorizontal_passage(Passage[][] horizontal_passages) {
        this.horizontal_passages = horizontal_passages;
    }

    /**
     * This method is used to get horizontal passages between rooms.
     *
     * @return horizontal passages list of the floor.
     * @see Passage
     */
    public Passage[][] getHorizontal_passages() {
        return horizontal_passages;
    }

    /**
     * This method is used to set the vertical passages list of the floor.
     *
     * @param vertical_passages vertical passages list of the floor.
     * @see Passage
     */
    public void setVertical_passage(Passage[][] vertical_passages) {
        this.vertical_passages = vertical_passages;
    }

    /**
     * This method is used to get the vertical passages list of the floor.
     *
     * @return vertical passages list of the floor.
     * @see Passage
     */
    public Passage[][] getVertical_passages() {
        return vertical_passages;
    }

    /**
     * This method is used to get the coordinates of a room in the floor.
     *
     * @param room room to get coordinates.
     * @return coordinates of the room.
     * @see Room
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
     * This method is used to get Passages connected to a room.
     *
     * @param room room to get passages.
     * @return Passages connected to a room.
     * @see , PassageRoom
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
