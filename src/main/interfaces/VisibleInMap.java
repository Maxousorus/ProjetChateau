package main.interfaces;

import java.util.ArrayList;

/**
 * Interface for objects that can be seen in the map.
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public interface VisibleInMap {
    /**
     * Get the list of line of the object to see it on map.
     * @return The list of line of the object to see it on map.
     */
    public ArrayList<String> toStringList();
}
