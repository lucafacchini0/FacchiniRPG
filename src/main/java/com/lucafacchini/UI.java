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

        Key_Object key_object = new Key_Object(gp, new Utilities());
        keyImage = key_object.image;
    }

    public void showMessage(String text) {
    }

    public void draw(Graphics2D g2d) {
    }
}