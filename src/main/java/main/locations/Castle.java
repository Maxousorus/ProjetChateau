package main.locations;

/**
 * The type Castle.
 */
public class Castle {

    private final int nbFloors;
    private final Floor[] floors;

    /**
     * Instantiates a new Castle.
     *
     * @param nbFloors the nb floors
     */
    public Castle(int nbFloors) {
        this.nbFloors = nbFloors;
        this.floors = new Floor[nbFloors];
        for (int i = 0; i < nbFloors; i++) {
            this.floors[i] = new Floor(i);
        }
    }

    /**
     * Get floors floor [ ].
     *
     * @return the floor [ ]
     */
    public Floor[] getFloors() {
        return floors;
    }
}
