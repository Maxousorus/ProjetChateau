package main.visibles;

import main.utils.RawConsoleInput;

import java.io.IOException;

/**
 * This class permits to display a menu and to get the choice of the user.
 *
 * @author BOUDIER Maxime; BAYEN MAXIME; FOURNIER Victor; DOSSA Josias
 */
public class Menu {

    private String[] options;
    private int selected = 0;

    private String title;

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
        String line = "";
        for (int i = 0; i < title.length(); i++) {
            line += "═";
        }
        System.out.println("╔══" + line + "══╗");
        System.out.println("║  " + title +"  ║");
        System.out.println("╚══" + line + "══╝");

        for (int i = 0; i < options.length; i++) {
            if (i == selected) {
                System.out.println("\u001b[107;30m" + options[i] + "\u001b[0m");
            } else {
                System.out.println(options[i]);
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
        while (true) {
            show();
            int choice = RawConsoleInput.read(true);
            if(choice == 10 || choice == 13) { // enter
                return selected;
            }
            if(choice == 57424 || choice == 57425) { // up
                selected = (selected + 1) % options.length;
            }
            if(choice == 57416 || choice == 57417) { // down
                if(selected == 0) {
                    selected = options.length - 1;
                }else {
                    selected--;
                }
            }
        }
    }
}
