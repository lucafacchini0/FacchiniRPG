package com.lucafacchini.entity;

import com.lucafacchini.GamePanel;

public class NPC_OldMan extends Entity {

    // ---------- NPC Properties ----------

    public final String name;

    public final int NPC_HEIGTH = 16;
    public final int NPC_WIDTH = 16;
    public final int RESCALED_NPC_HEIGHT;
    public final int RESCALED_NPC_WIDTH;
    public final int DEFAULT_NPC_SPEED = 2;



    // ---------- Sprite Settings ----------

    public final int NPC_MAX_SPRITES_PER_WALKING_DIRECTION = 2;
    public final int NPC_MAX_SPRITES_PER_IDLING_DIRECTION = 1;



    // ---------- Animation Timing ----------

    public final int UPDATE_TIME_FOR_SPRITE = 1;
    public final int MOVING_NPC_SPRITE_MULTIPLIER = 5;
    public final int IDLING_NPC_SPRITE_MULTIPLIER_DEFAULT = 20;
    public final int IDLING_NPC_SPRITE_MULTIPLIER_EYES_OPEN = 120;
    public final int IDLING_NPC_SPRITE_MULTIPLIER_EYES_CLOSED = MOVING_NPC_SPRITE_MULTIPLIER;



    // ---------- Constructor ----------

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        name = "Old Man";
        currentDirection = "down";
        speed = 2;

        worldX = 22 * gp.TILE_SIZE;
        worldY = 22 * gp.TILE_SIZE;

        // also pas class name
        getImages("npc/old_man", 4, 4);

        RESCALED_NPC_HEIGHT = gp.TILE_SIZE * NPC_HEIGTH;
        RESCALED_NPC_WIDTH = gp.TILE_SIZE * NPC_WIDTH;
    }

    public void update() {
        updateDirection(null, false);
        updateSprite(UPDATE_TIME_FOR_SPRITE,  IDLING_NPC_SPRITE_MULTIPLIER_EYES_OPEN,
                IDLING_NPC_SPRITE_MULTIPLIER_EYES_CLOSED, MOVING_NPC_SPRITE_MULTIPLIER,
                NPC_MAX_SPRITES_PER_WALKING_DIRECTION, NPC_MAX_SPRITES_PER_IDLING_DIRECTION);
        updatePosition();
    }

    private void updatePosition() {
        switch(lastPosition) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

    }


}
