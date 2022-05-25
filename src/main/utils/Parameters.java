package main.utils;

import main.visibles.Menu;
import main.visibles.Notification;

import java.io.IOException;

/**
 * The type Parameters.
 */
public class Parameters {

    /**
     * The constant COLOR_1.
     */
//Color Parameters
    public static final String COLOR_1 = "\033[38;5;49m";
    /**
     * The constant COLOR_2.
     */
    public static final String COLOR_2 = "\033[38;5;42m";
    /**
     * The constant SELECTED_COLOR.
     */
    public static final String SELECTED_COLOR = "\033[38;5;208m";
    /**
     * The constant MAP_COLOR.
     */
    public static final String MAP_COLOR = "\033[48;5;13m";
    /**
     * The constant FRAME_COLOR.
     */
    public static final String FRAME_COLOR = "\033[38;5;226m";
    /**
     * The constant RESET_COLOR.
     */
    public static final String RESET_COLOR = "\033[0m";


    /**
     * The constant FLOOR_SIZE.
     */
//Castle parameters
    public static int FLOOR_SIZE;
    /**
     * The constant ROOM_SIZE.
     */
    public static int ROOM_SIZE;
    /**
     * The constant ROOM_HEIGHT.
     */
    public static int ROOM_HEIGHT;

    /**
     * The constant CHANCE_OF_LOCKED_PASSAGE.
     */
//Passage parameters
    public static double CHANCE_OF_LOCKED_PASSAGE;
    /**
     * The constant CHANCE_OF_CHALLENGE_IN_PASSAGE.
     */
    public static double CHANCE_OF_CHALLENGE_IN_PASSAGE;
    /**
     * The constant CHANCE_OF_CHALLENGE_IS_TRAP.
     */
    public static double CHANCE_OF_CHALLENGE_IS_TRAP;

    //Trap parameters at Ground Floor

    public static int TRAP_MIN_DAMAGE;
    public static int TRAP_MAX_DAMAGE;

    /**
     * The constant CHANCE_OF_ITEM_IN_ROOM.
     */
//Room parameters
    public static double CHANCE_OF_ITEM_IN_ROOM;
    /**
     * The constant CHANCE_OF_ITEM_IS_WEAPON.
     */
    public static double CHANCE_OF_ITEM_IS_WEAPON;
    /**
     * The constant CHANCE_OF_MONSTER_IN_ROOM.
     */
    public static double CHANCE_OF_MONSTER_IN_ROOM;

    /**
     * The constant PLAYER_MAX_HP.
     */
//Player parameters
    public static int PLAYER_MAX_HP;
    /**
     * The constant PLAYER_HAND_DAMAGE.
     */
    public static int PLAYER_HAND_DAMAGE;

    //Boss parameters

    public static int BOSS_MAX_HP;
    public static int BOSS_FIRST_ATTACK_DAMAGE;

    /**
     * Init parameters.
     */
    public static void initParameters() { //Recommended Parameters
        //Castle Parameters
        FLOOR_SIZE = 3;
        ROOM_SIZE = 10;
        ROOM_HEIGHT = (int) (ROOM_SIZE * 0.6);
        //Passage Parameters
        CHANCE_OF_LOCKED_PASSAGE = 0.2;
        CHANCE_OF_CHALLENGE_IN_PASSAGE = 0.5;
        CHANCE_OF_CHALLENGE_IS_TRAP = 0.7;
        //Trap Parameters at Ground Floor
        TRAP_MIN_DAMAGE = 1;
        TRAP_MAX_DAMAGE = 3;
        //Room Parameters
        CHANCE_OF_ITEM_IN_ROOM = 0.45;
        CHANCE_OF_MONSTER_IN_ROOM = 0.45;
        CHANCE_OF_ITEM_IS_WEAPON = 0.5;
        //Player Parameters
        PLAYER_MAX_HP = 100;
        PLAYER_HAND_DAMAGE = 5;
        //Boss Parameters
        BOSS_MAX_HP = 250;
        BOSS_FIRST_ATTACK_DAMAGE = 12;
    }

