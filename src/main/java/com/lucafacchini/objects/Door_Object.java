package com.lucafacchini.objects;

import com.lucafacchini.GamePanel;
import com.lucafacchini.Utilities;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Door_Object extends SuperObject {
    public Door_Object(GamePanel gp, Utilities utilities) {

        name = "Door";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            image = utilities.rescaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isSolid = true;
    }
}