package main.objects;

public class Potion extends Item {

    private int pv;

    public Potion(int pv, String name) {
        super(name);
        this.pv = pv;
    }

    public Potion() {
        super();
    }
}
