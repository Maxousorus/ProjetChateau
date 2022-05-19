package main.game;
import main.entities.*;
import main.visibles.Menu;

import java.io.IOException;


/**
 * The type Fight.
 */
public class Fight {

    private Player player;
    private Entity entity;

    /**
     * Instantiates a new Fight.
     *
     * @param player the player
     * @param entity the entity
     * @throws IOException the io exception
     */
    public Fight(Player player, Entity entity) throws IOException {
        this.player = player;
        this.entity = entity;
    }

    /**
     * Attack player.
     */
    public void attackPlayer(){
        System.out.println("J'attaque !");
        System.out.println("L'attaque provoque " + player.getDamage() + "dégats !");
        entity.setPv(entity.getPv() - player.getDamage());
        System.out.println("Le monstre a " + entity.getPv() + "points de vie");
    }

    /**
     * Attack monster.
     */
    public void attackMonster(){
        System.out.println("Le monstre attaque !");
        System.out.println("L'attaque provoque " + entity.getDamage() + "dégats !");
        player.setPv(player.getPv() - entity.getDamage());
        System.out.println("Il te reste " + player.getPv() + "points de vie");
    }

    /**
     * Fight round int.
     *
     * @return the int
     * @throws IOException the io exception
     */
    public int fightRound() throws IOException {
        Menu choixattack = new Menu("Voulez vous attaquer ou fuir ?", new String[]{ "Attaquer", " Fuir"});
        int choix;
        do {
            choix = choixattack.choose();
            if(choix == 0){
                attackPlayer();
                attackMonster();
            }
        }while(choix == 1 || player.getPv() <= 0 || entity.getPv() <= 0);
        if(player.getPv() > 0){
            return 1;
        }
        if (choix == 1){
            return -1;
        }
        return 0;
    }
}
