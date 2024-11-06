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
    public final int TOTAL_UNIQUE_TILES = 4;

    // The gameMap of the game.
    private final int[][] gameMap;

    // Map paths
    private final String MAP_PATH = "/assets/maps/";
    
    GamePanel gp;
    Tile[] tileType; // This will contain all the unique tiles in the game

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tileType = new Tile[TOTAL_UNIQUE_TILES];

        gameMap = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];

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

            tileType[GRASS1_INDEX] = new Tile();
            tileType[GRASS1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/GRASS1.png")));

            tileType[WALL1_INDEX] = new Tile();
            tileType[WALL1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WALL1.png")));

            tileType[WATER1_INDEX] = new Tile();
            tileType[WATER1_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/WATER1.png")));

            tileType[TREE_INDEX] = new Tile();
            tileType[TREE_INDEX].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/TREE1.png")));


        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputFile = getClass().getResourceAsStream(filePath);
            assert inputFile != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));

            int currentColumn = 0;
            int currentRow = 0;

            while(currentRow < gp.MAX_SCREEN_ROWS) {

                String line = reader.readLine();

                while(currentColumn < gp.MAX_SCREEN_COLUMNS) {

                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[currentColumn]);

                    gameMap[currentColumn][currentRow] = number;
                    currentColumn++;
                }

                currentColumn = 0;
                currentRow++;
            }
            reader.close();
        } catch (Exception e) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void draw(Graphics2D g2d) {

        int currentColumn = 0;
        int currentRow = 0;
        int tileX = 0;
        int tileY = 0;

        while(currentColumn < gp.MAX_SCREEN_COLUMNS && currentRow < gp.MAX_SCREEN_ROWS) {


            int currentTileIndex = gameMap[currentColumn][currentRow];

            g2d.drawImage(tileType[currentTileIndex].image, tileX, tileY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            currentColumn++;

            tileX = tileX + gp.TILE_SIZE;

            if(currentColumn == gp.MAX_SCREEN_COLUMNS) {
                tileX = 0;
                tileY = tileY + gp.TILE_SIZE;
                currentRow++;
                currentColumn = 0;
            }
        }
    }
}


