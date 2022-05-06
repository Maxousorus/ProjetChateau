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

    /**
     * Constructor of the Floor class
     * Instantiate all the rooms and passages of the floor.
     */

    public Floor() {
        for (int i = 0; i < floorSize; i++) {
            for (int j = 0; j < floorSize; j++) {
                rooms[i][j] = new Room();
            }
        }
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


    public Passage[][] getHorizontal_passages() {
        return horizontal_passages;
    }


    /**
     * This method is used to get the vertical passages list of the floor.
     *
     * @param vertical_passages vertical passages list of the floor
     */
    public void setVertical_passage(Passage[][] vertical_passages) {
        this.vertical_passages = vertical_passages;
    }

    public Passage[][] getVertical_passages() {
        return vertical_passages;
    }
}
