package main.game;

import main.entities.Entity;
import main.entities.Player;
import main.utils.Parameters;
import main.utils.Utils;
import main.visibles.Menu;
import main.visibles.Notification;
import main.visibles.Popup;

import java.io.IOException;

public class Boss extends Entity {

    private int[] attacksDamage;
    private double[] attacksChance;
    private String[] attacksName;

    private int maxPv;

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
        this.maxPv = this.getPv();
        this.setDamage((int) (Parameters.PLAYER_HAND_DAMAGE * 2.5));
        this.setName("Great Interstellar Monarch Civodul");

        this.attacksName = new String[]{
                "Gravity Crush",
                "Asteroids Strike",
                "Solar Flamethrower"
        };

        this.attacksDamage = new int[]{
                (int) (this.getDamage() * 1),
                (int) (this.getDamage() * 2),
                (int) (this.getDamage() * 3.5)
        };

        this.attacksChance = new double[]{
                1,
                0.80,
                0.40
        };
    }

    public String[] stringListInfos() {
        String[] info = new String[]{
                Parameters.FRAME_COLOR + this.getName() + " : ",
                "\033[38;5;46mHP: " + this.getPv() + "/" + this.maxPv + "\033[0m",
        };
        return info;
    }


    public boolean fight(Player player) throws IOException { //return true if the player won, false if the boss won
        new Notification("A dark figure appears in front of you...");
        new Notification("It's the Great Interstellar Monarch Civodul!");

        String[] possibleAttacks = new String[player.getFinalAttacksName().length+1];
        String[] possibleAttacksDesc = new String[player.getFinalAttacksName().length+1];
        if(player.getWeapon() == null) {
            possibleAttacks[0] = "Hand punch !";
            possibleAttacksDesc[0] = "Hand punch ! (DMG : " + player.getDamage() + " | CHANCE : 100%)";
        } else {
            possibleAttacks[0] = player.getWeapon().getName() + " Slash !";
            possibleAttacksDesc[0] = player.getWeapon().getName() + " Slash ! (DMG : " + player.getWeapon().getDamage() + " | CHANCE : 100%)";
        }

        for(int i = 0; i < player.getFinalAttacksName().length; i++) {
            possibleAttacks[i+1] = player.getFinalAttacksName()[i];
            possibleAttacksDesc[i + 1] = player.getFinalAttacksName()[i] + " (DMG : " + player.getFinalAttacksDamage()[i] + " | CHANCE : " + (int) (player.getFinalAttacksChance()[i] * 100) + "%)";
        }

        //Add strings tabs stringListInfo() and player.stringListInfo()
        String[] infos;

        while(true){

            infos = new String[this.stringListInfos().length + player.stringListInfos().length];
            for(int i = 0; i < this.stringListInfos().length; i++) {
                infos[i] = this.stringListInfos()[i];
            }
            for(int i = 0; i < player.stringListInfos().length; i++) {
                infos[i + this.stringListInfos().length] = player.stringListInfos()[i];
            }

            Menu menuAttack = new Menu("It's time to attack him ! What will you do ?", possibleAttacksDesc, new Popup(infos,false));
            int attack = menuAttack.choose();

            if(attack == 0) {
                if(player.getWeapon() == null) {
                    new Notification("You punch the Great Interstellar Monarch Civodul with your hand for " + player.getDamage() + " damages !").choose();
                    this.setPv(this.getPv() - player.getDamage());
                } else {
                    new Notification("You slash the Great Interstellar Monarch Civodul with your " + player.getWeapon().getName() +
                            " for " + player.getWeapon().getDamage() + " damages !").choose();
                    this.setPv(this.getPv() - player.getWeapon().getDamage());
                }
            } else if(Math.random() <= player.getFinalAttacksChance()[attack-1]) {
                this.setPv(this.getPv() - player.getFinalAttacksDamage()[attack-1]);
                new Notification("You hit the Great Interstellar Monarch Civodul for " + player.getFinalAttacksDamage()[attack-1] + " damages !").choose();
            } else {
                new Notification("You missed the Great Interstellar Monarch Civodul !").choose();
            }

            if(this.getPv() <= 0) {
                new Notification("You killed the Great Interstellar Monarch Civodul !").choose();
                return true; // Player won
            } else {
                int bossAttack = Utils.randomInt(0,3);
                new Notification("The Great Interstellar Monarch Civodul prepare to throw you a " + this.attacksName[bossAttack] + " !").choose();
                if(Math.random() <= this.attacksChance[bossAttack]) {
                    player.setPv(player.getPv() - this.attacksDamage[bossAttack]);
                    new Notification("The Great Interstellar Monarch Civodul hit you for " + this.attacksDamage[bossAttack] + " damages !").choose();
                } else {
                    new Notification("The Great Interstellar Monarch Civodul missed you !").choose();
                }
            }

            if(player.getPv() <= 0) {
                new Notification("You died !").choose();
                return false; // Player lost
            }
        }
    }
}
