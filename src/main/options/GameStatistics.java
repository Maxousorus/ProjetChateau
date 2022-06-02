package main.options;

import main.utils.Utils;

import java.io.IOException;

/**
 * The type Game statistics.
 */
public class GameStatistics extends Statistics{

    private int score;

    /**
     * Instantiates a new Game statistics.
     */
    public GameStatistics() {
        super();
        this.score = 0;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Score calculation.
     */
    public void scoreCalculation() {
        int score = getEnemyKilled() * 5;
        score += getRoomVisited();
        score += getSageQuestionSolved() * 3;
        score += getDamageDealt() - getDamageTaken() / 100;
        score += getWeaponsTaken() * 3;
        score += getPotionFound() * 2;
        score += getTrapFound() * 2;
        if(getBossKilled() > 0) {
            score *= 2;
        }
        this.score = score;
    }

    /**
     * Show game statistics.
     */
    public void showGameStatistics() {
        Utils.clearConsole();
        String line = "";
        for (int i = 0; i < "Game Statistics".length(); i++) {
            line += "\u2550";
        }
        String frame = "\u2554\u2550\u2550" + line + "\u2550\u2550\u2557" + "\n" +
                "\u2551  " + "Game Statistics" + "  \u2551" + "\n" +
                "\u255a\u2550\u2550" + line + "\u2550\u2550\u255d" + "\n";
        String stats = this.getStatistics();

        String score = Parameters.COLOR_1 + "Score : " + Parameters.SELECTED_COLOR + this.score;

        System.out.println(Parameters.FRAME_COLOR + frame + stats + score);

        line = "";
        for (int i = 0; i < "Press Enter to continue !".length(); i++) {
            line += "\u2550";
        }
        System.out.println(Parameters.FRAME_COLOR);
        System.out.println("\u2554\u2550\u2550" + line + "\u2550\u2550\u2557");
        System.out.println("\u2551  " + "Press Enter to continue !" +"  \u2551"); //Put the title in a frame
        System.out.println("\u255a\u2550\u2550" + line + "\u2550\u2550\u255d");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(Parameters.RESET_COLOR);
    }
}
