package com.lucafacchini.tiles;
import com.lucafacchini.GamePanel;
import com.lucafacchini.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager {

    // ------------------- Fields -------------------

    // Coordinates
    public static int worldX, worldY;
    public static int screenX, screenY;

    // Map management
    public static final String MAPS_PATH = "/maps/";
    public HashMap<Integer, Tile> tileMap; // Store all the tiles
    public final int[][] GAME_MAP; // Store the actual map

    // Objects
    GamePanel gp;
    private final Utilities utilities = new Utilities();



    // ------------------- Constructor -------------------

    public TileManager(GamePanel gp, String path) {
        this.gp = gp;
        GAME_MAP = new int[gp.MAX_WORLD_COLUMNS][gp.MAX_WORLD_ROWS];
        tileMap = new HashMap<>();

        loadMap(MAPS_PATH + path);

       rescaleAllTileImages();

        // TODO: Implement a way to set the solid tiles
        // [ DEBUG ]
      //  setSolid(38193);
    }



    // ------------------- Tile Loading -------------------

    public void loadMap(String filePath) {
        try {
            InputStream inputFile = getClass().getResourceAsStream(filePath);
            assert inputFile != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));

            int currentWorldColumn = 0;
            int currentWorldRow = 0;

            while (currentWorldRow < gp.MAX_WORLD_ROWS) {
                String line = reader.readLine();
                if (line == null) break; // Stop if no more lines

                String[] numbers = line.split(",");

                while (currentWorldColumn < gp.MAX_WORLD_COLUMNS && currentWorldColumn < numbers.length) {
                    int number = Integer.parseInt(numbers[currentWorldColumn]);
                    GAME_MAP[currentWorldColumn][currentWorldRow] = number;
                    currentWorldColumn++;
                }
                currentWorldColumn = 0;
                currentWorldRow++;
            }
            reader.close();
        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
        loadAllTileImages();
    }

    // Method to load all tile images
    private void loadAllTileImages() {
        for (int row = 0; row < gp.MAX_WORLD_ROWS; row++) {
            for (int col = 0; col < gp.MAX_WORLD_COLUMNS; col++) {
                int tileID = GAME_MAP[col][row];
                if (tileID != -1) {
                    loadTileImage(tileID);
                }
            }
        }
    }

    // Load images with the specified ID, if not already loaded
    private void loadTileImage(int id) {
        if (!tileMap.containsKey(id)) {
            try {
                String imagePath = "/tiles/tile_" + id + ".png";
                InputStream imageStream = getClass().getResourceAsStream(imagePath);

                if (imageStream == null) {
                    utilities.printErrorMessage("TILE_MANAGER", "Error: TILE not found for ID: " + id + " (" + imagePath + ")");
                } else {
                    Tile tile = new Tile();
                    tile.image = ImageIO.read(imageStream);
                    if (tile.image != null) {
                        tileMap.put(id, tile);
                        utilities.printSuccess("TILE_MANAGER", "Successfully loaded TILE for ID: " + id);
                    } else {
                        utilities.printErrorMessage("TILE_MANAGER", "Error: Failed to load TILE for ID: " + id + " (" + imagePath + ")");
                    }
                }

            } catch (IOException e) {
                utilities.printErrorMessage("TILE_MANAGER", "Error loading TILE: " + id + " (" + e.getMessage() + ")");
            }
        }
    }




    // ------------------- Tile Rescaling -------------------

    private void rescaleAllTileImages() {
        for (Tile tile : tileMap.values()) {
            if (tile.image != null) {
                tile.image = utilities.rescaleImage(tile.image, gp.TILE_SIZE, gp.TILE_SIZE);
            }
        }
    }



    // ------------------- Tile Setting -------------------

    public void setSolid(int id) {
        Tile tile = tileMap.get(id);
        if (tile != null) {
            tile.isSolid = true;
        }
    }



    // ------------------- Drawing -------------------

    public void draw(Graphics2D g2d) {
        int currentWorldColumn = 0;
        int currentWorldRow = 0;

        while (currentWorldColumn < gp.MAX_WORLD_COLUMNS && currentWorldRow < gp.MAX_WORLD_ROWS) {
            int currentTileIndex = GAME_MAP[currentWorldColumn][currentWorldRow];

            // Skip if the index is -1
            if (currentTileIndex == -1) {
                currentWorldColumn++;
                if (currentWorldColumn == gp.MAX_WORLD_COLUMNS) {
                    currentWorldRow++;
                    currentWorldColumn = 0;
                }
                continue;
            }

            worldX = currentWorldColumn * gp.TILE_SIZE;
            worldY = currentWorldRow * gp.TILE_SIZE;
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw the tile only if it's visible
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                Tile tile = tileMap.get(currentTileIndex);
                if (tile != null && tile.image != null) {
                    g2d.drawImage(tile.image, screenX, screenY, null);
                }
            }

            currentWorldColumn++;

            if (currentWorldColumn == gp.MAX_WORLD_COLUMNS) {
                currentWorldRow++;
                currentWorldColumn = 0;
            }

            // [ DEBUG ]
            // [ WARNING ] - takes long time to draw (around 0.8 ms)
            // g2d.setColor(Color.pink);
            // g2d.drawRect(screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE);
        }
    }
}
