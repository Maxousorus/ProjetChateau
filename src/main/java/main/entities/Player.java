package main.entities;

import main.objects.Weapon;
import main.options.Parameters;
import main.locations.Castle;
import main.locations.Room;

/**
 * The type Player.
 */
public class Player extends Entity {

    private Weapon weapon;
    private Room room;
    private Room previousRoom;

    private String[] finalAttacksName = new String[]{};
    private int[] finalAttacksDamage = new int[]{};
    private double[] finalAttacksChance = new double[]{};

    /**
     * Instantiates a new Player.
     */
    public Player() {
        super(0);
        this.setName("Player");
        this.setPv(Parameters.PLAYER_MAX_HP);
        this.setDamage(Parameters.PLAYER_HAND_DAMAGE);
    }

    /**
     * Get final attacks name string [].
     * Final attacks are the attacks that the player can choose to use in the fight versus the boss.
     *
     * @return the string [ ]
     */
    public String[] getFinalAttacksName() {
        return finalAttacksName;
    }

    /**
     * Get final attacks damage int [ ].
     * Final attacks are the attacks that the player can choose to use in the fight versus the boss.
     *
     * @return the int [ ]
     */
    public int[] getFinalAttacksDamage() {
        return finalAttacksDamage;
    }

    /**
     * Get final attacks chance double [ ].
     * Final attacks are the attacks that the player can choose to use in the fight versus the boss.
     *
     * @return the double [ ]
     */
    public double[] getFinalAttacksChance() {
        return finalAttacksChance;
    }

    /**
     * Gets weapon.
     *
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets weapon.
     *
     * @param weapon the weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setRoom(Room room) {

        this.previousRoom = this.room;
        this.room = room;

    }

    /**
     * Run away.
     * Can be used to go at the previous room.
     */
    public void runAway(){
        this.setRoom(this.previousRoom);
    }

    /**
     * Spawn the player in the spawn room.
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
     * Show infos.
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
