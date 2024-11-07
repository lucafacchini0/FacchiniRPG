package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager {

    // Constants for the total number of unique tiles in the game.
    public final int TOTAL_UNIQUE_TILES = 6;

    // The gameMap of the game.
    public final int[][] gameMap;

    // Map paths
    private final String MAP_PATH = "/assets/maps/";
    
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

            // The x and y position of the tile in the game world.
            // This is the actual position of the tile in the game world.
            int worldX = currentWorldColumn * gp.TILE_SIZE; // The x position of the tile in the game world
            int worldY = currentWorldRow * gp.TILE_SIZE; // The y position of the tile in the game world

            // The x and y position of the tile displayed on the screen.
            // This is the position of the tile on the screen.
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
}


