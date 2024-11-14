package com.lucafacchini.objects;

import com.lucafacchini.GamePanel;
import com.lucafacchini.Utilities;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Chest_Object extends SuperObject {
    public Chest_Object(GamePanel gp, Utilities utilities) {

        name = "Chest";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            image = utilities.rescaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        isSolid = true;
    }
}