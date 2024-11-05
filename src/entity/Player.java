package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private final int UPDATE_TIME_FOR_SPRITE = 10;

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        setDefaultValues();
        getImages();
    }

    void setDefaultValues() {
        x = 250;
        y = 250;
        speed = gamePanel.DEFAULT_PLAYER_SPEED;
        currentDirection = "down";
    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if(keyHandler.isLeftPressed == true || keyHandler.isRightPressed == true || keyHandler.isUpPressed == true || keyHandler.isDownPressed == true) {
            if (keyHandler.isLeftPressed) {
                currentDirection = "left";
                deltaX -= speed;
            }
            if (keyHandler.isRightPressed) {
                currentDirection = "right";
                deltaX += speed;
            }
            if (keyHandler.isUpPressed) {
                currentDirection = "up";
                deltaY -= speed;
            }
            if (keyHandler.isDownPressed) {
                currentDirection = "down";
                deltaY += speed;
            }

            // Normalize the speed when moving diagonally
            if (deltaX != 0 && deltaY != 0) {
                deltaX /= Math.sqrt(2);
                deltaY /= Math.sqrt(2);
            }

            x += deltaX;
            y += deltaY;

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

        g2d.drawImage(image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }

    @Override
    public void getImages() {
        try {
            for(int i = 0; i < MAX_SPRITES_PER_DIRECTION; i++) {
                upImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/up" + (i + 1) + ".png")));
                downImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/down" + (i + 1) + ".png")));
                leftImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left" + (i + 1) + ".png")));
                rightImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/right" + (i + 1) + ".png")));
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
