package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public final int MAX_SPRITES_PER_DIRECTION = 2;

    public BufferedImage[] upImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] downImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] leftImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];
    public BufferedImage[] rightImages = new BufferedImage[MAX_SPRITES_PER_DIRECTION];

    public String currentDirection;

    public void update() {}
    public void draw(Graphics2D g2d) {}
    public void getImages() {}

    public int spriteFramesCounter = 0;
    public int spriteImageNum = 1;
}
