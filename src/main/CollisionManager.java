package main;

import entity.Entity;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.boundingBox.x;
        int entityRightWorldX = entity.worldX + entity.boundingBox.x + entity.boundingBox.width;
        int entityTopWorldY = entity.worldY + entity.boundingBox.y;
        int entityBottomWorldY = entity.worldY + entity.boundingBox.y + entity.boundingBox.height;

        int entityLeftColumn = entityLeftWorldX / gp.TILE_SIZE;
        int entityRightColumn = entityRightWorldX / gp.TILE_SIZE;
        int entityTopRow = entityTopWorldY / gp.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch(entity.currentDirection) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;

                tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityTopRow];
                if(gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
                    entity.isColliding = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;

                tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityBottomRow];
                tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityBottomRow];
                if(gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
                    entity.isColliding = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;

                tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileManager.gameMap[entityLeftColumn][entityBottomRow];
                if(gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
                    entity.isColliding = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;

                tileNum1 = gp.tileManager.gameMap[entityRightColumn][entityTopRow];
                tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityBottomRow];
                if(gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
                    entity.isColliding = true;
                }
                break;
        }
    }
}
