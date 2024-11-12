package com.lucafacchini.objects;

import com.lucafacchini.Utilities;
import com.lucafacchini.GamePanel;

import javax.imageio.ImageIO;

public class Key_Object extends SuperObject {
    public Key_Object(GamePanel gp, Utilities utilities) {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image = utilities.rescaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}