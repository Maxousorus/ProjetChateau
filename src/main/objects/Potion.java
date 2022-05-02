package main.objects;

import main.utils.Utils;

public class Potion extends Item {

    private int pv;

    public Potion() {
        super();
        this.pv = Utils.randomInt(0,500);
    }
}
