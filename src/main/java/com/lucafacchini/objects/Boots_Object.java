package com.lucafacchini.objects;

import com.lucafacchini.entity.Utilities;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Boots_Object extends SuperObject {
    public Boots_Object() {
        name = "Boots";

        Utilities utilities = new Utilities();

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        } catch (Exception e) {
            utilities.printError(this.getClass().getSimpleName(), "Failed to load image.", "\u001B[31m");
            e.printStackTrace();
        }

    }
}