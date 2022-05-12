package main.locations;

/**
 * Castle class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Castle {

    private int nbFloors;
    private Floor[] floors;

    /**
     * Constructor of the class Castle
     *
     * @param nbFloors number of floors
     */

    public Castle(int nbFloors) {
        this.nbFloors = nbFloors;
        this.floors = new Floor[nbFloors];
        for (int i = 0; i < nbFloors; i++) {
            this.floors[i] = new Floor(i);
        }
    }

    /**
     * Getter of the list of floors
     *
     * @return the list of floors
     */
    public Floor[] getFloors() {
        return floors;
    }
}
