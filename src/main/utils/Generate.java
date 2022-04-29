package main.utils;

import static java.lang.Math.random;
import main.challenges.Challenge;
import main.challenges.Sage;
import main.challenges.Trap;
import main.entities.Monster;
import main.locations.*;
import main.objects.*;

public class Generate {

    public static Castle castle(int nbFloors) {
        Castle castle = new Castle(nbFloors);
        Floor[] floors = castle.getFloors();
        Utils.setCastleSpawn(castle);
        Utils.setCastleStairs(castle);
        Utils.setCastleExit(castle);

        return castle;
    }

    public static Monster monster(){
        return new Monster();
    }

    public static Challenge challenge(){
        if(random() > 0.5) {
            return new Sage();
        }
        return new Trap();
    }

    public static Item item() {
        if(random()> 0.5) {
            return new Potion();
        }
        return new Weapon();
    }
}
