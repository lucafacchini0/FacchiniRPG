package com.lucafacchini.tiles;
import com.lucafacchini.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class TileManager {

    // Constants for the total number of unique tiles in the game.
    public final int TOTAL_UNIQUE_TILES = 6;

    // The gameMap of the game.
    public final int[][] gameMap;

    // Map paths
    private final String MAP_PATH = "/maps/";

    GamePanel gp;
    public Tile[] tileType; // This will contain all the unique tiles in the game

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tileType = new Tile[TOTAL_UNIQUE_TILES];
        gameMap = new int[gp.MAX_WORLD_COLUMNS][gp.MAX_WORLD_ROWS];

        loadTileImages();
        loadMap(MAP_PATH + "default.txt");
    }

    private void loadTileImages() {
        try {
            // Constants for the tile indexes
            final int GRASS1_INDEX = 0;
            final int WALL1_INDEX = 1;
            final int WATER1_INDEX = 2;
            final int TREE_INDEX = 3;
            final int DIRT_INDEX = 4;
            final int SAND_INDEX = 5;

            tileType[GRASS1_INDEX] = new Tile();
            tileType[GRASS1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/GRASS1.png")));

            tileType[WALL1_INDEX] = new Tile();
            tileType[WALL1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WALL1.png")));
            tileType[WALL1_INDEX].collision = true;

            tileType[WATER1_INDEX] = new Tile();
            tileType[WATER1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WATER1.png")));
            tileType[WATER1_INDEX].collision = true;

            tileType[TREE_INDEX] = new Tile();
            tileType[TREE_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/TREE1.png")));
            tileType[TREE_INDEX].collision = true;

            tileType[DIRT_INDEX] = new Tile();
            tileType[DIRT_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/DIRT1.png")));

            tileType[SAND_INDEX] = new Tile();
            tileType[SAND_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/SAND1.png")));


        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Reads the map file and loads the map into the gameMap array.
    public void loadMap(String filePath) {
        try {
            InputStream inputFile = getClass().getResourceAsStream(filePath);
            assert inputFile != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));

            int currentWorldColumn = 0;
            int currentWorldRow = 0;

            while(currentWorldRow < gp.MAX_WORLD_ROWS) {
                String line = reader.readLine();

                while(currentWorldColumn < gp.MAX_WORLD_COLUMNS) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[currentWorldColumn]);
                    gameMap[currentWorldColumn][currentWorldRow] = number;
                    currentWorldColumn++;
                }

                currentWorldColumn = 0;
                currentWorldRow++;
            }
            reader.close();
        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void draw(Graphics2D g2d) {

        int currentWorldColumn = 0;
        int currentWorldRow = 0;

        while(currentWorldColumn < gp.MAX_WORLD_COLUMNS && currentWorldRow < gp.MAX_WORLD_ROWS) {

            int currentTileIndex = gameMap[currentWorldColumn][currentWorldRow];

            int worldX = currentWorldColumn * gp.TILE_SIZE; // The x position of the tile in the game world
            int worldY = currentWorldRow * gp.TILE_SIZE; // The y position of the tile in the game world

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw the tile if it is within the screen bounds.
            // player.worldX and player.worldY are the player's coordinates in the game world.
            // worldX and worldY are the coordinates of the tile in the game world.
            if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
                g2d.drawImage(tileType[currentTileIndex].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }

            currentWorldColumn++;

            if(currentWorldColumn == gp.MAX_WORLD_COLUMNS) {

                currentWorldRow++;
                currentWorldColumn = 0;
            }
        }
    }

    public List<Rectangle> getCollisionTiles(int playerX, int playerY, int tileSize, int range) {
        List<Rectangle> collisionTiles = new ArrayList<>();

        int startCol = Math.max((playerX - range) / tileSize, 0);
        int endCol = Math.min((playerX + range) / tileSize, gp.MAX_WORLD_COLUMNS - 1);
        int startRow = Math.max((playerY - range) / tileSize, 0);
        int endRow = Math.min((playerY + range) / tileSize, gp.MAX_WORLD_ROWS - 1);

        for (int col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                int tileIndex = gameMap[col][row];
                Tile tile = tileType[tileIndex];
                if (tile.collision) {
                    int tileX = col * tileSize;
                    int tileY = row * tileSize;
                    collisionTiles.add(new Rectangle(tileX, tileY, tileSize, tileSize));
                }
            }
        }
        return collisionTiles;
    }

}

