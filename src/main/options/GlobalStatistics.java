package main.options;

import main.utils.Utils;

import java.io.IOException;

/**
 * The type Global statistics.
 */
public class GlobalStatistics extends Statistics{

    private int bestScore;

    /**
     * Instantiates a new Global statistics.
     */
    public GlobalStatistics() {
        super();
        this.bestScore = 0;
    }

    /**
     * Update global statistics.
     *
     * @param gameStatistics the game statistics
     */
    public void updateGlobalStatistics(GameStatistics gameStatistics) {
        if(gameStatistics.getScore() > this.bestScore) {
            this.bestScore = gameStatistics.getScore();
        }
        this.setBossKilled(this.getBossKilled() + gameStatistics.getBossKilled());
        this.setDamageDealt(this.getDamageDealt() + gameStatistics.getDamageDealt());
        this.setDamageTaken(this.getDamageTaken() + gameStatistics.getDamageTaken());
        this.setEnemyKilled(this.getEnemyKilled() + gameStatistics.getEnemyKilled());
        this.setRoomVisited(this.getRoomVisited() + gameStatistics.getRoomVisited());
        this.setSageQuestionSolved(this.getSageQuestionSolved() + gameStatistics.getSageQuestionSolved());
        this.setWeaponsTaken(this.getWeaponsTaken() + gameStatistics.getWeaponsTaken());
        this.setPotionFound(this.getPotionFound() + gameStatistics.getPotionFound());
        this.setTrapFound(this.getTrapFound() + gameStatistics.getTrapFound());
    }

    /**
     * Show global statistics.
     */
    public void showGlobalStatistics() {

        Utils.clearConsole();

        String line = "";
        for (int i = 0; i < "Global Statistics".length(); i++) {
            line += "\u2550";
        }
        String frame = "\u2554\u2550\u2550" + line + "\u2550\u2550\u2557" + "\n" +
                    "\u2551  " + "Global Statistics" + "  \u2551" + "\n" +
                    "\u255a\u2550\u2550" + line + "\u2550\u2550\u255d" + "\n";

        String stats = this.getStatistics();

        String bestScore = Parameters.COLOR_1 + "Best Score : " + Parameters.SELECTED_COLOR + this.bestScore;

        System.out.println(Parameters.FRAME_COLOR + frame + stats + bestScore);

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
