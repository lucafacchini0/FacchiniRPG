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
                String upPath = "/assets/player/up" + (i+1) + ".png";
                String downPath = "/assets/player/down" + (i+1) + ".png";
                String leftPath = "/assets/player/left" + (i+1) + ".png";
                String rightPath = "/assets/player/right" + (i+1) + ".png";

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

    @Override
    public void update() {

        if(kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed) {
            if(kh.isUpPressed) {
                currentDirection = "up";
            } else if(kh.isDownPressed) {
                currentDirection = "down";
            } else if(kh.isLeftPressed) {
                currentDirection = "left";
            } else if(kh.isRightPressed) {
                currentDirection = "right";
            }

            isColliding = false;
            gp.collisionManager.checkTile(this);

            if(!isColliding) {
                if(kh.isUpPressed) {
                    worldY -= speed;
                } else if(kh.isDownPressed) {
                    worldY += speed;
                } else if(kh.isLeftPressed) {
                    worldX -= speed;
                } else if(kh.isRightPressed) {
                    worldX += speed;
                }
            }

            spriteFramesCounter++;
            if(spriteFramesCounter >= UPDATE_TIME_FOR_SPRITE) {
                spriteFramesCounter = 0;
                spriteImageNum = (spriteImageNum == 1) ? 2 : 1;
            }
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
}
