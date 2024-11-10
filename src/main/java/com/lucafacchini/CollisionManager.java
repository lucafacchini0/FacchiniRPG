package com.lucafacchini;

import com.lucafacchini.entity.Entity;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    private boolean isTileColliding(int tileNum1, int tileNum2, int tileNum3, int tileNum4, int tileNum5, int tileNum6) {
        boolean isSolid = false;

        if (tileNum1 >= 0 && gp.firstLayerMap.tileUnique[tileNum1].isSolid) {
            isSolid = true;
        }
        if (tileNum2 >= 0 && gp.firstLayerMap.tileUnique[tileNum2].isSolid) {
            isSolid = true;
        }
        if (tileNum3 >= 0 && gp.secondLayerMap.tileUnique[tileNum3].isSolid) {
            isSolid = true;
        }
        if (tileNum4 >= 0 && gp.secondLayerMap.tileUnique[tileNum4].isSolid) {
            isSolid = true;
        }
        if (tileNum5 >= 0 && gp.thirdLayerMap.tileUnique[tileNum5].isSolid) {
            isSolid = true;
        }
        if (tileNum6 >= 0 && gp.thirdLayerMap.tileUnique[tileNum6].isSolid) {
            isSolid = true;
        }

        return isSolid;
    }

    private void checkTileCollision(Entity entity, int entityLeftColumn, int entityRightColumn, int entityTopRow, int entityBottomRow) {
        int tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6;

        // Check top side
        tileNum1 = gp.firstLayerMap.GAME_MAP[entityLeftColumn][entityTopRow];
        tileNum2 = gp.firstLayerMap.GAME_MAP[entityRightColumn][entityTopRow];
        tileNum3 = gp.secondLayerMap.GAME_MAP[entityLeftColumn][entityTopRow];
        tileNum4 = gp.secondLayerMap.GAME_MAP[entityRightColumn][entityTopRow];
        tileNum5 = gp.thirdLayerMap.GAME_MAP[entityLeftColumn][entityTopRow];
        tileNum6 = gp.thirdLayerMap.GAME_MAP[entityRightColumn][entityTopRow];

        if (isTileColliding(tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6)) {
            entity.isCollidingWithTile = true;
        }

        // Check bottom side
        tileNum1 = gp.firstLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow];
        tileNum2 = gp.firstLayerMap.GAME_MAP[entityRightColumn][entityBottomRow];
        tileNum3 = gp.secondLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow];
        tileNum4 = gp.secondLayerMap.GAME_MAP[entityRightColumn][entityBottomRow];
        tileNum5 = gp.thirdLayerMap.GAME_MAP[entityLeftColumn][entityBottomRow];
        tileNum6 = gp.thirdLayerMap.GAME_MAP[entityRightColumn][entityBottomRow];

        if (isTileColliding(tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6)) {
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

        return isTileColliding(gp.firstLayerMap.GAME_MAP[leftTile][topTile], gp.firstLayerMap.GAME_MAP[leftTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[leftTile][topTile], gp.secondLayerMap.GAME_MAP[leftTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][topTile], gp.thirdLayerMap.GAME_MAP[leftTile][bottomTile]);
    }

    public boolean isCollidingFromRight(Entity entity) {
        int entityRightWorldX = entity.worldX + entity.boundingBox.x + entity.boundingBox.width;
        int nextRightWorldX = entityRightWorldX + entity.speed;

        int rightTile = nextRightWorldX / gp.TILE_SIZE;
        int topTile = (entity.worldY + entity.boundingBox.y) / gp.TILE_SIZE;
        int bottomTile = (entity.worldY + entity.boundingBox.y + entity.boundingBox.height) / gp.TILE_SIZE;

        return isTileColliding(gp.firstLayerMap.GAME_MAP[rightTile][topTile], gp.firstLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[rightTile][topTile], gp.secondLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[rightTile][topTile], gp.thirdLayerMap.GAME_MAP[rightTile][bottomTile]);
    }

    public boolean isCollidingFromBottom(Entity entity) {
        int entityBottomWorldY = entity.worldY + entity.boundingBox.y + entity.boundingBox.height;
        int nextBottomWorldY = entityBottomWorldY + entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int bottomTile = nextBottomWorldY / gp.TILE_SIZE;

        return isTileColliding(gp.firstLayerMap.GAME_MAP[leftTile][bottomTile], gp.firstLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.secondLayerMap.GAME_MAP[leftTile][bottomTile], gp.secondLayerMap.GAME_MAP[rightTile][bottomTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][bottomTile], gp.thirdLayerMap.GAME_MAP[rightTile][bottomTile]);
    }

    public boolean isCollidingFromTop(Entity entity) {
        int entityTopWorldY = entity.worldY + entity.boundingBox.y;
        int nextTopWorldY = entityTopWorldY - entity.speed;

        int leftTile = (entity.worldX + entity.boundingBox.x) / gp.TILE_SIZE;
        int rightTile = (entity.worldX + entity.boundingBox.x + entity.boundingBox.width) / gp.TILE_SIZE;
        int topTile = nextTopWorldY / gp.TILE_SIZE;

        return isTileColliding(gp.firstLayerMap.GAME_MAP[leftTile][topTile], gp.firstLayerMap.GAME_MAP[rightTile][topTile],
                gp.secondLayerMap.GAME_MAP[leftTile][topTile], gp.secondLayerMap.GAME_MAP[rightTile][topTile],
                gp.thirdLayerMap.GAME_MAP[leftTile][topTile], gp.thirdLayerMap.GAME_MAP[rightTile][topTile]);
    }

    // Objects
    public int checkObject(Entity entity, boolean isPlayer) {
        int index = -1;

        for(int i = 0; i < gp.objectsArray.length; i++) {
            if(gp.objectsArray[i] != null) {
                entity.boundingBox.x = entity.worldX + entity.boundingBox.x;
                entity.boundingBox.y = entity.worldY + entity.boundingBox.y;

                gp.objectsArray[i].boundingBox.x = gp.objectsArray[i].worldX + gp.objectsArray[i].boundingBox.x;
                gp.objectsArray[i].boundingBox.y = gp.objectsArray[i].worldY + gp.objectsArray[i].boundingBox.y;

                switch(entity.currentDirection) {
                    case "up-left":
                        entity.boundingBox.x -= entity.speed;
                        entity.boundingBox.y -= entity.speed;
                        if(entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }

                        break;
                    case "up-right":
                        entity.boundingBox.x += entity.speed;
                        entity.boundingBox.y -= entity.speed;
                        if(entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down-left":
                        entity.boundingBox.x -= entity.speed;
                        entity.boundingBox.y += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down-right":
                        entity.boundingBox.x += entity.speed;
                        entity.boundingBox.y += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "up":
                        entity.boundingBox.y -= entity.speed;
                        if(entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.boundingBox.y += entity.speed;
                        if(entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;

                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.boundingBox.x -= entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;
                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.boundingBox.x += entity.speed;
                        if (entity.boundingBox.intersects(gp.objectsArray[i].boundingBox)) {
                            if(gp.objectsArray[i].isSolid) {
                                // Debug message
                                entity.isCollidingWithObject = true;
                            }
                            if(isPlayer) {
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