package com.lucafacchini;

import com.lucafacchini.entity.Player;
import com.lucafacchini.objects.SuperObject;
import com.lucafacchini.entity.Entity;
import com.lucafacchini.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Game settings and status
    public String gameStatus = "running";
    public final int FPS = 60;

    // Tile settings
    public final int ORIGINAL_TILE_SIZE = 16;
    public final int SCALE = 4;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    // Screen settings
    public final int MAX_SCREEN_ROWS = 12;
    public final int MAX_SCREEN_COLUMNS = 16;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;

    // World settings
    public final int MAX_WORLD_COLUMNS = 50;
    public final int MAX_WORLD_ROWS = 50;

    // Object & NPCs settings
    public final int MAX_OBJECTS_ARRAY = 15;
    public final int MAX_NPC_ARRAY = 15;

    // Game objects and managers
    Thread gameThread;
    KeyHandler kh = new KeyHandler(this);
    public Player player = new Player(this, kh);
    public CollisionManager collisionManager = new CollisionManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    // Objects & NPCs
    public SuperObject[] objectsArray = new SuperObject[MAX_OBJECTS_ARRAY]; // Array for objects in the game
    public Entity[] npcArray = new Entity[MAX_NPC_ARRAY]; // Array for NPCs in the game

    // Tile layers for rendering different map layers
    public TileManager firstLayerMap = new TileManager(this, "background.csv");
    public TileManager secondLayerMap = new TileManager(this, "groundDecoration.csv");
    public TileManager thirdLayerMap = new TileManager(this, "background.csv");

    // Sound and UI components
    private Sound music = new Sound();
    private Sound sound = new Sound();
    public UI ui = new UI(this);



    // ------------------- Constructor -------------------

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(kh);
        this.setFocusable(true);

        playMusic(0);
    }


    // ------------------- Game Initialization -------------------

    public void initializeGame() {
        assetSetter.placeObject();
        assetSetter.placeNPC();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }



    // ------------------- Game Loop -------------------

    @Override
    public void run() {
        double targetFrameTime = 1_000_000_000.0 / FPS; // The delay between frames in nanoseconds.
        double nextFrameTime = System.nanoTime() + targetFrameTime; // The time when the next frame should be drawn.

        while (gameThread != null) {
            updateComponents();
            repaint();

            try {
                double remainingTimeToNextFrame = nextFrameTime - System.nanoTime();
                if (remainingTimeToNextFrame > 0) {
                    Thread.sleep((long) remainingTimeToNextFrame / 1_000_000); // Sleep until the next frame time is reached.
                }
                nextFrameTime += targetFrameTime; // Calculate the time when the next frame should be drawn.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



    // ------------------- Update Components -------------------

    private void updateComponents() {
        if(gameStatus.equals("running")) {
            player.update();
            npcArray[0].update();
        }
    }

    @Override
    public void paintComponent(Graphics g)  {
        if(gameStatus.equals("running")) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            drawAllComponents(g2d);

            g2d.dispose();
        } else {
            ui.draw((Graphics2D)g);
        }
    }



    // ------------------- Drawing  -------------------

    private void drawAllComponents(Graphics2D g2d) {
        firstLayerMap.draw(g2d);

        for(int i = 0; i < objectsArray.length; i++) {
            if(objectsArray[i] != null) {
                System.out.println("Drawing object: " + objectsArray[i].name);
                objectsArray[i].draw(g2d, this);
            }
        }

        player.draw(g2d);

        for(int i = 0; i < npcArray.length; i++) {
            if(npcArray[i] != null) {

            }
        }

       secondLayerMap.draw(g2d);

        ui.draw(g2d);
    }



    // ------------------- Music and Sound -------------------

    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSound(int index) {
        sound.setFile(index);
        sound.play();
    }
}