    private static void showParameters() {
        Utils.clearConsole();
        System.out.println(COLOR_1);
        System.out.println("\u2550\u2550\u2550   Castle Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_1 + "Floor Size : " + Parameters.SELECTED_COLOR + FLOOR_SIZE);
        System.out.println(COLOR_1 + "Room Size : " + Parameters.SELECTED_COLOR + ROOM_SIZE);
        System.out.println(COLOR_1 + "Room Height : " + Parameters.SELECTED_COLOR + ROOM_HEIGHT);

        System.out.println(COLOR_2);
        System.out.println("\u2550\u2550\u2550   Passage Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_2 + "Chance of Locked Passage : " + Parameters.SELECTED_COLOR + (int)  (CHANCE_OF_LOCKED_PASSAGE*100) + "%");
        System.out.println(COLOR_2 + "Chance of Challenge in Passage : " + Parameters.SELECTED_COLOR +(int)  (CHANCE_OF_CHALLENGE_IN_PASSAGE*100) + "%");
        System.out.println(COLOR_2 + "Chance of Challenge is Trap : " + Parameters.SELECTED_COLOR +(int)  (CHANCE_OF_CHALLENGE_IS_TRAP*100) + "%");
        System.out.println(COLOR_2 + "Chance of Challenge is Sage : " + Parameters.SELECTED_COLOR +(int)  ((1 - CHANCE_OF_CHALLENGE_IS_TRAP)*100) + "%");

        System.out.println(COLOR_1);
        System.out.println("\u2550\u2550\u2550   Trap Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_1 + "Trap Minimum Damage at ground floor : " + Parameters.SELECTED_COLOR + TRAP_MIN_DAMAGE);
        System.out.println(COLOR_1 + "Trap Maximum Damage at ground floor : " + Parameters.SELECTED_COLOR  + TRAP_MAX_DAMAGE);

        System.out.println(COLOR_2);
        System.out.println("\u2550\u2550\u2550   Room Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_2 + "Chance of Item in Room : " + Parameters.SELECTED_COLOR +(int)  (CHANCE_OF_ITEM_IN_ROOM*100) + "%");
        System.out.println(COLOR_2 + "Chance of Monster in Room : " + Parameters.SELECTED_COLOR +(int)  (CHANCE_OF_MONSTER_IN_ROOM*100) + "%");
        System.out.println(COLOR_2 + "Chance of Item is Weapon : " + Parameters.SELECTED_COLOR +(int)  (CHANCE_OF_ITEM_IS_WEAPON*100) + "%");
        System.out.println(COLOR_2 + "Chance of Item is Potion : " + Parameters.SELECTED_COLOR +(int) ((1 - CHANCE_OF_ITEM_IS_WEAPON)*100) + "%");

        System.out.println(COLOR_1);
        System.out.println("\u2550\u2550\u2550   Player Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_1 + "Player Max HP : " + Parameters.SELECTED_COLOR + PLAYER_MAX_HP);
        System.out.println(COLOR_1 + "Player Hand Damage : " + Parameters.SELECTED_COLOR + PLAYER_HAND_DAMAGE);

        System.out.println(COLOR_2);
        System.out.println("\u2550\u2550\u2550   Boss Parameters   \u2550\u2550\u2550");
        System.out.println(COLOR_2 + "Boss Max HP : " + Parameters.SELECTED_COLOR + BOSS_MAX_HP);
        System.out.println(COLOR_2 + "Boss First Attack Damage : " + Parameters.SELECTED_COLOR + BOSS_FIRST_ATTACK_DAMAGE);


        System.out.println(FRAME_COLOR); //Reset Color
        String line = ""; //The line to display
        for (int i = 0; i < "Press Enter to continue !".length(); i++) {
            line += "\u2550";
        }
        System.out.println("\u2554\u2550\u2550" + line + "\u2550\u2550\u2557");
        System.out.println("\u2551  " + "Press Enter to continue !" +"  \u2551"); //Put the title in a frame
        System.out.println("\u255a\u2550\u2550" + line + "\u2550\u2550\u255d");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(RESET_COLOR);
    }

    /**
     * Parameters menu.
     *
     * @throws IOException the io exception
     */
    public static void parametersMenu() throws IOException {
        Menu parametersMenu = new Menu("Parameters Categories :", new String[]{"Player Parameters", "Castle Parameters", "Passage Parameters",
         "Trap Parameters","Room Parameters","Boss Parameters","Show Parameters","Reset Parameters","Back"});
        boolean back = false;

        while(!back) {
            switch (parametersMenu.choose()) {
                case 0 -> playerParametersMenu();
                case 1 -> castleParametersMenu();
                case 2 -> passageParametersMenu();
                case 3 -> trapParametersMenu();
                case 4 -> roomParametersMenu();
                case 5 -> bossParameters();
                case 6 -> showParameters();
                case 7 -> {
                    initParameters();
                    new Notification("Parameters Reset !").choose();
                }
                case 8 -> back = true;
            }
        }
    }

    private static void bossParameters() throws IOException {
        Menu bossParametersMenu = new Menu("Boss Parameters :", new String[]{"Boss Max HP", "Boss First Attack Damage","Back"});
        boolean back = false;
        while(!back) {
            switch (bossParametersMenu.choose()) {
                case 0 -> BOSS_MAX_HP = changeParameter("Boss Max HP", BOSS_MAX_HP, 10 , 100, PLAYER_MAX_HP, PLAYER_MAX_HP*20);
                case 1 -> BOSS_FIRST_ATTACK_DAMAGE = changeParameter("Boss First Attack Damage", BOSS_FIRST_ATTACK_DAMAGE, 1, 10, PLAYER_HAND_DAMAGE, PLAYER_MAX_HP/2);
                case 2 -> back = true;
            }
        }
    }


    private static void playerParametersMenu() throws IOException {
        Menu playerParametersMenu = new Menu("Player Parameters :", new String[]{"Max HP", "Hand Damage", "Back"});
        boolean back = false;
        while(!back) {
            switch (playerParametersMenu.choose()) {
                case 0 -> PLAYER_MAX_HP = changeParameter("Max HP", PLAYER_MAX_HP, 10, 50, 10, 500);
                case 1 -> PLAYER_HAND_DAMAGE = changeParameter("Hand Damage", PLAYER_HAND_DAMAGE, 1, 3 , 2, 15);
                case 2 -> back = true;
            }
        }
    }
    private static void castleParametersMenu() throws IOException {
        Menu castleParametersMenu = new Menu("Castle Parameters :", new String[]{"Floor Size", "Room Size", "Back"});
        boolean back = false;
        while(!back) {
            switch (castleParametersMenu.choose()) {
                case 0 -> FLOOR_SIZE = changeParameter("Floor Size", FLOOR_SIZE, 1, 10, 2, 6);
                case 1 -> {
                    ROOM_SIZE = changeParameter("Room Size", ROOM_SIZE, 1, 10, 4, 20);
                    ROOM_HEIGHT = (int) (ROOM_SIZE * 0.6);
                }
                case 2 -> back = true;
            }
        }
    }
    private static void passageParametersMenu() throws IOException {
        Menu passageParametersMenu = new Menu("Passage Parameters :", new String[]{"Chance of locked passage", "Chance of Challenge in Passage"
                , "Chance of Challenge is Trap", "Back"});
        boolean back = false;
        while(!back) {
            switch (passageParametersMenu.choose()) {
                case 0 -> CHANCE_OF_LOCKED_PASSAGE = changeParameter("Chance of locked passage", CHANCE_OF_LOCKED_PASSAGE);
                case 1 -> CHANCE_OF_CHALLENGE_IN_PASSAGE = changeParameter("Chance of Challenge in Passage", CHANCE_OF_CHALLENGE_IN_PASSAGE);
                case 2 -> CHANCE_OF_CHALLENGE_IS_TRAP = changeParameter("Chance of Challenge is Trap", CHANCE_OF_CHALLENGE_IS_TRAP);
                case 3 -> back = true;
            }
        }

    }
    private static void trapParametersMenu() throws IOException {
        Menu trapParametersMenu = new Menu("Trap Parameters :", new String[]{"Trap Minimum Damage", "Trap Maximum Damage", "Back"});
        boolean back = false;
        while(!back) {
            switch (trapParametersMenu.choose()) {
                case 0 -> TRAP_MIN_DAMAGE = changeParameter("Trap Minimum Damage", TRAP_MIN_DAMAGE, 1, 10, 1, 10);
                case 1 -> TRAP_MAX_DAMAGE = changeParameter("Trap Maximum Damage", TRAP_MAX_DAMAGE, 1, 10, 1, 10);
                case 2 -> back = true;
            }
        }
    }
    private static void roomParametersMenu() throws IOException {
        Menu roomParametersMenu = new Menu("Room Parameters :", new String[]{"Chance of Item in room", "Chance of Monster in room",
                "Chance of Item is a Weapon", "Back"});
        boolean back = false;
        while(!back) {
            switch (roomParametersMenu.choose()) {
                case 0 -> {
                    CHANCE_OF_ITEM_IN_ROOM = changeParameter("Chance of Item in room", CHANCE_OF_ITEM_IN_ROOM);
                    if(CHANCE_OF_ITEM_IN_ROOM + CHANCE_OF_MONSTER_IN_ROOM > 1) {
                        CHANCE_OF_MONSTER_IN_ROOM = 1 - CHANCE_OF_ITEM_IN_ROOM;
                        new Notification("The chance of Item in Room is too high so Chance of Monster in room is now " + (int) (CHANCE_OF_MONSTER_IN_ROOM*100)).choose();
                    }
                }
                case 1 -> {
                    CHANCE_OF_MONSTER_IN_ROOM = changeParameter("Chance of Monster in room", CHANCE_OF_MONSTER_IN_ROOM);
                    if(CHANCE_OF_ITEM_IN_ROOM + CHANCE_OF_MONSTER_IN_ROOM > 1) {
                        CHANCE_OF_ITEM_IN_ROOM = 1 - CHANCE_OF_MONSTER_IN_ROOM;
                        new Notification("The chance of Monster in Room is too high so Chance of Item in room is now " + (int) (CHANCE_OF_ITEM_IN_ROOM*100) + "%").choose();
                    }
                }
                case 2 -> CHANCE_OF_ITEM_IS_WEAPON = changeParameter("Chance of Item is a Weapon", CHANCE_OF_ITEM_IS_WEAPON);
                case 3 -> back = true;
            }
        }

    }
    private static double changeParameter(String parameterName, double beforeValue) throws IOException {
        int valueinpercent = changeParameter(parameterName + " (percentage)", (int) (beforeValue * 100), 1,10, 0, 100);
        return valueinpercent / 100.0;
    }
    private static int changeParameter(String paramName, int beforeValue, int little, int big, int min, int max) throws IOException {
        int newValue = beforeValue;
        while(true) {
            Menu changeParameter = new Menu(paramName + " : " + newValue,
                    new String[]{"-" + big, "-" + little, "+" + little, "+" + big, "Validate", "Back"});
            switch (changeParameter.choose()) {
                case 0 -> newValue -= big;
                case 1 -> newValue -= little;
                case 2 -> newValue += little;
                case 3 -> newValue += big;
                case 4 -> {return newValue;}
                case 5 -> {return beforeValue;}
            }
            if (newValue < min) {
                newValue = min;
            }
            if (newValue > max) {
                newValue = max;
            }
        }
    }
}
