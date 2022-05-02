package main.game;

import main.entities.Entity;
import main.entities.Player;
import main.objects.Weapon;

public class Main {

    /**
     * The main method.
     * @param args
     */

    public static void main(String[] args) {
        Weapon w = new Weapon();
        Entity e = new Entity();
        Player p = new Player(w);
        Fight f = new Fight(p,e);
        System.out.println(f.fight());
    }
}
