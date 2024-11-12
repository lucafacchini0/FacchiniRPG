package com.lucafacchini.objects;

import com.lucafacchini.entity.Utilities;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Door_Object extends SuperObject {
    public Door_Object() {
        name = "Door";

        Utilities utilities = new Utilities();

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch (Exception e) {
            utilities.printError(this.getClass().getSimpleName(), "Failed to load image.", "\u001B[31m");
            e.printStackTrace();
        }

        isSolid = true;
    }
}