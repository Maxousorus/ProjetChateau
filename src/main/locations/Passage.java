package main.locations;

import main.entities.*;
import main.challenges.*;
import main.utils.*;

public class Passage {

    private int challenge_type; //Monster or Sage or Trap
    private Entity monster;
    private Sage sage;
    private Trap trap;
    private boolean locked;

    private static final int MONSTER = 0;
    private static final int SAGE = 1;
    private static final int TRAP = 2;

    /**
     * Constructor
     */

    public Passage(){
        challenge_type = (int) (Math.random() * 3);
        if (challenge_type == MONSTER) {
            this.monster = new Entity();
        } else if (challenge_type == SAGE) {
            this.sage = new Sage();
        } else if (challenge_type == TRAP) {
            this.trap = new Trap();
        }

        this.locked = (Math.random() < Parameters.CHANCE_OF_LOCKED_PASSAGE);
    }

}
