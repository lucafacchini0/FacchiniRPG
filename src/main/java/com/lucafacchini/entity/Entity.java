package com.lucafacchini.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public final int MAX_SPRITES_PER_WALKING_DIRECTION = 6;
    public final int MAX_SPRITES_PER_IDLING_DIRECTION = 4;

    public int worldX, worldY;
    public int speed;

    public BufferedImage[] upImages = new BufferedImage[MAX_SPRITES_PER_WALKING_DIRECTION];
    public BufferedImage[] downImages = new BufferedImage[MAX_SPRITES_PER_WALKING_DIRECTION];
    public BufferedImage[] leftImages = new BufferedImage[MAX_SPRITES_PER_WALKING_DIRECTION];
    public BufferedImage[] rightImages = new BufferedImage[MAX_SPRITES_PER_WALKING_DIRECTION];

    public BufferedImage[] idlingDownImages = new BufferedImage[MAX_SPRITES_PER_IDLING_DIRECTION];
    public BufferedImage[] idlingUpImages = new BufferedImage[MAX_SPRITES_PER_IDLING_DIRECTION];
    public BufferedImage[] idlingLeftImages = new BufferedImage[MAX_SPRITES_PER_IDLING_DIRECTION];
    public BufferedImage[] idlingRightImages = new BufferedImage[MAX_SPRITES_PER_IDLING_DIRECTION];

    public String currentDirection;

    // The bounding box of the entity and whether it is colliding with another entity.
    public Rectangle boundingBox;
    public int boundingBoxDefaultX, boundingBoxDefaultY;
    public boolean isCollidingWithTile = false;
    public boolean isCollidingWithObject = false;

    public void update() {}
    public void draw(Graphics2D g2d) {}
    public void getImages() {}

    public int spriteFramesCounter = 0; // Frames that has passed since the last sprite change.
    public int spriteImageNum = 1; // The current sprite image number.


}