package com.lucafacchini;

import com.lucafacchini.objects.Boots_Object;
import com.lucafacchini.objects.Chest_Object;
import com.lucafacchini.objects.Door_Object;
import com.lucafacchini.objects.Key_Object;

import com.lucafacchini.entity.NPC_OldMan;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void placeObject() {
        gp.objectsArray[0] = new Key_Object(gp, new Utilities());
        gp.objectsArray[0].worldX = 9 * gp.TILE_SIZE;
        gp.objectsArray[0].worldY = 14 * gp.TILE_SIZE;

        gp.objectsArray[1] = new Key_Object(gp, new Utilities());
        gp.objectsArray[1].worldX = 18 * gp.TILE_SIZE;
        gp.objectsArray[1].worldY = 8 * gp.TILE_SIZE;

        gp.objectsArray[2] = new Door_Object(gp, new Utilities());
        gp.objectsArray[2].worldX = 23 * gp.TILE_SIZE;
        gp.objectsArray[2].worldY = 34 * gp.TILE_SIZE;

        gp.objectsArray[3] = new Boots_Object(gp, new Utilities());
        gp.objectsArray[3].worldX = 30 * gp.TILE_SIZE;
        gp.objectsArray[3].worldY = 31 * gp.TILE_SIZE;

        gp.objectsArray[4] = new Chest_Object(gp, new Utilities());
        gp.objectsArray[4].worldX = 23 * gp.TILE_SIZE;
        gp.objectsArray[4].worldY = 36 * gp.TILE_SIZE;
    }

    public void placeNPC() {
        gp.npcArray[0] = new NPC_OldMan(gp);
        gp.npcArray[0].worldX = 22 * gp.TILE_SIZE;
        gp.npcArray[0].worldY = 22 * gp.TILE_SIZE;
    }
}