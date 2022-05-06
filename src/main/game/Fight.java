package main.game;

import main.entities.*;

public class Fight {

    private Player player;
    private Entity entity;

    public Fight(Player player, Entity entity) {
        this.player = player;
        this.entity = entity;
    }

    public Entity fight() {
        int pvPlayer;
        int pvEntity;
        do {
            pvPlayer = player.getPv() - entity.getDamage();
            pvEntity = entity.getPv() - player.getDamage();
        } while (pvEntity <= 0 || pvPlayer <= 0);
        if (pvEntity <= 0) {
            return entity;
        }
        return player;
    }
}
