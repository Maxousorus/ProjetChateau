package main.game;
import main.entities.*;
import main.visibles.Menu;
import main.visibles.Notification;
import main.visibles.Popup;

import java.io.IOException;


/**
 * The type Fight.
 */
public class Fight {

    private Player player;
    private Entity entity;

    private Game game;

    /**
     * Instantiates a new Fight.
     *
     * @param player the player
     * @param entity the entity
     * @throws IOException the io exception
     */
    public Fight(Player player, Entity entity, Game game) throws IOException {
        this.player = player;
        this.entity = entity;
        this.game = game;
    }

    /**
     * Attack player.
     */
    private void attackPlayer() throws IOException {
        new Notification("The " + entity.getName() + " attacks you ! You take " + entity.getDamage() + " damages !").choose();
        player.setPv(player.getPv() - entity.getDamage());
        game.getGame_statistics().addDamageTaken(entity.getDamage());
    }

    /**
     * Attack monster.
     */
    private void attackMonster() throws IOException {
        if(player.getWeapon() == null) {
            new Notification("You attack the " + entity.getName() + " ! He takes " + player.getDamage() + " damages !").choose();
            entity.setPv(entity.getPv() - player.getDamage());
            game.getGame_statistics().addDamageDealt(player.getDamage());
        } else {
            new Notification("You attack the " + entity.getName() + " with you " + player.getWeapon().getName() +
                    " ! He takes " + player.getWeapon().getDamage() + " damages !").choose();
            entity.setPv(entity.getPv() - player.getWeapon().getDamage());
            game.getGame_statistics().addDamageDealt(player.getWeapon().getDamage());
        }
    }

    /**
     * Fight round int.
     *
     * @return the int
     * @throws IOException the io exception
     */
    public int fight() throws IOException {

        Menu chooseAction;
        int choice;
        do {
            chooseAction = new Menu("Do you attack or run away ?", new String[]{ "Attack", "Run away" },
                    new Popup("--- PLAYER ---",
                            "HP : " + player.getPv(),
                            "--- " + entity.getName().toUpperCase() + " ---",
                            "HP : " + entity.getPv()));;
            choice = chooseAction.choose();
            if (choice == 0) { //attack
                attackMonster();
                attackPlayer();
            }
            if (choice == 1) {
                return 0; //player escape
            }
        } while (player.getPv() > 0 && entity.getPv() > 0);

        if (player.getPv() <= 0) {
            return -1; //player dead
        }
        return 1; //monster dead
    }
}
