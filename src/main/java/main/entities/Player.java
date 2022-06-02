package main.entities;

import main.objects.Weapon;
import main.options.Parameters;
import main.locations.Castle;
import main.locations.Room;

/**
 * Player class
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Player extends Entity {

    private Weapon weapon;
    private Room room;
    private Room previousRoom;

    private String[] finalAttacksName = new String[]{};
    private int[] finalAttacksDamage = new int[]{};
    private double[] finalAttacksChance = new double[]{};

    /**
     * Player constructor
     * Instanciate a random new player
     */
    public Player() {
        super(0);
        this.setName("Player");
        this.setPv(Parameters.PLAYER_MAX_HP);
        this.setDamage(Parameters.PLAYER_HAND_DAMAGE);
    }

    /**
     * Get final attacks name string [ ].
     *
     * @return the string [ ]
     */
    public String[] getFinalAttacksName() {
        return finalAttacksName;
    }

    /**
     * Get final attacks damage int [ ].
     *
     * @return the int [ ]
     */
    public int[] getFinalAttacksDamage() {
        return finalAttacksDamage;
    }

    /**
     * Get final attacks chance double [ ].
     *
     * @return the double [ ]
     */
    public double[] getFinalAttacksChance() {
        return finalAttacksChance;
    }

    /**
     * This method permits to get the player's weapon
     *
     * @return the player's weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * This method permits to set the player's weapon
     *
     * @param weapon the player's weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * This method permits to get the player's room
     *
     * @return the player's room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * This method permits to set the player's room
     *
     * @param room the player's room
     */
    public void setRoom(Room room) {

        this.previousRoom = this.room;
        this.room = room;

    }

    /**
     * Run away.
     */
    public void runAway(){
        this.setRoom(this.previousRoom);
    }

    /**
     * This method permits to place the player at the enter of the castle.
     *
     * @param castle the castle
     */
    public void spawn(Castle castle) {
        Room[][] rooms  = castle.getFloors()[0].getRooms();
        for (Room[] row : rooms) {
            for (Room room : row) {
                if (room.isSpawn()) {
                    this.room = room;
                    return;
                }
            }
        }
    }

    /**
     * This method show player's stats
     */
    public void showInfos() {
        for (String line : this.stringListInfos()) {
            System.out.println(line);
        }
    }

    /**
     * String list infos string [ ].
     *
     * @return the string [ ]
     */
    public String[] stringListInfos() {
        String line = (Parameters.FRAME_COLOR + "Information about your character :" + "\n");
        if(this.getWeapon() == null) {
            line += ("\033[38;5;46mHP: " + this.getPv() + "/" + Parameters.PLAYER_MAX_HP + Parameters.FRAME_COLOR +
                    " - \033[38;5;196mDMG: " + this.getDamage())+ "\n";
        } else {
            line += ("\033[38;5;46mHP: " + this.getPv() + "/" + Parameters.PLAYER_MAX_HP + Parameters.FRAME_COLOR +
                    " - \033[38;5;51mWeapon: " + this.getWeapon().getName() + Parameters.FRAME_COLOR +
                    " - \033[38;5;196mDMG: " + this.getWeapon().getDamage()) + "\n";
        }
        return line.split("\n");
    }

    /**
     * Init finals attacks.
     */
    public void initFinalsAttacks(){
        this.finalAttacksName = new String[]{
                "Bubble sort Algorithm !",
                "Infinite while loop !",
                "Git push !"
        };

        if(getWeapon() == null) {
            this.finalAttacksDamage = new int[]{
                    (int) (this.getDamage() * 1.75),
                    (int) (this.getDamage() * 3.5),
                    (int) (this.getDamage() * 5)
            };
        } else {
            this.finalAttacksDamage = new int[]{
                    (int) (this.getWeapon().getDamage() * 1.75),
                    (int) (this.getWeapon().getDamage() * 3.5),
                    (int) (this.getWeapon().getDamage() * 5)
            };
        }

        this.finalAttacksChance = new double[]{
                0.95,
                0.80,
                0.45
        };
    }
}
