package com.lucafacchini;

import com.lucafacchini.objects.Door_Object;
import com.lucafacchini.objects.Key_Object;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void placeObject() {
        gp.objectsArray[0] = new Key_Object();
        gp.objectsArray[0].worldX = 2 * gp.TILE_SIZE;
        gp.objectsArray[0].worldY = 2 * gp.TILE_SIZE;

        gp.objectsArray[1] = new Key_Object();
        gp.objectsArray[1].worldX = 5 * gp.TILE_SIZE;
        gp.objectsArray[1].worldY = 6 * gp.TILE_SIZE;

        gp.objectsArray[2] = new Door_Object();
        gp.objectsArray[2].worldX = 8 * gp.TILE_SIZE;
        gp.objectsArray[2].worldY = 8 * gp.TILE_SIZE;
    }
}