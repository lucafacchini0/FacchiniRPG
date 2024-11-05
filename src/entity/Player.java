package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        setDefaultValues();
    }

    void setDefaultValues() {
        x = 250;
        y = 250;
        speed = gamePanel.DEFAULT_PLAYER_SPEED;
    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (keyHandler.isLeftPressed) {
            deltaX -= speed;
        }
        if (keyHandler.isRightPressed) {
            deltaX += speed;
        }
        if (keyHandler.isUpPressed) {
            deltaY -= speed;
        }
        if (keyHandler.isDownPressed) {
            deltaY += speed;
        }

        // Normalize the speed when moving diagonally
        if (deltaX != 0 && deltaY != 0) {
            deltaX /= Math.sqrt(2);
            deltaY /= Math.sqrt(2);
        }

        x += deltaX;
        y += deltaY;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE); // Draw the player.
    }
}
