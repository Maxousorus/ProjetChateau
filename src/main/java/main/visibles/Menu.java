package main.visibles;

import main.options.Parameters;
import main.utils.RawConsoleInput;
import main.utils.Utils;

import java.io.IOException;

/**
 * The type Menu.
 */
public class Menu {

    private final String[] options; //The choices of the menu.
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
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param options the options
     * @param map     the map
     */
    public Menu(String title, String[] options,Map map) {
        this(title, options, map, null);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param options the options
     */
    public Menu(String title, String[] options) {
        this(title, options, null, null);
    }

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
     * Choose int.
     *
     * @return the int
     */
    public int choose() {
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

            int choice; //Get the keyboard input
            try {
                choice = RawConsoleInput.read(true);
            } catch (IOException e) {
                new Notification("Invalid keyboard input.");
                continue;
            }

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
