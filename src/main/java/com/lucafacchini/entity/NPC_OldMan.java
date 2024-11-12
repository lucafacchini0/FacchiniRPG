package com.lucafacchini.entity;

import com.lucafacchini.GamePanel;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        currentDirection = "down";
        speed = 2;

        worldX = 22 * gp.TILE_SIZE;
        worldY = 22 * gp.TILE_SIZE;

        getImages("npc", 2, 1);
    }

}
