package main.visibles;

import main.utils.Parameters;

public class Popup {

    private String[] lines;

    public Popup(String... lines) {
        this.lines = lines;
    }

    public void show() {
        int longest = longestLine();
        String topline = ""; //The line to display
        for (int i = 0; i < longest; i++) {
            topline += "\u2550";
        }
        System.out.println(Parameters.FRAME_COLOR + "\u2554\u2550\u2550" + topline + "\u2550\u2550\u2557");
        for (String line : lines) {
            System.out.println("\u2551  " + line + " ".repeat(longest - line.length()) + "  \u2551");
        }
        System.out.println("\u255a\u2550\u2550" + topline + "\u2550\u2550\u255d" + Parameters.RESET_COLOR);
    }

    private int longestLine() {
        int longest = 0;
        for (String line : lines) {
            if (line.length() > longest) {
                longest = line.length();
            }
        }
        return longest;
    }
}
