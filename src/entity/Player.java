package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // Player speed
    public final int DEFAULT_PLAYER_SPEED = 15;

    // Player sprites settings
    private final int UPDATE_TIME_FOR_SPRITE = 10; // Every x frames the sprite will be updated.
    private final int IDLING_SPRITE_COUNTER_MULTIPLIER = 3; // Makes the idling sprite change slower.
    private final int MOVING_SPRITE_COUNTER_MULTIPLIER = 1; // Makes the moving sprite change faster.

    // Where we draw player on the screen. (Fixed to the center of the screen, in this game).
    public final int screenX;
    public final int screenY;

    // Bounding box constants
    public final int BOUNDING_BOX_X = 8;
    public final int BOUNDING_BOX_Y = 2;

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

        boundingBox.x = gp.TILE_SIZE / BOUNDING_BOX_X;
        boundingBox.y = gp.TILE_SIZE / BOUNDING_BOX_Y;
        boundingBox.width = gp.TILE_SIZE - 2 * (gp.TILE_SIZE / BOUNDING_BOX_X) - 1;
        boundingBox.height = gp.TILE_SIZE - (gp.TILE_SIZE / BOUNDING_BOX_Y) - 1;

        setDefaultValues();
        getImages();
    }

    void setDefaultValues() {
        // Where we "spawn" the player in the world.
        worldX = gp.TILE_SIZE * 25 - gp.TILE_SIZE;
        worldY = gp.TILE_SIZE * 25 - gp.TILE_SIZE;

        // Initial speed
        speed = DEFAULT_PLAYER_SPEED;

        // Initial direction
        currentDirection = "down";
    }

    @Override
    public void getImages() {
        try {
            for (int i = 0; i < MAX_SPRITES_PER_DIRECTION; i++) {
                String upPath = "/resources/player/up" + (i+1) + ".png";
                String downPath = "/resources/player/down" + (i+1) + ".png";
                String leftPath = "/resources/player/left" + (i+1) + ".png";
                String rightPath = "/resources/player/right" + (i+1) + ".png";
                String idlingPath = "/resources/player/idling" + (i+1) + ".png";

                System.out.println("Loading: " + upPath);
                upImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(upPath)));

                System.out.println("Loading: " + downPath);
                downImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(downPath)));

                System.out.println("Loading: " + leftPath);
                leftImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(leftPath)));

                System.out.println("Loading: " + rightPath);
                rightImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(rightPath)));

                // idling1.png and idling2.png
                idlingImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(idlingPath)));

            }
        } catch (IOException e) {
            System.out.println("""
                    Error loading player sprites.
                    Check if the files are in the correct directory in src/assets/player/.
                    You can also check method getImage() in Player.java to load the correct files."""
            );
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;
        boolean isIdle = !isMoving;

        if (isIdle || (kh.isUpPressed && kh.isDownPressed) || (kh.isLeftPressed && kh.isRightPressed)) {
            currentDirection = "idling";
        } else {
            if (kh.isUpPressed && kh.isLeftPressed) currentDirection = "up-left";
            else if (kh.isUpPressed && kh.isRightPressed) currentDirection = "up-right";
            else if (kh.isDownPressed && kh.isLeftPressed) currentDirection = "down-left";
            else if (kh.isDownPressed && kh.isRightPressed) currentDirection = "down-right";
            else if (kh.isUpPressed) currentDirection = "up";
            else if (kh.isDownPressed) currentDirection = "down";
            else if (kh.isLeftPressed) currentDirection = "left";
            else currentDirection = "right";
        }

        // Every frame we increase the spriteFramesCounter
        spriteFramesCounter++;

        // The multiplier is used to change the sprite image slower or faster.
        int spriteCounterMultiplier = isIdle ? IDLING_SPRITE_COUNTER_MULTIPLIER : MOVING_SPRITE_COUNTER_MULTIPLIER;

        // Change the sprite image
        if (spriteFramesCounter >= UPDATE_TIME_FOR_SPRITE * spriteCounterMultiplier) {
            spriteFramesCounter = 0;

            switch(spriteImageNum) {
                case 1: spriteImageNum = 2; break;
                case 2: spriteImageNum = 1; break;
            }
        }

        // Change the player's position
        if (isMoving && !(kh.isUpPressed && kh.isDownPressed) && !(kh.isLeftPressed && kh.isRightPressed)) {

            isColliding = false; // Reset collision
            gp.collisionManager.checkTile(this); // This changes isColliding to true if the player is colliding with a tile.

            // Player is not colliding with a tile
            if (!isColliding) {
                // Diagonal movement
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
                }

                else if (kh.isUpPressed) worldY -= speed;
                else if (kh.isDownPressed) worldY += speed;
                else if (kh.isLeftPressed) worldX -= speed;
                else if (kh.isRightPressed) worldX += speed;
            }

            // Player is colliding with a tile
            else {
                // Diagonal movement
                if (kh.isUpPressed && kh.isLeftPressed) {
                    if (gp.collisionManager.isCollidingFromLeft(this) && gp.collisionManager.isCollidingFromTop(this)) {
                        return;
                    } else if (gp.collisionManager.isCollidingFromLeft(this)) {
                        worldY -= (int) (speed * Math.sqrt(2) / 2);
                    } else if (gp.collisionManager.isCollidingFromTop(this)) {
                        worldX -= (int) (speed * Math.sqrt(2) / 2);
                    } else {
                        worldY -= (int) (speed * Math.sqrt(2) / 2);
                        worldX -= (int) (speed * Math.sqrt(2) / 2);
                    }
                }

                else if (kh.isUpPressed && kh.isRightPressed) {
                    if (gp.collisionManager.isCollidingFromRight(this) && gp.collisionManager.isCollidingFromTop(this)) {
                        return;
                    } else if (gp.collisionManager.isCollidingFromRight(this)) {
                        worldY -= (int) (speed * Math.sqrt(2) / 2);
                    } else if (gp.collisionManager.isCollidingFromTop(this)) {
                        worldX += (int) (speed * Math.sqrt(2) / 2);
                    } else {
                        worldY -= (int) (speed * Math.sqrt(2) / 2);
                        worldX += (int) (speed * Math.sqrt(2) / 2);
                    }
                }

                else if (kh.isDownPressed && kh.isLeftPressed) {
                    if (gp.collisionManager.isCollidingFromLeft(this) && gp.collisionManager.isCollidingFromBottom(this)) {
                        return;
                    } else if (gp.collisionManager.isCollidingFromLeft(this)) {
                        worldY += (int) (speed * Math.sqrt(2) / 2);
                    } else if (gp.collisionManager.isCollidingFromBottom(this)) {
                        worldX -= (int) (speed * Math.sqrt(2) / 2);
                    } else {
                        worldY += (int) (speed * Math.sqrt(2) / 2);
                        worldX -= (int) (speed * Math.sqrt(2) / 2);
                    }
                }

                else if (kh.isDownPressed && kh.isRightPressed) {
                    if (gp.collisionManager.isCollidingFromRight(this) && gp.collisionManager.isCollidingFromBottom(this)) {
                        return;
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
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch(currentDirection) {
            case "up":
                if(spriteImageNum == 1) image = upImages[0];
                if(spriteImageNum == 2) image = upImages[1];
                break;

            case "down":
                if(spriteImageNum == 1) { image = downImages[0]; }
                if(spriteImageNum == 2) { image = downImages[1]; }
                break;

            case "left":
                if(spriteImageNum == 1) { image = leftImages[0]; }
                if(spriteImageNum == 2) { image = leftImages[1]; }
                break;

            case "right":
                if(spriteImageNum == 1) { image = rightImages[0]; }
                if(spriteImageNum == 2) { image = rightImages[1]; }
                break;

            case "up-left":
                if(spriteImageNum == 1) { image = upImages[0]; }
                if(spriteImageNum == 2) { image = upImages[1]; }
                break;

            case "up-right":
                if(spriteImageNum == 1) { image = upImages[0]; }
                if(spriteImageNum == 2) { image = upImages[1]; }
                break;

            case "down-left":
                if(spriteImageNum == 1) { image = downImages[0]; }
                if(spriteImageNum == 2) { image = downImages[1]; }
                break;

            case "down-right":
                if(spriteImageNum == 1) { image = downImages[0]; }
                if(spriteImageNum == 2) { image = downImages[1]; }
                break;

            case "idling":
                if(spriteImageNum == 1) { image = idlingImages[0]; }
                if(spriteImageNum == 2) { image = idlingImages[1]; }
                break;
        }
        // screenX and screenY is the position of the player on the screen. (Always centered)
        g2d.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
