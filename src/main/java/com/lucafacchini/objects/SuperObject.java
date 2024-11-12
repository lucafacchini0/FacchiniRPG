package com.lucafacchini.objects;

import com.lucafacchini.GamePanel;
import com.lucafacchini.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    // ------------------- Fields -------------------

    // Direction and position
    public int worldX, worldY;
    public int screenX, screenY;

    // TODO: Implement a constructor to construct every object faster and resize it automatically.

    // Object Properties
    public boolean isSolid = false;

    // Collision properties
    public Rectangle boundingBox = new Rectangle(0, 0, 64, 64); // TODO: Replace size with gp.TILE_SIZE
    public int boundingBoxDefaultX = 0;
    public int boundingBoxDefaultY = 0;

    // Image and name
    public BufferedImage image;
    public String name;

    // Utilities
    public Utilities utilities = new Utilities();



    // ------------------- Draw Method -------------------

    public void draw(Graphics2D g2d, GamePanel gp) {
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        // If the object is within the screen boundaries, draw it.
        if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
            g2d.drawImage(image, screenX, screenY, null);
        }

        // [ DEBUG ] bounding box
        g2d.setColor(Color.BLACK);
        g2d.drawRect(screenX + boundingBox.x, screenY + boundingBox.y, boundingBox.width, boundingBox.height);
    }
}