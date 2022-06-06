package main.visibles;

/**
 * The type Notification.
 */
public class Notification extends Menu{

    /**
     * The constant OK. (only one choice of menu notification)
     */
    public static final String[] OK = {"Press enter"}; // The text of the button.

    /**
     * Instantiates a new Notification.
     *
     * @param title the title
     * @param map   the map
     */
    public Notification(String title, Map map) {
        super(title, OK, map);
    }

    /**
     * Instantiates a new Notification.
     *
     * @param title the title
     */
    public Notification(String title){
        this(title, null);
    }
}
