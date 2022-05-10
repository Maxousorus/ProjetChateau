package main.visibles;

import main.utils.RawConsoleInput;

import java.io.IOException;

public class Menu {

    private String[] options;
    private int selected = 0;

    private String title;

    public Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    public void show() {
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

    public int choose() throws IOException {
        while (true) {
            show();
            if(RawConsoleInput.read(true) == 10) {
                return selected;
            }
            if(RawConsoleInput.read(true) == 65) {

            }
        }
    }
}
