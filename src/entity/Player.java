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
    public final int DEFAULT_PLAYER_SPEED = 4;

    // Player sprites settings
    private final int UPDATE_TIME_FOR_SPRITE = 10; // Every x frames the sprite will be updated.

    // Where we draw player on the screen. (Usually, fixed to the center of the screen)
    public final int screenX;
    public final int screenY;

    // Objects
    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        screenX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE / 2; // Center the player on the screen
        screenY = gp.SCREEN_HEIGHT / 2 - gp.TILE_SIZE / 2; // Center the player on the screen

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
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if(kh.isLeftPressed || kh.isRightPressed || kh.isUpPressed || kh.isDownPressed) {
            if (kh.isLeftPressed) {
                currentDirection = "left";
                deltaX -= speed;
            }
            if (kh.isRightPressed) {
                currentDirection = "right";
                deltaX += speed;
            }
            if (kh.isUpPressed) {
                currentDirection = "up";
                deltaY -= speed;
            }
            if (kh.isDownPressed) {
                currentDirection = "down";
                deltaY += speed;
            }

            // Normalize the speed when moving diagonally
            if (deltaX != 0 && deltaY != 0) {
                deltaX /= Math.sqrt(1.7); // Not 2 because we want to mover a bit faster.
                deltaY /= Math.sqrt(1.7); // Not 2 because we want to mover a bit faster.
            }

            worldX += deltaX;
            worldY += deltaY;

            spriteFramesCounter++;

            // Update() is called FPS times per second. So our sprite will be updated every 10 frames.
            if(spriteFramesCounter > UPDATE_TIME_FOR_SPRITE) {
                if(spriteImageNum == 1) {
                    spriteImageNum = 2;
                } else if(spriteImageNum == 2) {
                    spriteImageNum = 1;
                }

                spriteFramesCounter = 0;
            }
        } else { // If no key is pressed, as soon as the player presses another key, the sprite will be updated.
            spriteFramesCounter = UPDATE_TIME_FOR_SPRITE;
        }
    }



    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch(currentDirection) {
            case "up":
                if(spriteImageNum == 1) {
                    image = upImages[0];
                }
                if(spriteImageNum == 2) {
                    image = upImages[1];
                }
                break;

            case "down":
                if(spriteImageNum == 1) {
                    image = downImages[0];
                }
                if(spriteImageNum == 2) {
                    image = downImages[1];
                }
                break;

            case "left":
                if(spriteImageNum == 1) {
                    image = leftImages[0];
                }
                if(spriteImageNum == 2) {
                    image = leftImages[1];
                }
                break;

            case "right":
                if(spriteImageNum == 1) {
                    image = rightImages[0];
                }
                if(spriteImageNum == 2) {
                    image = rightImages[1];
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

    @Override
    public void getImages() {
        try {
            for (int i = 0; i < MAX_SPRITES_PER_DIRECTION; i++) {
                String upPath = "/assets/player/up" + (i + 1) + ".png";
                String downPath = "/assets/player/down" + (i + 1) + ".png";
                String leftPath = "/assets/player/left" + (i + 1) + ".png";
                String rightPath = "/assets/player/right" + (i + 1) + ".png";

                System.out.println("Loading: " + upPath);
                upImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(upPath)));

                System.out.println("Loading: " + downPath);
                downImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(downPath)));

                System.out.println("Loading: " + leftPath);
                leftImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(leftPath)));

                System.out.println("Loading: " + rightPath);
                rightImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(rightPath)));
            }
        } catch (IOException e) {
            System.out.println("Error loading player sprites.\n" +
                    "Check if the files are in the correct directory in src/assets/player/.\n" +
                    "You can also check method getImage() in Player.java to load the correct files."
            );
            throw new RuntimeException(e);
        }
    }
}
