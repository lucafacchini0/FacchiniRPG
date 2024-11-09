package com.lucafacchini;

import com.lucafacchini.objects.Key_Object;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_30;
    Font arial_50;

    public boolean messageOn = false;
    public String message = "";

    int messageCounter = 0;

    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat df = new DecimalFormat("#0.00");

    BufferedImage keyImage;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_50 = new Font("Arial", Font.PLAIN, 50);

        Key_Object key_object = new Key_Object();
        keyImage = key_object.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {

        if(gameFinished) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

            g2d.setFont(arial_50);
            g2d.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "Congrats!!";
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 - 50;
            g2d.drawString(text, x, y);

            g2d.setFont(arial_30);
            g2d.setColor(Color.YELLOW);
            text = "You have completed the game!";
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 - 150;

            g2d.drawString(text, x, y);

            g2d.setColor(Color.WHITE);
            text = df.format(playTime) + " seconds";

            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            g2d.drawString(text, gp.SCREEN_WIDTH / 2 - textLength / 2, gp.SCREEN_HEIGHT / 2 + 50);

            gp.gameThread = null;

        } else {
            playTime += 1.0 / gp.FPS;

            g2d.setFont(arial_30);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyImage, 10, 10, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2d.drawString("x " + gp.player.hasKey, 80, 60);
            g2d.setColor(Color.RED);
            g2d.drawString("FPS: " + gp.FPS, 10, 150);

            g2d.setColor(Color.WHITE);
            g2d.drawString("Time: " + df.format(playTime), 10, 200);

            if(messageOn) {
                messageCounter++;
                g2d.setColor(Color.WHITE);
                g2d.drawString(message, 10, 100);

                if(messageCounter > 120) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}
