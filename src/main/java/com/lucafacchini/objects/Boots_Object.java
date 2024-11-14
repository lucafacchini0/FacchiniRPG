package com.lucafacchini.objects;

import com.lucafacchini.Utilities;
import com.lucafacchini.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Boots_Object extends SuperObject {
    public Boots_Object(GamePanel gp, Utilities utilities) {

        name = "Boots";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            image = utilities.rescaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}