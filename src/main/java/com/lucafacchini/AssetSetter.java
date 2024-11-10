package com.lucafacchini;

import com.lucafacchini.objects.Boots_Object;
import com.lucafacchini.objects.Chest_Object;
import com.lucafacchini.objects.Door_Object;
import com.lucafacchini.objects.Key_Object;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void placeObject() {
        gp.objectsArray[0] = new Key_Object();
        gp.objectsArray[0].worldX = 22 * gp.TILE_SIZE;
        gp.objectsArray[0].worldY = 22 * gp.TILE_SIZE;

        gp.objectsArray[1] = new Key_Object();
        gp.objectsArray[1].worldX = 20 * gp.TILE_SIZE;
        gp.objectsArray[1].worldY = 20 * gp.TILE_SIZE;

        gp.objectsArray[2] = new Door_Object();
        gp.objectsArray[2].worldX = 24 * gp.TILE_SIZE;
        gp.objectsArray[2].worldY = 17 * gp.TILE_SIZE;

        gp.objectsArray[3] = new Boots_Object();
        gp.objectsArray[3].worldX = 30 * gp.TILE_SIZE;
        gp.objectsArray[3].worldY = 31 * gp.TILE_SIZE;
    }
}