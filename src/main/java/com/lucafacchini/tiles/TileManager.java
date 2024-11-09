package com.lucafacchini.tiles;
import com.lucafacchini.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager {

    // Coordinates
    public int worldX, worldY;
    public int screenX, screenY;

    // Costants
    public final int TOTAL_UNIQUE_TILES = 6;
    public final String MAPS_PATH = "/maps/";
    public final int[][] GAME_MAP;

    // Objects
    GamePanel gp;
    public Tile[] tileUnique; // This will contain all the unique tiles in the game

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tileUnique = new Tile[TOTAL_UNIQUE_TILES];
        GAME_MAP = new int[gp.MAX_WORLD_COLUMNS][gp.MAX_WORLD_ROWS];

        loadTileImages();
        loadMap(MAPS_PATH + "default.txt");
    }

    private void loadTileImages() {
        try {
            final int GRASS1_INDEX = 0;
            final int WALL1_INDEX = 1;
            final int WATER1_INDEX = 2;
            final int TREE1_INDEX = 3;
            final int DIRT1_INDEX = 4;
            final int SAND1_INDEX = 5;

            tileUnique[GRASS1_INDEX] = new Tile();
            tileUnique[GRASS1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/GRASS1.png")));

            tileUnique[WALL1_INDEX] = new Tile();
            tileUnique[WALL1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WALL1.png")));
            tileUnique[WALL1_INDEX].isSolid = true;

            tileUnique[WATER1_INDEX] = new Tile();
            tileUnique[WATER1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WATER1.png")));
            tileUnique[WATER1_INDEX].isSolid = true;

            tileUnique[TREE1_INDEX] = new Tile();
            tileUnique[TREE1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/TREE1.png")));
            tileUnique[TREE1_INDEX].isSolid = true;

            tileUnique[DIRT1_INDEX] = new Tile();
            tileUnique[DIRT1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/DIRT1.png")));

            tileUnique[SAND1_INDEX] = new Tile();
            tileUnique[SAND1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/SAND1.png")));


        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
    }

    public void draw(Graphics2D g2d) {

        int currentWorldColumn = 0;
        int currentWorldRow = 0;

        while(currentWorldColumn < gp.MAX_WORLD_COLUMNS && currentWorldRow < gp.MAX_WORLD_ROWS) {

            int currentTileIndex = GAME_MAP[currentWorldColumn][currentWorldRow];

            // These are static that represent the position of the tile in the game world.
            worldX = currentWorldColumn * gp.TILE_SIZE; // The x position of the tile in the game world
            worldY = currentWorldRow * gp.TILE_SIZE; // The y position of the tile in the game world

            // This is the tile representation on the screen! Image the tile moving on the screen while the
            // player moves in the game world. This is the position of the tile on the screen.
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw the tile if it is within the screen bounds.
            // screenX and screenY must be within the screen bounds.
            if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
                g2d.drawImage(tileUnique[currentTileIndex].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }

            currentWorldColumn++;

            if(currentWorldColumn == gp.MAX_WORLD_COLUMNS) {
                currentWorldRow++;
                currentWorldColumn = 0;
            }

            // ##TROUBLESHOOTING ##IMPORTANT
//            g2d.setColor(Color.pink);
//            g2d.drawRect(screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE);
        }
    }
}
