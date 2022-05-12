package main.visibles;

import main.utils.RawConsoleInput;

import java.io.IOException;

/**
 * This class permits to display a menu and to get the choice of the user.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias
 */
public class Menu {

    private String[] options; //The choices of the menu
    private int selected = 0; //The selected choice of the user
    private String title; //The title of the menu or the question

    /**
     * Constructor of the class Menu.
     *
     * @param title
     * @param options
     */
    public Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    /**
     * Display the menu.
     */
    private void show() {
        String line = ""; //The line to display
        for (int i = 0; i < title.length(); i++) {
            line += "═";
        }
        System.out.println("╔══" + line + "══╗");
        System.out.println("║  " + title +"  ║"); //Put the title in a frame
        System.out.println("╚══" + line + "══╝");

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
     * @throws IOException
     */
    public int choose() throws IOException {
        while (true) { //While the user doesn't choose an option
            show(); //Display the menu
            int choice = RawConsoleInput.read(true); //Get the keyboard input
            if(choice == 10 || choice == 13) { // If input is enter
                return selected;
            }
            if(choice == 57424 || choice == 57425) { //If input is up
                selected = (selected + 1) % options.length;
            }
            if(choice == 57416 || choice == 57417) { //If input is down
                if(selected == 0) {
                    selected = options.length - 1;
                }else {
                    selected--;
                }
            }
        }
    }
}
