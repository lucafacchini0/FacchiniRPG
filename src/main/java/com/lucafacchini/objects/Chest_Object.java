package com.lucafacchini.objects;

import javax.imageio.ImageIO;

public class Chest_Object extends SuperObject {
    public Chest_Object() {
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }

        isSolid = true;
    }
}