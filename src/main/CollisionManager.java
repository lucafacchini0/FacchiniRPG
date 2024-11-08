package main;

import entity.Entity;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    private boolean isTileColliding(int tileNum1, int tileNum2) {
        return gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision;
    }

    private void checkTileCollision(Entity entity, int entityLeftColumn, int entityRightColumn, int entityTopRow, int entityBottomRow) {
        int tileNum1, tileNum2;

        // Check top side
        tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityTopRow];
        tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityTopRow];
        if (isTileColliding(tileNum1, tileNum2)) {
            entity.isColliding = true;
        }

        // Check bottom side
        tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityBottomRow];
        tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityBottomRow];
        if (isTileColliding(tileNum1, tileNum2)) {
            entity.isColliding = true;
        }
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

        switch (entity.currentDirection) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                break;
            case "up-left":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                break;
            case "up-right":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                break;
            case "down-left":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                break;
            case "down-right":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                break;
        }
        checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
    }

    public boolean isCollidingFromLeft(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.boundingBox.x;
        int nextLeftWorldX = entityLeftWorldX - entity.speed;

        int leftTile = nextLeftWorldX / gp.TILE_SIZE;
        int topTile = (entity.worldY + entity.boundingBox.y) / gp.TILE_SIZE;
        int bottomTile = (entity.worldY + entity.boundingBox.y + entity.boundingBox.height) / gp.TILE_SIZE;

        return isTileColliding(gp.tileManager.gameMap[leftTile][topTile], gp.tileManager.gameMap[leftTile][bottomTile]);
    }

    public boolean isCollidingFromRight(Entity entity) {
        int entityRightWorldX = entity.worldX + entity.boundingBox.x + entity.boundingBox.width;
        int nextRightWorldX = entityRightWorldX + entity.speed;

        int rightTile = nextRightWorldX / gp.TILE_SIZE;
        int topTile = (entity.worldY + entity.boundingBox.y) / gp.TILE_SIZE;
        int bottomTile = (entity.worldY + entity.boundingBox.y + entity.boundingBox.height) / gp.TILE_SIZE;

        return isTileColliding(gp.tileManager.gameMap[rightTile][topTile], gp.tileManager.gameMap[rightTile][bottomTile]);
    }

}
