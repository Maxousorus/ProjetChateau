package main.game;

import main.entities.Entity;
import main.utils.Parameters;
import main.visibles.Menu;
import main.visibles.Notification;

public class Boss extends Entity {

    private int[] attacksDamage;
    private double[] attacksChance;
    private String[] attacksName;

    private static final String[] speech = new String[]{
            "I'm the boss !",
            "Prepare to die !",
            "Don't touch me !",
            "You killed my babies !",
            "I'm going to eat you !",
            "I want to know how you taste !",
            "Look around you, it's the last thing you'll ever see !",
            "Do you like my speeches ?",
            "You're just a little guy in the universe !",
            "My castle is not yours !",
            "You're not the only one who's trying to kill me !",
            "Victory is mine !",
            "I'm not going to let you win !",
            "No one can stop me !"
    };

    public Boss() {
        super(0);
        this.setPv(Parameters.PLAYER_MAX_HP * 2);
        this.setDamage((int) (Parameters.PLAYER_HAND_DAMAGE * 2.5));
        this.setName("Great Interstellar Monarch Civodul");

        this.attacksName = new String[]{
                "Gravity Crush",
                "Asteroids Strike",
                "Solar Flamethrower"
        };

        this.attacksDamage = new int[]{
                (int) (this.getDamage() * 1.5),
                (int) (this.getDamage() * 2.5),
                (this.getDamage() * 4)
        };

        this.attacksChance = new double[]{
                1,
                0.80,
                0.45
        };
    }

    public boolean fight(Entity player) {
        new Notification("A dark figure appears in front of you...");
        new Notification("It's the Great Interstellar Monarch Civodul!");
        return false; //TODO : implement fight
    }
}
