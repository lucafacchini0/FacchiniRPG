package main;

import entity.Entity;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    // Helper method to check for tile collisions
    private void checkTileCollision(Entity entity, int entityLeftColumn, int entityRightColumn, int entityTopRow, int entityBottomRow) {
        int tileNum1, tileNum2;

        // Check the left and right sides of the entity
        tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityTopRow];
        tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityTopRow];
        if (gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
            entity.isColliding = true;
        }

        // Check the bottom side of the entity (for vertical movement)
        tileNum1 = gp.tileManager.gameMap[entityLeftColumn][entityBottomRow];
        tileNum2 = gp.tileManager.gameMap[entityRightColumn][entityBottomRow];
        if (gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision) {
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

        // Handle movement directions
        switch (entity.currentDirection) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            // Handling Diagonal movement
            case "up-left":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "up-right":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "down-left":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
            case "down-right":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                checkTileCollision(entity, entityLeftColumn, entityRightColumn, entityTopRow, entityBottomRow);
                break;
        }
    }
}
