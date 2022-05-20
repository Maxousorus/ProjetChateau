package main.visibles;

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

    /**
     * Constructor of the class Menu.
     *
     * @param title   the title of the menu or the question.
     * @param options the choices of the menu.
     * @param map     the map of the floor.
     * @see Map
     */
    public Menu(String title, String[] options,Map map) {
        this.title = title;
        this.options = options;
        this.map = map;
    }

    /**
     * Constructor of the class Menu.
     *
     * @param title   the title of the menu or the question.
     * @param options the choices of the menu.
     */
    public Menu(String title, String[] options) {
        this(title, options, null);
    }

    /**
     * Display the menu.
     */
    private void show() {
        String line = ""; //The line to display
        for (int i = 0; i < title.length(); i++) {
            line += "\u2550";
        }
        System.out.println("\u2554\u2550\u2550" + line + "\u2550\u2550\u2557");
        System.out.println("\u2551  " + title +"  \u2551"); //Put the title in a frame
        System.out.println("╚\u2550\u2550" + line + "\u2550\u2550╝");

        for (int i = 0; i < options.length; i++) { //For each options
            if (i == selected) {
                System.out.println("\u001b[107;30m" + options[i] + "\u001b[0m"); //Print the selected option in inverted
            } else {
                System.out.println(options[i]); //Print the other options
            }
        }
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
            if(map != null) {
                System.out.println("Map of the floor n°" + map.getFloor() + " :");
                map.show();
                map.getPlayer().showStats();
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
