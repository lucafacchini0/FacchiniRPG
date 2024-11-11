package com.lucafacchini;

import com.lucafacchini.entity.Entity;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    private boolean isTileColliding(int... tileNums) {
        for (int tileNum : tileNums) {
            if (tileNum >= 0) {
                if (gp.firstLayerMap.tileMap.get(tileNum) != null && gp.firstLayerMap.tileMap.get(tileNum).isSolid) {
                    return true;
                }
                if (gp.secondLayerMap.tileMap.get(tileNum) != null && gp.secondLayerMap.tileMap.get(tileNum).isSolid) {
                    return true;
                }
                if (gp.thirdLayerMap.tileMap.get(tileNum) != null && gp.thirdLayerMap.tileMap.get(tileNum).isSolid) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkTileCollision(Entity entity, int entityLeftColumn, int entityRightColumn, int entityTopRow, int entityBottomRow) {
        int[] topTiles = {
                gp.firstLayerMap.GAME_MAP[entityLeftColumn][entityTopRow],
                gp.firstLayerMap.GAME_MAP[entityRightColumn][entityTopRow],
                gp.secondLayerMap.GAME_MAP[entityLeftColumn][entityTopRow],
                gp.secondLayerMap.GAME_MAP[entityRightColumn][entityTopRow],
                gp.thirdLayerMap.GAME_MAP[entityLeftColumn][entityTopRow],
                gp.thirdLayerMap.GAME_MAP[entityRightColumn][entityTopRow]
        };

        int[] bottomTiles = {
                gp.firstLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow],
                gp.firstLayerMap.GAME_MAP[entityRightColumn][entityBottomRow],
                gp.secondLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow],
                gp.secondLayerMap.GAME_MAP[entityRightColumn][entityBottomRow],
                gp.thirdLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow],
                gp.thirdLayerMap.GAME_MAP[entityRightColumn][entityBottomRow]
        };

        if (isTileColliding(topTiles) || isTileColliding(bottomTiles)) {
            entity.isCollidingWithTile = true;
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

        return isTileColliding(
                gp.firstLayerMap.GAME_MAP[leftTile][topTile], gp.firstLayerMap.GAME_MAP[leftTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[leftTile][topTile], gp.secondLayerMap.GAME_MAP[leftTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][topTile], gp.thirdLayerMap.GAME_MAP[leftTile][bottomTile]
        );
    }

    public boolean isCollidingFromRight(Entity entity) {
        int entityRightWorldX = entity.worldX + entity.boundingBox.x + entity.boundingBox.width;
        int nextRightWorldX = entityRightWorldX + entity.speed;

        int rightTile = nextRightWorldX / gp.TILE_SIZE;
        int topTile = (entity.worldY + entity.boundingBox.y) / gp.TILE_SIZE;
        int bottomTile = (entity.worldY + entity.boundingBox.y + entity.boundingBox.height) / gp.TILE_SIZE;

        return isTileColliding(
                gp.firstLayerMap.GAME_MAP[rightTile][topTile], gp.firstLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[rightTile][topTile], gp.secondLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[rightTile][topTile], gp.thirdLayerMap.GAME_MAP[rightTile][bottomTile]
        );
    }

    public boolean isCollidingFromBottom(Entity entity) {
        int entityBottomWorldY = entity.worldY + entity.boundingBox.y + entity.boundingBox.height;
        int nextBottomWorldY = entityBottomWorldY + entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int bottomTile = nextBottomWorldY / gp.TILE_SIZE;

        return isTileColliding(
                gp.firstLayerMap.GAME_MAP[leftTile][bottomTile], gp.firstLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[leftTile][bottomTile], gp.secondLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][bottomTile], gp.thirdLayerMap.GAME_MAP[rightTile][bottomTile]
        );
    }

    public boolean isCollidingFromTop(Entity entity) {
        int entityTopWorldY = entity.worldY + entity.boundingBox.y;
        int nextTopWorldY = entityTopWorldY - entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int topTile = nextTopWorldY / gp.TILE_SIZE;

        return isTileColliding(
                gp.firstLayerMap.GAME_MAP[leftTile][topTile], gp.firstLayerMap.GAME_MAP[rightTile][topTile],
                gp.secondLayerMap.GAME_MAP[leftTile][topTile], gp.secondLayerMap.GAME_MAP[rightTile][topTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][topTile], gp.thirdLayerMap.GAME_MAP[rightTile][topTile]
        );
    }

    // Objects
    public int checkObject(Entity entity, boolean isPlayer) {
        int index = -1;

        for (int i = 0; i < gp.objectsArray.length; i++) {
            if (gp.objectsArray[i] != null) {
                entity.boundingBox.x = entity.worldX + entity.boundingBox.x;
                entity.boundingBox.y = entity.worldY + entity.boundingBox.y;

                gp.objectsArray[i].boundingBox.x = gp.objectsArray[i].worldX + gp.objectsArray[i].boundingBox.x;
                gp.objectsArray[i].boundingBox.y = gp.objectsArray[i].worldY + gp.objectsArray[i].boundingBox.y;

                switch (entity.currentDirection) {
                    case "up-left":
                        entity.boundingBox.x -= entity.speed;
                        entity.boundingBox.y -= entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "up-right":
                        entity.boundingBox.x += entity.speed;
                        entity.boundingBox.y -= entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down-left":
                        entity.boundingBox.x -= entity.speed;
                        entity.boundingBox.y += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down-right":
                        entity.boundingBox.x += entity.speed;
                        entity.boundingBox.y += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "up":
                        entity.boundingBox.y -= entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.boundingBox.y += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.boundingBox.x -= entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.boundingBox.x += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if (gp.objectsArray[i].isSolid) {
                                entity.isCollidingWithObject = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.boundingBox.x = entity.boundingBoxDefaultX;
                entity.boundingBox.y = entity.boundingBoxDefaultY;
                gp.objectsArray[i].boundingBox.x = gp.objectsArray[i].boundingBoxDefaultX;
                gp.objectsArray[i].boundingBox.y = gp.objectsArray[i].boundingBoxDefaultY;
            }
        }
        return index;
    }
}