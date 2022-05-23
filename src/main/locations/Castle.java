package main.locations;

/**
 * Castle class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Castle {

    private final int nbFloors;
    private final Floor[] floors;

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

    public Floor getFloorOfPassage(Passage passage) {
        for (int i = 0; i < nbFloors; i++) {
            for (int j = 0; j < floors[i].getHorizontal_passages().length; j++) {
                for (int k = 0; k < floors[i].getHorizontal_passages()[j].length; k++) {
                    if (floors[i].getHorizontal_passages()[j][k] == passage) {
                        return floors[i];
                    }
                }
            }
            for (int j = 0; j < floors[i].getVertical_passages().length; j++) {
                for (int k = 0; k < floors[i].getVertical_passages()[j].length; k++) {
                    if (floors[i].getVertical_passages()[j][k] == passage) {
                        return floors[i];
                    }
                }
            }
        }
        return null;
    }
}
