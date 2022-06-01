package main.visibles;

import main.utils.Parameters;
import main.utils.RawConsoleInput;
import main.utils.Utils;

import java.io.IOException;

/**
 * This class permits to display a menu and to get the choice of the user.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias.
 */
public class Menu {

    private final String[] options; //The choices of the menu
    private int selected = 0; //The selected choice of the user
    private final String title; //The title of the menu or the question
    private final Map map; //The map of the game

    private final Popup popup; //The popup of the game

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param options the options
     * @param map     the map
     * @param popup   the popup
     */
    public Menu(String title, String[] options, Map map, Popup popup) {
        this.title = title;
        this.options = options;
        this.map = map;
        this.popup = popup;
    }

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param options the options
     * @param popup   the popup
     */
    public Menu(String title, String[] options, Popup popup) {
        this(title, options, null, popup);
    }

    /**
     * Constructor of the class Menu.
     *
     * @param title   the title of the menu or the question.
     * @param options the choices of the menu.
     * @param map     the map of the floor.
     * @see Map
     */
    public Menu(String title, String[] options,Map map) {
        this(title, options, map, null);
    }

    /**
     * Constructor of the class Menu.
     *
     * @param title   the title of the menu or the question.
     * @param options the choices of the menu.
     */
    public Menu(String title, String[] options) {
        this(title, options, null, null);
    }

    /**
     * Display the menu.
     */
    private void show() {
        String line = ""; //The line to display
        for (int i = 0; i < title.length(); i++) {
            line += "\u2550";
        }
        System.out.print(Parameters.FRAME_COLOR);
        System.out.println("\u2554\u2550\u2550" + line + "\u2550\u2550\u2557");
        System.out.println("\u2551  " + title +"  \u2551"); //Put the title in a frame
        System.out.println("\u255a\u2550\u2550" + line + "\u2550\u2550\u255d");

        String usingColor;
        for (int i = 0; i < options.length; i++) { //For each options
            if(i % 2 == 0) {
                usingColor = Parameters.COLOR_1;
            } else {
                usingColor = Parameters.COLOR_2;
            }
            if (i == selected) {;
                System.out.println(Parameters.SELECTED_COLOR + ">" + options[i]); //Print the selected option in selected color
            } else {
                System.out.println(usingColor + options[i]); //Print the other options
            }
        }
        System.out.print(Parameters.RESET_COLOR);
    }

    /**
     * Get the choice of the user.
     *
     * @return the index in options of the choice.
     * @throws IOException if the user doesn't enter a valid keyboard.
     */
    public int choose() throws IOException {
        while (true) { //While the user doesn't choose an option
            Utils.clearConsole();
            if(popup != null) {
                popup.show();
            }
            if(map != null) {
                System.out.print(Parameters.SELECTED_COLOR);
                System.out.println("Map of the floor n°" + map.getFloor() + " :" + Parameters.RESET_COLOR);
                map.show();
                map.getPlayer().showInfos();
            }
            show(); //Display the menu
            int choice = RawConsoleInput.read(true); //Get the keyboard input
            if(choice == 10 || choice == 13) { // If input is enter
                return selected; //Return the selected option
            }
            if(choice == 57424 || choice == 57425) { //If input is up
                selected = (selected + 1) % options.length; //Change the selected option
            }
            if(choice == 57416 || choice == 57417) { //If input is down
                if(selected == 0) {
                    selected = options.length - 1; //Change the selected option
                }else {
                    selected--; //Change the selected option
                }
            }
        }
    }
}
