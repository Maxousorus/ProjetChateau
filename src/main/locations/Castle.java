package main.locations;

public class Castle {

    private int nbFloors;
    private Floor[] floors;

    /**
     * Constructor
     * @param nbFloors
     */

    public Castle(int nbFloors) {
        this.nbFloors = nbFloors;
        this.floors = new Floor[nbFloors];
        for (int i = 0; i < nbFloors; i++) {
            this.floors[i] = new Floor();
        }
    }

    public Floor[] getFloors() {
        return floors;
    }
}
