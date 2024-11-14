package com.lucafacchini.entity;

import com.lucafacchini.GamePanel;
import com.lucafacchini.KeyHandler;
import com.lucafacchini.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Entity {

    // ------------------- Fields -------------------

    // Direction and position
    public String currentDirection;
    public String lastPosition = "down";
    public int worldX, worldY;
    public int speed;

    // Sprite and animation settings
    public int spriteCounterMultiplier = 1; // Speed of the sprite animation.
    public int spriteFramesCounter = 0;     // Frames passed since last sprite change.
    public int spriteImageNum = 1;          // Current sprite image number.
    public HashMap<String, BufferedImage[]> images = new HashMap<>();

    // Collision properties
    public Rectangle boundingBox;
    public int boundingBoxDefaultX, boundingBoxDefaultY;
    public boolean isCollidingWithTile = false;
    public boolean isCollidingWithObject = false;

    // Utilities
    public Utilities utilities = new Utilities();

    // Game Panel reference
    GamePanel gp;



    // ------------------- Constructor -------------------

    public Entity(GamePanel gp) {
        boundingBox = new Rectangle(0, 0, gp.TILE_SIZE, gp.TILE_SIZE);
        this.gp = gp;
    }



    // ------------------- Initialization -------------------

    public void setDefaultValues() {}



    // ------------------- Update Methods -------------------

    public void update() {}

    public void updateDirection(KeyHandler kh, boolean isPlayer) {
        if (isPlayer) {
            updatePlayerDirection(kh);
        } else {
            setRandomDirection();
        }
    }

    private void updatePlayerDirection(KeyHandler kh) {
        boolean isMoving = kh.isUpPressed || kh.isDownPressed || kh.isLeftPressed || kh.isRightPressed;
        boolean isIdle = !isMoving;

        if (isIdle || (kh.isUpPressed && kh.isDownPressed) || (kh.isLeftPressed && kh.isRightPressed)) {
            currentDirection = "idling-" + lastPosition;
        } else {
            setDirectionBasedOnKeys(kh);
        }
    }

    private void setDirectionBasedOnKeys(KeyHandler kh) {
        if (kh.isUpPressed && kh.isLeftPressed) {
            setDirection("up-left");
        } else if (kh.isUpPressed && kh.isRightPressed) {
            setDirection("up-right");
        } else if (kh.isDownPressed && kh.isLeftPressed) {
            setDirection("down-left");
        } else if (kh.isDownPressed && kh.isRightPressed) {
            setDirection("down-right");
        } else if (kh.isUpPressed) {
            setDirection("up");
        } else if (kh.isDownPressed) {
            setDirection("down");
        } else if (kh.isLeftPressed) {
            setDirection("left");
        } else {
            setDirection("right");
        }
    }

    private void setDirection(String direction) {
        currentDirection = direction;
        lastPosition = direction;
    }

    private void setRandomDirection() {
        String[] directions = {"up", "down", "left", "right"};
        currentDirection = directions[new Random().nextInt(directions.length)];
    }



    public void updateSprite(int UPDATE_TIME_FOR_SPRITE, int IDLING_EYES_OPEN_MULTIPLIER,
                             int IDLING_EYES_CLOSED_MULTIPLIER, int IDLING_DEFAULT_MULTIPLIER, int MOVING_MULTIPLIER,
                             int MAX_SPRITES_WALKING) {
        spriteFramesCounter++; // Increase the counter FPS times per second

        // This method has the only purpose of setting some "delays" in the sprite animation
        // depending on the current direction of the player. It does not follow any
        // logical pattern, it's just a way to make the sprite animation look better.
        setMultiplier(currentDirection, spriteImageNum, IDLING_EYES_OPEN_MULTIPLIER,
                IDLING_EYES_CLOSED_MULTIPLIER, IDLING_DEFAULT_MULTIPLIER, MOVING_MULTIPLIER);

        // Reset the sprite image number if the player is idling
        if(currentDirection.contains("idling")) {
            if(spriteImageNum > 4) { // 4 is the last sprite of the idling animation. Walk have 6.
                spriteImageNum = 1;
            }
        }

        // Update the sprite image number, depending on the spriteCounterMultiplier
        // That variable depends on the current direction of the player. It's
        // assigned in the setMultiplier method.
        if (spriteFramesCounter >= UPDATE_TIME_FOR_SPRITE * spriteCounterMultiplier) {
            spriteFramesCounter = 0;
            spriteImageNum++;
            if (spriteImageNum > MAX_SPRITES_WALKING) {
                spriteImageNum = 1;
            }
        }
    }

    private void setMultiplier(String direction, int spriteImageNum,
                               int IDLING_EYES_OPEN_MULTIPLIER, int IDLING_EYES_CLOSED_MULTIPLIER,
                               int IDLING_DEFAULT_MULTIPLIER, int MOVING_MULTIPLIER) {

        if(direction.equals("idling-down") || direction.equals("idling-left") || direction.equals("idling-right")) {
            if(spriteImageNum == 1) {
                spriteCounterMultiplier = IDLING_EYES_OPEN_MULTIPLIER;
            } else {
                spriteCounterMultiplier = IDLING_EYES_CLOSED_MULTIPLIER;
            }
        } else if(direction.contains("idling")) {
            spriteCounterMultiplier = IDLING_DEFAULT_MULTIPLIER;
        } else {
            spriteCounterMultiplier = MOVING_MULTIPLIER;
        }
    }



    // ------------------- Movement Methods -------------------

    public void move(KeyHandler kh, boolean isPlayer) {
        if(isPlayer) {
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
    }

    // TODO: Fix this method. Diagonal movement is not working while colliding with Objects.
    public void handleCollision(KeyHandler kh) {
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



    // ------------------- Image Handling -------------------

    public void getImages(String folderName, int MAX_WALKING_SPRITES, int MAX_IDLING_SPRITES) {
        loadDirectionalImages("up", "/" + folderName + "/walk_up_", MAX_WALKING_SPRITES);
        loadDirectionalImages("down", "/" + folderName + "/walk_down_", MAX_WALKING_SPRITES);
        loadDirectionalImages("left", "/" + folderName + "/walk_left_", MAX_WALKING_SPRITES);
        loadDirectionalImages("right", "/" + folderName + "/walk_right_", MAX_WALKING_SPRITES);

        loadDirectionalImages("idlingUp", "/" + folderName + "/idling/idling_up_", MAX_IDLING_SPRITES);
        loadDirectionalImages("idlingDown", "/" + folderName + "/idling/idling_down_", MAX_IDLING_SPRITES);
        loadDirectionalImages("idlingLeft", "/" + folderName + "/idling/idling_left_", MAX_IDLING_SPRITES);
        loadDirectionalImages("idlingRight", "/" + folderName + "/idling/idling_right_", MAX_IDLING_SPRITES);
    }

    private void loadDirectionalImages(String key, String pathPrefix, int maxSprites) {


        String imagePath = null;
        try {
            imagePath = "";

            BufferedImage[] directionImages = images.computeIfAbsent(key, _ -> new BufferedImage[maxSprites]);
            for (int i = 0; i < maxSprites; i++) {
                imagePath = pathPrefix + (i + 1) + ".png";
                directionImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            }
            utilities.printLoadingImagesSuccess(getClass().getSimpleName(), "Loaded SPRITES: ", imagePath);
        } catch (NullPointerException e) {
            utilities.printLoadingImagesError(getClass().getSimpleName(), "Error loading SPRITES: ", imagePath, "NullPointerException");
        } catch (IOException e) {
            utilities.printLoadingImagesError(getClass().getSimpleName(), "Error loading SPRITES: ", imagePath, "IOException");
        }
    }

    public void rescaleImages(final int RESCALED_WIDTH, final int RESCALED_HEIGHT, final int WALKING_SPRITES, final int IDLING_SPRITES, final Utilities utilities) {
        for (String direction : new String[]{"up", "down", "left", "right"}) {
            BufferedImage[] directionImages = images.get(direction);
            if (directionImages != null) {
                for (int i = 0; i < WALKING_SPRITES; i++) {
                    if (directionImages[i] != null) {
                        directionImages[i] = utilities.rescaleImage(directionImages[i], RESCALED_WIDTH, RESCALED_HEIGHT);
                    }
                }
            }
        }

        for (String direction : new String[]{"idlingUp", "idlingDown", "idlingLeft", "idlingRight"}) {
            BufferedImage[] directionImages = images.get(direction);
            if (directionImages != null) {
                for (int i = 0; i < IDLING_SPRITES; i++) {
                    if (directionImages[i] != null) {
                        directionImages[i] = utilities.rescaleImage(directionImages[i], RESCALED_WIDTH, RESCALED_HEIGHT);
                    }
                }
            }
        }
    }



    // ------------------- Drawing -------------------

    public void drawEntity(Graphics2D g2d, int screenX, int screenY) {
        BufferedImage image;

        switch (currentDirection) {
            case "up", "up-left", "up-right" -> image = getImageForDirection("up", spriteImageNum);
            case "down", "down-left", "down-right" -> image = getImageForDirection("down", spriteImageNum);
            case "left", "left-up", "left-down" -> image = getImageForDirection("left", spriteImageNum);
            case "right", "right-up", "right-down" -> image = getImageForDirection("right", spriteImageNum);
            // Add more cases as needed
            default -> image = getImageForDirection("idlingDown", spriteImageNum); // default direction
        }

        if (image != null) {
            g2d.drawImage(image, screenX, screenY, null);

            // Debug
            g2d.setColor(Color.RED);
            g2d.drawRect(screenX + boundingBox.x, screenY + boundingBox.y, boundingBox.width, boundingBox.height);
            utilities.drawEntitySuccess(getClass().getSimpleName(), "Drawn entity at: " + worldX / gp.TILE_SIZE + ", " + worldY / gp.TILE_SIZE);
        }
    }

    private BufferedImage getImageForDirection(String direction, int spriteImageNum) {
        BufferedImage[] directionImages = images.get(direction);
        if (directionImages == null || directionImages.length == 0) {
            throw new IllegalArgumentException("No images found for direction: " + direction);
        }
        // Clamp spriteImageNum within the valid range
        int index = Math.max(0, Math.min(spriteImageNum - 1, directionImages.length - 1));
        return directionImages[index];
    }



    // ------------------- Utilities & Debugging -------------------



}