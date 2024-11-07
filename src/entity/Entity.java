package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY; // The position of the entity in the game world. Not just the visible screen.
    public int speed;

    // worldX and worldY is not where we draw the player.
    // This is the Entity's position in the game world.


    public final int MAX_SPRITES_PER_DIRECTION = 2;

    public BufferedImage[] upImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] downImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] leftImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] rightImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] idlingImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];

    public String currentDirection;

    // The bounding box of the entity and whether it is colliding with another entity.
    public Rectangle boundingBox;
    public boolean isColliding = false;

    public void update() {}
    public void draw(Graphics2D g2d) {}
    public void getImages() {}

    public int spriteFramesCounter = 0; // Frames that has passed since the last sprite change.
    public int spriteImageNum = 1; // The current sprite image number.
}
