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
    public final int TOTAL_UNIQUE_TILES = 2500;
    public final String MAPS_PATH = "/maps/";
    public final int[][] GAME_MAP;

    // Objects
    GamePanel gp;
    public Tile[] tileUnique; // This will contain all the unique tiles in the game

    public TileManager(GamePanel gp, String path) {
        this.gp = gp;

        tileUnique = new Tile[TOTAL_UNIQUE_TILES];
        GAME_MAP = new int[gp.MAX_WORLD_COLUMNS][gp.MAX_WORLD_ROWS];

        loadTileImages();
        loadMap(MAPS_PATH + path);
    }

    private void loadTileImages() {
        try {
            // Grass Round Tiles
            final int GRASS_ROUND_1 = 183;
            final int GRASS_ROUND_2 = 184;
            final int GRASS_ROUND_3 = 185;
            final int GRASS_ROUND_4 = 186;
            final int GRASS_ROUND_5 = 187;
            final int GRASS_ROUND_6 = 359;
            final int GRASS_ROUND_7 = 360;
            final int GRASS_ROUND_8 = 361;
            final int GRASS_ROUND_9 = 362;
            final int GRASS_ROUND_10 = 363;
            final int GRASS_ROUND_11 = 535;
            final int GRASS_ROUND_12 = 536;
            final int GRASS_ROUND_13 = 537;
            final int GRASS_ROUND_14 = 538;
            final int GRASS_ROUND_15 = 539;
            final int GRASS_ROUND_16 = 711;
            final int GRASS_ROUND_17 = 712;
            final int GRASS_ROUND_18 = 713;
            final int GRASS_ROUND_19 = 714;
            final int GRASS_ROUND_20 = 715;
            final int GRASS_ROUND_21 = 887;
            final int GRASS_ROUND_22 = 888;
            final int GRASS_ROUND_23 = 889;
            final int GRASS_ROUND_24 = 890;
            final int GRASS_ROUND_25 = 891;

            // Small Tree Clean Tiles
            final int SMALL_TREE_CLEAN_1 = 544;
            final int SMALL_TREE_CLEAN_2 = 545;
            final int SMALL_TREE_CLEAN_3 = 720;
            final int SMALL_TREE_CLEAN_4 = 721;
            final int SMALL_TREE_CLEAN_5 = 896;
            final int SMALL_TREE_CLEAN_6 = 897;

            // Small Tree With Grass Tiles
            final int SMALL_TREE_WITH_GRASS_1 = 546;
            final int SMALL_TREE_WITH_GRASS_2 = 547;
            final int SMALL_TREE_WITH_GRASS_3 = 722;
            final int SMALL_TREE_WITH_GRASS_4 = 723;
            final int SMALL_TREE_WITH_GRASS_5 = 898;
            final int SMALL_TREE_WITH_GRASS_6 = 899;

            // Small Tree With Flowerbed Tiles
            final int SMALL_TREE_WITH_FLOWERBED_1 = 548;
            final int SMALL_TREE_WITH_FLOWERBED_2 = 549;
            final int SMALL_TREE_WITH_FLOWERBED_3 = 724;
            final int SMALL_TREE_WITH_FLOWERBED_4 = 725;
            final int SMALL_TREE_WITH_FLOWERBED_5 = 900;
            final int SMALL_TREE_WITH_FLOWERBED_6 = 901;
            final int SMALL_TREE_WITH_FLOWERBED_7 = 1076;
            final int SMALL_TREE_WITH_FLOWERBED_8 = 1077;

            // Small Tree With Artificial Flowerbed Tiles
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_1 = 550;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_2 = 551;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_3 = 726;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_4 = 727;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_5 = 902;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_6 = 903;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_7 = 1078;
            final int SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_8 = 1079;

            // Pine in sequence
            final int PINE_IN_SEQUENCE_1 = 1069;
            final int PINE_IN_SEQUENCE_2 = 1070;
            final int PINE_IN_SEQUENCE_3 = 1071;
            final int PINE_IN_SEQUENCE_4 = 1245;
            final int PINE_IN_SEQUENCE_5 = 1246;
            final int PINE_IN_SEQUENCE_6 = 1247;
            final int PINE_IN_SEQUENCE_7 = 1421;
            final int PINE_IN_SEQUENCE_8 = 1422;
            final int PINE_IN_SEQUENCE_9 = 1423;
            final int PINE_IN_SEQUENCE_10 = 1597;
            final int PINE_IN_SEQUENCE_11 = 1598;
            final int PINE_IN_SEQUENCE_12 = 1599;

            // Island with grass
            final int ISLAND_WITH_GRASS_1 = 305;
            final int ISLAND_WITH_GRASS_2 = 306;
            final int ISLAND_WITH_GRASS_3 = 307;
            final int ISLAND_WITH_GRASS_4 = 308;
            final int ISLAND_WITH_GRASS_5 = 481;
            final int ISLAND_WITH_GRASS_6 = 482;
            final int ISLAND_WITH_GRASS_7 = 483;
            final int ISLAND_WITH_GRASS_8 = 484;
            final int ISLAND_WITH_GRASS_9 = 657;
            final int ISLAND_WITH_GRASS_10 = 658;
            final int ISLAND_WITH_GRASS_11 = 659;
            final int ISLAND_WITH_GRASS_12 = 660;
            final int ISLAND_WITH_GRASS_13 = 833;
            final int ISLAND_WITH_GRASS_14 = 834;
            final int ISLAND_WITH_GRASS_15 = 835;
            final int ISLAND_WITH_GRASS_16 = 836;

            // LITTLE POUND
            final int LITTLE_POUND_1 = 309;
            final int LITTLE_POUND_2 = 310;
            final int LITTLE_POUND_3 = 311;
            final int LITTLE_POUND_4 = 312;
            final int LITTLE_POUND_5 = 485;
            final int LITTLE_POUND_6 = 486;
            final int LITTLE_POUND_7 = 487;
            final int LITTLE_POUND_8 = 488;
            final int LITTLE_POUND_9 = 661;
            final int LITTLE_POUND_10 = 662;
            final int LITTLE_POUND_11 = 663;
            final int LITTLE_POUND_12 = 664;
            final int LITTLE_POUND_13 = 837;
            final int LITTLE_POUND_14 = 838;
            final int LITTLE_POUND_15 = 839;
            final int LITTLE_POUND_16 = 840;

            // final int WATER WITH EFFECTS
            final int WATER_WITH_EFFECTS_1 = 1009;
            final int WATER_WITH_EFFECTS_2 = 1010;
            final int WATER_WITH_EFFECTS_3 = 1011;
            final int WATER_WITH_EFFECTS_4 = 1012;

            // still water
            final int STILL_WATER_1 = 1185;
            final int STILL_WATER_2 = 1189;

            // ninfee
            final int LILYPAD_1 = 1016;
            final int LILYPAD_2 = 1017;
            final int LILYPAD_3 = 1018;

            // small place of land (1 tile) in the water
            final int SMALL_PLACE_OF_LAND_IN_WATER_1 = 1186;
            final int SMALL_PLACE_OF_LAND_IN_WATER_2 = 1187;
            final int SMALL_PLACE_OF_LAND_IN_WATER_3 = 1188;

            // Initializing all tile objects
            tileUnique[GRASS_ROUND_1] = new Tile();
            tileUnique[GRASS_ROUND_2] = new Tile();
            tileUnique[GRASS_ROUND_3] = new Tile();
            tileUnique[GRASS_ROUND_4] = new Tile();
            tileUnique[GRASS_ROUND_5] = new Tile();
            tileUnique[GRASS_ROUND_6] = new Tile();
            tileUnique[GRASS_ROUND_7] = new Tile();
            tileUnique[GRASS_ROUND_8] = new Tile();
            tileUnique[GRASS_ROUND_9] = new Tile();
            tileUnique[GRASS_ROUND_10] = new Tile();
            tileUnique[GRASS_ROUND_11] = new Tile();
            tileUnique[GRASS_ROUND_12] = new Tile();
            tileUnique[GRASS_ROUND_13] = new Tile();
            tileUnique[GRASS_ROUND_14] = new Tile();
            tileUnique[GRASS_ROUND_15] = new Tile();
            tileUnique[GRASS_ROUND_16] = new Tile();
            tileUnique[GRASS_ROUND_17] = new Tile();
            tileUnique[GRASS_ROUND_18] = new Tile();
            tileUnique[GRASS_ROUND_19] = new Tile();
            tileUnique[GRASS_ROUND_20] = new Tile();
            tileUnique[GRASS_ROUND_21] = new Tile();
            tileUnique[GRASS_ROUND_22] = new Tile();
            tileUnique[GRASS_ROUND_23] = new Tile();
            tileUnique[GRASS_ROUND_24] = new Tile();
            tileUnique[GRASS_ROUND_25] = new Tile();

            tileUnique[SMALL_TREE_CLEAN_1] = new Tile();
            tileUnique[SMALL_TREE_CLEAN_2] = new Tile();
            tileUnique[SMALL_TREE_CLEAN_3] = new Tile();
            tileUnique[SMALL_TREE_CLEAN_4] = new Tile();
            tileUnique[SMALL_TREE_CLEAN_5] = new Tile();
            tileUnique[SMALL_TREE_CLEAN_6] = new Tile();

            tileUnique[SMALL_TREE_WITH_GRASS_1] = new Tile();
            tileUnique[SMALL_TREE_WITH_GRASS_2] = new Tile();
            tileUnique[SMALL_TREE_WITH_GRASS_3] = new Tile();
            tileUnique[SMALL_TREE_WITH_GRASS_4] = new Tile();
            tileUnique[SMALL_TREE_WITH_GRASS_5] = new Tile();
            tileUnique[SMALL_TREE_WITH_GRASS_6] = new Tile();

            tileUnique[SMALL_TREE_WITH_FLOWERBED_1] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_2] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_3] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_4] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_5] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_6] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_7] = new Tile();
            tileUnique[SMALL_TREE_WITH_FLOWERBED_8] = new Tile();

            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_1] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_2] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_3] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_4] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_5] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_6] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_7] = new Tile();
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_8] = new Tile();

            tileUnique[PINE_IN_SEQUENCE_1] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_2] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_3] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_4] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_5] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_6] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_7] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_8] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_9] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_10] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_11] = new Tile();
            tileUnique[PINE_IN_SEQUENCE_12] = new Tile();

            tileUnique[ISLAND_WITH_GRASS_1] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_2] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_3] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_4] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_5] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_6] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_7] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_8] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_9] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_10] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_11] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_12] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_13] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_14] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_15] = new Tile();
            tileUnique[ISLAND_WITH_GRASS_16] = new Tile();

            tileUnique[LITTLE_POUND_1] = new Tile();
            tileUnique[LITTLE_POUND_2] = new Tile();
            tileUnique[LITTLE_POUND_3] = new Tile();
            tileUnique[LITTLE_POUND_4] = new Tile();
            tileUnique[LITTLE_POUND_5] = new Tile();
            tileUnique[LITTLE_POUND_6] = new Tile();
            tileUnique[LITTLE_POUND_7] = new Tile();
            tileUnique[LITTLE_POUND_8] = new Tile();
            tileUnique[LITTLE_POUND_9] = new Tile();
            tileUnique[LITTLE_POUND_10] = new Tile();
            tileUnique[LITTLE_POUND_11] = new Tile();
            tileUnique[LITTLE_POUND_12] = new Tile();
            tileUnique[LITTLE_POUND_13] = new Tile();
            tileUnique[LITTLE_POUND_14] = new Tile();
            tileUnique[LITTLE_POUND_15] = new Tile();
            tileUnique[LITTLE_POUND_16] = new Tile();

            tileUnique[WATER_WITH_EFFECTS_1] = new Tile();
            tileUnique[WATER_WITH_EFFECTS_2] = new Tile();
            tileUnique[WATER_WITH_EFFECTS_3] = new Tile();
            tileUnique[WATER_WITH_EFFECTS_4] = new Tile();

            tileUnique[STILL_WATER_1] = new Tile();
            tileUnique[STILL_WATER_2] = new Tile();

            tileUnique[LILYPAD_1] = new Tile();
            tileUnique[LILYPAD_2] = new Tile();
            tileUnique[LILYPAD_3] = new Tile();

            // small piece of land in the water
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_1] = new Tile();
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_2] = new Tile();
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_3] = new Tile();

            // Set images
            // Grass Round Tiles
            tileUnique[GRASS_ROUND_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_183.png")));
            tileUnique[GRASS_ROUND_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_184.png")));
            tileUnique[GRASS_ROUND_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_185.png")));
            tileUnique[GRASS_ROUND_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_186.png")));
            tileUnique[GRASS_ROUND_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_187.png")));
            tileUnique[GRASS_ROUND_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_359.png")));
            tileUnique[GRASS_ROUND_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_360.png")));
            tileUnique[GRASS_ROUND_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_361.png")));
            tileUnique[GRASS_ROUND_9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_362.png")));
            tileUnique[GRASS_ROUND_10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_363.png")));
            tileUnique[GRASS_ROUND_11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_535.png")));
            tileUnique[GRASS_ROUND_12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_536.png")));
            tileUnique[GRASS_ROUND_13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_537.png")));
            tileUnique[GRASS_ROUND_14].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_538.png")));
            tileUnique[GRASS_ROUND_15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_539.png")));
            tileUnique[GRASS_ROUND_16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_711.png")));
            tileUnique[GRASS_ROUND_17].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_712.png")));
            tileUnique[GRASS_ROUND_18].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_713.png")));
            tileUnique[GRASS_ROUND_19].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_714.png")));
            tileUnique[GRASS_ROUND_20].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_715.png")));
            tileUnique[GRASS_ROUND_21].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_887.png")));
            tileUnique[GRASS_ROUND_22].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_888.png")));
            tileUnique[GRASS_ROUND_23].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_889.png")));
            tileUnique[GRASS_ROUND_24].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_890.png")));
            tileUnique[GRASS_ROUND_25].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/grass/tile_891.png")));

            // Small Tree Clean Tiles
            tileUnique[SMALL_TREE_CLEAN_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_544.png")));
            tileUnique[SMALL_TREE_CLEAN_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_545.png")));
            tileUnique[SMALL_TREE_CLEAN_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_720.png")));
            tileUnique[SMALL_TREE_CLEAN_3].isSolid = true;
            tileUnique[SMALL_TREE_CLEAN_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_721.png")));
            tileUnique[SMALL_TREE_CLEAN_4].isSolid = true;
            tileUnique[SMALL_TREE_CLEAN_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_896.png")));
            tileUnique[SMALL_TREE_CLEAN_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_897.png")));

            // Small Tree With Grass Tiles
            tileUnique[SMALL_TREE_WITH_GRASS_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_546.png")));
            tileUnique[SMALL_TREE_WITH_GRASS_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_547.png")));
            tileUnique[SMALL_TREE_WITH_GRASS_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_722.png")));
            tileUnique[SMALL_TREE_WITH_GRASS_3].isSolid = true;
            tileUnique[SMALL_TREE_WITH_GRASS_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_723.png")));
            tileUnique[SMALL_TREE_WITH_GRASS_4].isSolid = true;
            tileUnique[SMALL_TREE_WITH_GRASS_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_898.png")));
            tileUnique[SMALL_TREE_WITH_GRASS_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_899.png")));

           // Small Tree With Flowerbed Tiles
            tileUnique[SMALL_TREE_WITH_FLOWERBED_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_548.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_549.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_724.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_3].isSolid = true;
            tileUnique[SMALL_TREE_WITH_FLOWERBED_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_725.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_4].isSolid = true;
            tileUnique[SMALL_TREE_WITH_FLOWERBED_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_900.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_5].isSolid = true;
            tileUnique[SMALL_TREE_WITH_FLOWERBED_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_901.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_6].isSolid = true;
            tileUnique[SMALL_TREE_WITH_FLOWERBED_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_1076.png")));
            tileUnique[SMALL_TREE_WITH_FLOWERBED_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_1077.png")));

            // Small Tree With Artificial Flowerbed Tiles
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_550.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_551.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_726.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_3].isSolid = true;
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_727.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_4].isSolid = true;
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_902.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_5].isSolid = true;
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_903.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_6].isSolid = true;
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_1078.png")));
            tileUnique[SMALL_TREE_WITH_ARTIFICIAL_FLOWERBED_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/unique_single/tile_1079.png")));

            // Pine in sequence
            tileUnique[PINE_IN_SEQUENCE_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1069.png")));
            tileUnique[PINE_IN_SEQUENCE_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1070.png")));
            tileUnique[PINE_IN_SEQUENCE_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1071.png")));
            tileUnique[PINE_IN_SEQUENCE_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1245.png")));
            tileUnique[PINE_IN_SEQUENCE_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1246.png")));
            tileUnique[PINE_IN_SEQUENCE_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1247.png")));
            tileUnique[PINE_IN_SEQUENCE_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1421.png")));
            tileUnique[PINE_IN_SEQUENCE_7].isSolid = true;
            tileUnique[PINE_IN_SEQUENCE_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1422.png")));
            tileUnique[PINE_IN_SEQUENCE_8].isSolid = true;
            tileUnique[PINE_IN_SEQUENCE_9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1423.png")));
            tileUnique[PINE_IN_SEQUENCE_9].isSolid = true;
            tileUnique[PINE_IN_SEQUENCE_10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1597.png")));
            tileUnique[PINE_IN_SEQUENCE_11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1598.png")));
            tileUnique[PINE_IN_SEQUENCE_12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/trees/pines/tile_1599.png")));

            // Island with grass
            tileUnique[ISLAND_WITH_GRASS_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_305.png")));
            tileUnique[ISLAND_WITH_GRASS_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_306.png")));
            tileUnique[ISLAND_WITH_GRASS_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_307.png")));
            tileUnique[ISLAND_WITH_GRASS_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_308.png")));
            //solid 308
            tileUnique[ISLAND_WITH_GRASS_4].isSolid = true;
            tileUnique[ISLAND_WITH_GRASS_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_481.png")));
            tileUnique[ISLAND_WITH_GRASS_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_482.png")));
            tileUnique[ISLAND_WITH_GRASS_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_483.png")));
            tileUnique[ISLAND_WITH_GRASS_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_484.png")));
            tileUnique[ISLAND_WITH_GRASS_9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_657.png")));
            tileUnique[ISLAND_WITH_GRASS_10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_658.png")));
            tileUnique[ISLAND_WITH_GRASS_11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_659.png")));
            tileUnique[ISLAND_WITH_GRASS_12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_660.png")));
            tileUnique[ISLAND_WITH_GRASS_13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_833.png")));
            tileUnique[ISLAND_WITH_GRASS_13].isSolid = true;
            tileUnique[ISLAND_WITH_GRASS_14].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_834.png")));
            tileUnique[ISLAND_WITH_GRASS_14].isSolid = true;
            tileUnique[ISLAND_WITH_GRASS_15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_835.png")));
            tileUnique[ISLAND_WITH_GRASS_15].isSolid = true;
            tileUnique[ISLAND_WITH_GRASS_16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/tile_836.png")));
            tileUnique[ISLAND_WITH_GRASS_16].isSolid = true;

            // LITTLE POUND
            tileUnique[LITTLE_POUND_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_309.png")));
            tileUnique[LITTLE_POUND_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_310.png")));
            tileUnique[LITTLE_POUND_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_311.png")));
            tileUnique[LITTLE_POUND_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_312.png")));
            tileUnique[LITTLE_POUND_5].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_485.png")));
            tileUnique[LITTLE_POUND_6].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_486.png")));
            tileUnique[LITTLE_POUND_6].isSolid = true;
            tileUnique[LITTLE_POUND_7].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_487.png")));
            tileUnique[LITTLE_POUND_7].isSolid = true;
            tileUnique[LITTLE_POUND_8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_488.png")));
            tileUnique[LITTLE_POUND_9].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_661.png")));
            tileUnique[LITTLE_POUND_10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_662.png")));
            tileUnique[LITTLE_POUND_10].isSolid = true;
            tileUnique[LITTLE_POUND_11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_663.png")));
            tileUnique[LITTLE_POUND_11].isSolid = true;
            tileUnique[LITTLE_POUND_12].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_664.png")));
            tileUnique[LITTLE_POUND_13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_837.png")));
            tileUnique[LITTLE_POUND_14].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_838.png")));
            tileUnique[LITTLE_POUND_15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_839.png")));
            tileUnique[LITTLE_POUND_16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/pounds/tile_840.png")));

            // final int WATER WITH EFFECTS
            tileUnique[WATER_WITH_EFFECTS_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/effects/tile_1009.png")));
            tileUnique[WATER_WITH_EFFECTS_1].isSolid = true;
            tileUnique[WATER_WITH_EFFECTS_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/effects/tile_1010.png")));
            tileUnique[WATER_WITH_EFFECTS_2].isSolid = true;
            tileUnique[WATER_WITH_EFFECTS_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/effects/tile_1011.png")));
            tileUnique[WATER_WITH_EFFECTS_3].isSolid = true;
            tileUnique[WATER_WITH_EFFECTS_4].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/effects/tile_1012.png")));
            tileUnique[WATER_WITH_EFFECTS_4].isSolid = true;

            // still water
            tileUnique[STILL_WATER_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/tile_1185.png")));
            tileUnique[STILL_WATER_1].isSolid = true;
            tileUnique[STILL_WATER_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/tile_1189.png")));
            tileUnique[STILL_WATER_2].isSolid = true;

            // ninfee
            tileUnique[LILYPAD_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/lilypads/tile_1016.png")));
            tileUnique[LILYPAD_1].isSolid = true;
            tileUnique[LILYPAD_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/lilypads/tile_1017.png")));
            tileUnique[LILYPAD_2].isSolid = true;
            tileUnique[LILYPAD_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/water/lilypads/tile_1018.png")));
            tileUnique[LILYPAD_3].isSolid = true;

            // small piece of land in the water
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/small_island/tile_1186.png")));
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_1].isSolid = true;
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/small_island/tile_1187.png")));
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_2].isSolid = true;
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/islands/small_island/tile_1188.png")));
            tileUnique[SMALL_PLACE_OF_LAND_IN_WATER_3].isSolid = true;

        } catch (Exception e) {
            e.printStackTrace();
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
                    String[] numbers = line.split(",");
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

            // These are static that represent the position of the tile in the game world.
            worldX = currentWorldColumn * gp.TILE_SIZE; // The x position of the tile in the game world
            worldY = currentWorldRow * gp.TILE_SIZE; // The y position of the tile in the game world

            // This is the tile representation on the screen! Image the tile moving on the screen while the
            // player moves in the game world. This is the position of the tile on the screen.
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw the tile if it is within the screen bounds.
            // screenX and screenY must be within the screen bounds.
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                if (tileUnique[currentTileIndex] != null && tileUnique[currentTileIndex].image != null) {
                    g2d.drawImage(tileUnique[currentTileIndex].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
                }
            }

            currentWorldColumn++;

            if (currentWorldColumn == gp.MAX_WORLD_COLUMNS) {
                currentWorldRow++;
                currentWorldColumn = 0;
            }

             //##TROUBLESHOOTING ##IMPORTANT
             g2d.setColor(Color.pink);
             g2d.drawRect(screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE);
        }
    }
}