package com.lucafacchini.entity;

import com.lucafacchini.GamePanel;
import com.lucafacchini.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // Player settings
    public final int DEFAULT_PLAYER_SPEED = 4;

    // Sprite settings
    public final int UPDATE_TIME_FOR_SPRITE = 1;
    public final int MOVING_PLAYER_SPRITE_MULTIPLIER = 5;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_DEFAULT = 20;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_OPEN = 120;
    public final int IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_CLOSED = MOVING_PLAYER_SPRITE_MULTIPLIER;

    private double spriteCounterMultiplier = MOVING_PLAYER_SPRITE_MULTIPLIER;

    public String lastPosition = "down";

    // Coordinates of the player on the screen
    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    // Objects
    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        screenX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE / 2; // Center the player on the screen
        screenY = gp.SCREEN_HEIGHT / 2 - gp.TILE_SIZE / 2; // Center the player on the screen

        // Bounding box settings
        boundingBox = new Rectangle();

        boundingBox.x = 0;
        boundingBox.y = 20;
        boundingBox.width = gp.TILE_SIZE - 20;
        boundingBox.height = gp.TILE_SIZE - 10;

        boundingBoxDefaultX = boundingBox.x;
        boundingBoxDefaultY = boundingBox.y;

        // Load player sprites
        setDefaultValues();
        getImages();
    }

    void setDefaultValues() {
        worldX = gp.TILE_SIZE * 25 - gp.TILE_SIZE;
        worldY = gp.TILE_SIZE * 25 - gp.TILE_SIZE;

        speed = DEFAULT_PLAYER_SPEED;
        currentDirection = "down";
    }

    @Override
    public void getImages() {
        try {
            for(int i = 0; i < MAX_SPRITES_PER_WALKING_DIRECTION; i++) {
                upImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk_up_" + (i+1) + ".png")));
                downImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk_down_" + (i+1) + ".png")));
                leftImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk_left_" + (i+1) + ".png")));
                rightImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walk_right_" + (i+1) + ".png")));
            }

            for(int i = 0; i < MAX_SPRITES_PER_IDLING_DIRECTION; i++) {
                idlingUpImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idling/idling_up_" + (i+1) + ".png")));
                idlingDownImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idling/idling_down_" + (i+1) + ".png")));
                idlingLeftImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idling/idling_left_" + (i+1) + ".png")));
                idlingRightImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idling/idling_right_" + (i+1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // In loop
    @Override
    public void update() {
        updateDirection();
        updateSprite();
        updatePosition();
    }

    private void updateDirection() {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;
        boolean isIdle = !isMoving;

        if (isIdle || (kh.isUpPressed && kh.isDownPressed) || (kh.isLeftPressed && kh.isRightPressed)) {
            currentDirection = "idling-" + lastPosition;
        } else {
            if (kh.isUpPressed && kh.isLeftPressed) {
                currentDirection = "up-left";
                lastPosition = currentDirection;
            }
            else if (kh.isUpPressed && kh.isRightPressed) {
                currentDirection = "up-right";
                lastPosition = currentDirection;
            }
            else if (kh.isDownPressed && kh.isLeftPressed) {
                currentDirection = "down-left";
                lastPosition = currentDirection;
            }
            else if (kh.isDownPressed && kh.isRightPressed) {
                currentDirection = "down-right";
                lastPosition = currentDirection;
            }
            else if (kh.isUpPressed) {
                currentDirection = "up";
                lastPosition = currentDirection;
            }
            else if (kh.isDownPressed) {
                currentDirection = "down";
                lastPosition = currentDirection;
            }
            else if (kh.isLeftPressed) {
                currentDirection = "left";
                lastPosition = currentDirection;
            }
            else {
                currentDirection = "right";
                lastPosition = currentDirection;
            }
        }
    }

    // TODO: If the player is facing left or right and moves just by one pixel, the sprite animation iterates a full cycle.
    private void updateSprite() {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;

        spriteFramesCounter++; // Increase the counter FPS times per second

        // This method has the only purpose of setting some "delays" in the sprite animation
        // depending on the current direction of the player. It does not follow any
        // logical pattern, it's just a way to make the sprite animation look better.
        setMultiplier(currentDirection, spriteImageNum);

        if(currentDirection.contains("idling")) {
            if(spriteImageNum > 4) { // 4 is the last sprite of the idling animation. Walk have 6.
                spriteImageNum = 1;
            }
        }
        if (spriteFramesCounter >= UPDATE_TIME_FOR_SPRITE * spriteCounterMultiplier) {
            spriteFramesCounter = 0;

            // ##TROUBLESHOOTING
            // System.out.println("currentDirection: " + currentDirection + " lastPosition: " + lastPosition);
            if (isMoving) {
                // ##TROUBLESHOOTING
                // System.out.println("isMoving: " + isMoving + " spriteImageNum: " + spriteImageNum);
                spriteImageNum++;
                if (spriteImageNum > MAX_SPRITES_PER_WALKING_DIRECTION) {
                    spriteImageNum = 1;
                }
            } else {
                // ##TROUBLESHOOTING
                // System.out.println("isMoving: " + isMoving + " spriteImageNum: " + spriteImageNum);
                spriteImageNum++;
                if (spriteImageNum > MAX_SPRITES_PER_IDLING_DIRECTION) {
                    spriteImageNum = 1;
                }
            }
        }
    }

    private void updatePosition() {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;

        if (isMoving && !(kh.isUpPressed && kh.isDownPressed) && !(kh.isLeftPressed && kh.isRightPressed)) {
            isColliding = false;
            gp.collisionManager.checkTile(this);
            int objectIndex = gp.collisionManager.checkObject(this, true);

            pickUpObject(objectIndex);

            if (!isColliding) {
                movePlayer();
            } else {
               // handleCollision(); // TODO: Fix this method. Diagonal movement is not working while colliding with Objects.
            }
        }
    }

    private void setMultiplier(String direction, int spriteImageNum) {
        if(direction.equals("idling-down") || direction.equals("idling-left") || direction.equals("idling-right")) {
            if(spriteImageNum == 1) {
                spriteCounterMultiplier = IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_OPEN;
            } else {
                spriteCounterMultiplier = IDLING_PLAYER_SPRITE_MULTIPLIER_EYES_CLOSED;
            }
        }

        else if(direction.contains("idling")) {
            spriteCounterMultiplier = IDLING_PLAYER_SPRITE_MULTIPLIER_DEFAULT;
        } else {
            spriteCounterMultiplier = MOVING_PLAYER_SPRITE_MULTIPLIER;
        }
    }

    private void movePlayer() {
        if (kh.isUpPressed && kh.isLeftPressed) {
            worldY -= (int) (speed * Math.sqrt(2) / 2);
            worldX -= (int) (speed * Math.sqrt(2) / 2);
        } else if (kh.isUpPressed && kh.isRightPressed) {
            worldY -= (int) (speed * Math.sqrt(2) / 2);
            worldX += (int) (speed * Math.sqrt(2) / 2);
        } else if (kh.isDownPressed && kh.isLeftPressed) {
            worldY += (int) (speed * Math.sqrt(2) / 2);
            worldX -= (int) (speed * Math.sqrt(2) / 2);
        } else if (kh.isDownPressed && kh.isRightPressed) {
            worldY += (int) (speed * Math.sqrt(2) / 2);
            worldX += (int) (speed * Math.sqrt(2) / 2);
        } else if (kh.isUpPressed) {
            worldY -= speed;
        } else if (kh.isDownPressed) {
            worldY += speed;
        } else if (kh.isLeftPressed) {
            worldX -= speed;
        } else if (kh.isRightPressed) {
            worldX += speed;
        }
    }

    // TODO: Fix this method. Diagonal movement is not working while colliding with Objects.
    private void handleCollision() {
        if (kh.isUpPressed && kh.isLeftPressed) {
            if (gp.collisionManager.isCollidingFromLeft(this) && gp.collisionManager.isCollidingFromTop(this)) {
            } else if (gp.collisionManager.isCollidingFromLeft(this)) {
                worldY -= (int) (speed * Math.sqrt(2) / 2);
            } else if (gp.collisionManager.isCollidingFromTop(this)) {
                worldX -= (int) (speed * Math.sqrt(2) / 2);
            } else {
                worldY -= (int) (speed * Math.sqrt(2) / 2);
                worldX -= (int) (speed * Math.sqrt(2) / 2);
            }
        } else if (kh.isUpPressed && kh.isRightPressed) {
            if (gp.collisionManager.isCollidingFromRight(this) && gp.collisionManager.isCollidingFromTop(this)) {
            } else if (gp.collisionManager.isCollidingFromRight(this)) {
                worldY -= (int) (speed * Math.sqrt(2) / 2);
            } else if (gp.collisionManager.isCollidingFromTop(this)) {
                worldX += (int) (speed * Math.sqrt(2) / 2);
            } else {
                worldY -= (int) (speed * Math.sqrt(2) / 2);
                worldX += (int) (speed * Math.sqrt(2) / 2);
            }
        } else if (kh.isDownPressed && kh.isLeftPressed) {
            if (gp.collisionManager.isCollidingFromLeft(this) && gp.collisionManager.isCollidingFromBottom(this)) {
            } else if (gp.collisionManager.isCollidingFromLeft(this)) {
                worldY += (int) (speed * Math.sqrt(2) / 2);
            } else if (gp.collisionManager.isCollidingFromBottom(this)) {
                worldX -= (int) (speed * Math.sqrt(2) / 2);
            } else {
                worldY += (int) (speed * Math.sqrt(2) / 2);
                worldX -= (int) (speed * Math.sqrt(2) / 2);
            }
        } else if (kh.isDownPressed && kh.isRightPressed) {
            if (gp.collisionManager.isCollidingFromRight(this) && gp.collisionManager.isCollidingFromBottom(this)) {
            } else if (gp.collisionManager.isCollidingFromRight(this)) {
                worldY += (int) (speed * Math.sqrt(2) / 2);
            } else if (gp.collisionManager.isCollidingFromBottom(this)) {
                worldX += (int) (speed * Math.sqrt(2) / 2);
            } else {
                worldY += (int) (speed * Math.sqrt(2) / 2);
                worldX += (int) (speed * Math.sqrt(2) / 2);
            }
        }
    }

    private void pickUpObject(int index) {
        if(index != -1) {

            // ##TROUBLESHOOTING
            // for(int i = 0; i < gp.objectsArray.length; i++) {
            //     if(gp.objectsArray[i] != null) {
            //         System.out.println("Index: " + i + " Object: " + gp.objectsArray[i].name);
            //     }
            // }
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

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (currentDirection) {
            case "up":
            case "up-left":
            case "up-right":
                switch (spriteImageNum) {
                    case 1: image = upImages[0]; break;
                    case 2: image = upImages[1]; break;
                    case 3: image = upImages[2]; break;
                    case 4: image = upImages[3]; break;
                    case 5: image = upImages[4]; break;
                    case 6: image = upImages[5]; break;
                }
                break;

            case "down":
            case "down-left":
            case "down-right":
                switch (spriteImageNum) {
                    case 1: image = downImages[0]; break;
                    case 2: image = downImages[1]; break;
                    case 3: image = downImages[2]; break;
                    case 4: image = downImages[3]; break;
                    case 5: image = downImages[4]; break;
                    case 6: image = downImages[5]; break;
                }
                break;

            case "left":
            case "idling-left":
            case "idling-up-left":
            case "idling-down-left":
                switch (spriteImageNum) {
                    case 1: image = leftImages[0]; break;
                    case 2: image = leftImages[1]; break;
                    case 3: image = leftImages[2]; break;
                    case 4: image = leftImages[3]; break;
                    case 5: image = leftImages[4]; break;
                    case 6: image = leftImages[5]; break;
                }
                break;

            case "right":
            case "idling-right":
            case "idling-up-right":
            case "idling-down-right":
                switch (spriteImageNum) {
                    case 1: image = rightImages[0]; break;
                    case 2: image = rightImages[1]; break;
                    case 3: image = rightImages[2]; break;
                    case 4: image = rightImages[3]; break;
                    case 5: image = rightImages[4]; break;
                    case 6: image = rightImages[5]; break;
                }
                break;

            case "idling-up":
                switch (spriteImageNum) {
                    case 1: image = idlingUpImages[0]; break;
                    case 2: image = idlingUpImages[1]; break;
                    case 3: image = idlingUpImages[2]; break;
                    case 4: image = idlingUpImages[3]; break;
                }
                break;

            case "idling-down":
                switch (spriteImageNum) {
                    case 1: image = idlingDownImages[0]; break;
                    case 2: image = idlingDownImages[1]; break;
                    case 3: image = idlingDownImages[2]; break;
                    case 4: image = idlingDownImages[3]; break;
                }
                break;

            default:
                image = null;  // In case of an invalid direction
                break;
        }

        if (image != null) {
            g2d.drawImage(image, screenX, screenY, 11 * 4, 19 * 4, null);
        }

        // ##TROUBLESHOOTING ##IMPORTANT
//        g2d.setColor(Color.RED);
//        g2d.drawRect(screenX + boundingBox.x, screenY + boundingBox.y, boundingBox.width, boundingBox.height);
    }


}

