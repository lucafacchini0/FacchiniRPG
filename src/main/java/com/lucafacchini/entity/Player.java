package com.lucafacchini.entity;

import com.lucafacchini.GamePanel;
import com.lucafacchini.KeyHandler;
import com.lucafacchini.Utilities;

import java.awt.*;

public class Player extends Entity {

    // ---------- Player Properties ----------

    public final int PLAYER_HEIGHT = 19;
    public final int PLAYER_WIDTH = 11;
    public final int RESCALED_PLAYER_HEIGHT;
    public final int RESCALED_PLAYER_WIDTH;
    public final int DEFAULT_PLAYER_SPEED = 5;



    // ---------- Sprite Settings ----------

    public final int PLAYER_MAX_SPRITES_PER_WALKING_DIRECTION = 6;
    public final int PLAYER_MAX_SPRITES_PER_IDLING_DIRECTION = 4;



    // ---------- Animation Timing ----------

    public final int UPDATE_TIME_FOR_SPRITE = 1;
    public final int MOVING_PLAYER_SPRITE_MULTIPLIER = 5;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_DEFAULT = 20;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_OPEN = 120;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_CLOSED = MOVING_PLAYER_SPRITE_MULTIPLIER;



    // ---------- Player Coordinates (Centered) ----------

    public final int screenX;
    public final int screenY;



    // ---------- Object Components Tracking ----------

    public int hasKey = 0;



    // ---------- Game References ----------

    GamePanel gp;
    KeyHandler kh;
    Utilities utilities = new Utilities();



    // ---------- Constructor ----------

    public Player(GamePanel gp, KeyHandler kh) {
        super(gp);
        this.gp = gp;
        this.kh = kh;

        // Center the player on the screen
        screenX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE / 2;
        screenY = gp.SCREEN_HEIGHT / 2 - gp.TILE_SIZE / 2;

        // Set up bounding box properties
        boundingBox.x = 0;
        boundingBox.y = 20;
        boundingBox.width = gp.TILE_SIZE - 20;
        boundingBox.height = gp.TILE_SIZE - 10;

        boundingBoxDefaultX = boundingBox.x;
        boundingBoxDefaultY = boundingBox.y;

        // Rescale player dimensions
        RESCALED_PLAYER_HEIGHT = PLAYER_HEIGHT * gp.SCALE;
        RESCALED_PLAYER_WIDTH = PLAYER_WIDTH * gp.SCALE;

        initializePlayer();
     }



    // ---------- Initialization Methods ----------

    private void initializePlayer() {
        setDefaultValues();
        getImages("player", PLAYER_MAX_SPRITES_PER_WALKING_DIRECTION, PLAYER_MAX_SPRITES_PER_IDLING_DIRECTION);
        rescaleImages(RESCALED_PLAYER_WIDTH, RESCALED_PLAYER_HEIGHT, PLAYER_MAX_SPRITES_PER_WALKING_DIRECTION, PLAYER_MAX_SPRITES_PER_IDLING_DIRECTION, utilities);
    }

    @Override
    public void setDefaultValues() {
        worldX = gp.TILE_SIZE * 25 - gp.TILE_SIZE; // Spawn at the center of the map
        worldY = gp.TILE_SIZE * 25 - gp.TILE_SIZE; // Spawn at the center of the map

        speed = DEFAULT_PLAYER_SPEED;
        currentDirection = "down";
    }



    // ---------- Game Update Loop ----------

    @Override
    public void update() {
        updateDirection(kh, true);
        updateSprite(UPDATE_TIME_FOR_SPRITE, IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_OPEN,
                IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_CLOSED, IDLING_PLAYER_SPRITE_MULTIPLIER_DEFAULT,
                MOVING_PLAYER_SPRITE_MULTIPLIER, PLAYER_MAX_SPRITES_PER_WALKING_DIRECTION);
        updatePosition(); // In this class TODO: Move to Entity class
    }



    // ---------- Movement and Collision ----------

    private void updatePosition() {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;

        if (isMoving && !(kh.isUpPressed && kh.isDownPressed) && !(kh.isLeftPressed && kh.isRightPressed)) {
            isCollidingWithTile = false;
            isCollidingWithObject = false;

            gp.collisionManager.checkTile(this);
            int objectIndex = gp.collisionManager.checkObject(this, true);
            pickUpObject(objectIndex);

            if (!isCollidingWithTile && !isCollidingWithObject) {
                move(kh, true);
            } else if (isCollidingWithTile && !isCollidingWithObject) {
                handleCollision(kh); // TODO: Fix this method. Diagonal movement is not working while colliding with Objects.
            } else {
                // Implement diagonal object/NPC collision detection
            }
        }
    }



    // ---------- Object Interactions ----------

    // TODO: Use more specific sound titles (e.g. "key_pickup", "door_open", etc.)
    private void pickUpObject(int index) {
        if(index != -1) {
            String objectName = gp.objectsArray[index].name;

            switch(objectName) {
                case "Key":
                    gp.playSound(1);
                    hasKey++;
                    gp.ui.showMessage("You picked up a key!");
                    gp.objectsArray[index] = null;
                    break;

                case "Door":
                    if(hasKey > 0) {
                        gp.ui.showMessage("You used a key!");
                        gp.playSound(3);
                        hasKey--;
                        gp.objectsArray[index] = null;
                    } else {
                        gp.ui.showMessage("You need a key to open this door!");
                    }
                    break;

                case "Boots":
                    gp.ui.showMessage("You picked up boots!");
                    gp.playSound(2);
                    speed *= 2;
                    gp.objectsArray[index] = null;
                    break;

                case "Chest":
                    gp.stopMusic();
                    gp.playSound(4);
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }



    // ------------------- Drawing -------------------

    public void draw(Graphics2D g2d) {
        drawEntity(g2d, screenX, screenY);
    }
}
