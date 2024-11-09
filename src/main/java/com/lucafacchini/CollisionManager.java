package com.lucafacchini;

import com.lucafacchini.entity.Entity;
import com.lucafacchini.objects.SuperObject;

import java.awt.*;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    // Tile collision detection
    private boolean isTileColliding(int tileNum1, int tileNum2) {
        return gp.tileManager.tileType[tileNum1].collision || gp.tileManager.tileType[tileNum2].collision;
    }

    private Rectangle getTileBounds(int column, int row) {
        return new Rectangle(column * gp.TILE_SIZE, row * gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE);
    }

    private void checkTileCollision(Entity entity, Rectangle entityBounds) {
        for (int row = 0; row < gp.tileManager.gameMap.length; row++) {
            for (int column = 0; column < gp.tileManager.gameMap[0].length; column++) {
                if (isTileColliding(gp.tileManager.gameMap[column][row], gp.tileManager.gameMap[column][row])) {
                    Rectangle tileBounds = getTileBounds(column, row);
                    if (entityBounds.intersects(tileBounds)) {
                        entity.isCollidingWithTile = true;
                        return;
                    }
                }
            }
        }
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.boundingBox.x;
        int entityRightWorldX = entity.worldX + entity.boundingBox.x + entity.boundingBox.width;
        int entityTopWorldY = entity.worldY + entity.boundingBox.y;
        int entityBottomWorldY = entity.worldY + entity.boundingBox.y + entity.boundingBox.height;

        Rectangle entityBounds = new Rectangle(entityLeftWorldX, entityTopWorldY, entityRightWorldX - entityLeftWorldX, entityBottomWorldY - entityTopWorldY);

        switch (entity.currentDirection) {
            case "up":
                entityBounds.y -= entity.speed;
                break;
            case "down":
                entityBounds.y += entity.speed;
                break;
            case "left":
                entityBounds.x -= entity.speed;
                break;
            case "right":
                entityBounds.x += entity.speed;
                break;
            case "up-left":
                entityBounds.y -= entity.speed;
                entityBounds.x -= entity.speed;
                break;
            case "up-right":
                entityBounds.y -= entity.speed;
                entityBounds.x += entity.speed;
                break;
            case "down-left":
                entityBounds.y += entity.speed;
                entityBounds.x -= entity.speed;
                break;
            case "down-right":
                entityBounds.y += entity.speed;
                entityBounds.x += entity.speed;
                break;
        }
        checkTileCollision(entity, entityBounds);
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

    public boolean isCollidingFromBottom(Entity entity) {
        int entityBottomWorldY = entity.worldY + entity.boundingBox.y + entity.boundingBox.height;
        int nextBottomWorldY = entityBottomWorldY + entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int bottomTile = nextBottomWorldY / gp.TILE_SIZE;

        return isTileColliding(gp.tileManager.gameMap[leftTile][bottomTile], gp.tileManager.gameMap[rightTile][bottomTile]);
    }

    public boolean isCollidingFromTop(Entity entity) {
        int entityTopWorldY = entity.worldY + entity.boundingBox.y;
        int nextTopWorldY = entityTopWorldY - entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int topTile = nextTopWorldY / gp.TILE_SIZE;

        return isTileColliding(gp.tileManager.gameMap[leftTile][topTile], gp.tileManager.gameMap[rightTile][topTile]);
    }



}