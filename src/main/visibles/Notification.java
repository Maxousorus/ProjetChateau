package main.visibles;

/**
 * This class permits to display a notification.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias.
 * @see MenuExtendsofMenu
 */
public class Notification extends Menu{

    /**
     * The constant OK.
     */
    public static final String[] OK = {"Press enter"}; // The text of the button.

    /**
     * Constructor of Notification.
     *
     * @param title Title of the notification.
     * @param map   map of the notification.
     * @see Map
     */
    public Notification(String title, Map map) {
        super(title, OK, map);
    }

    /**
     * Constructor of Notification.
     *
     * @param title Title of the notification.
     */
    public Notification(String title){
        this(title, null);
    }
}
