package main;

import entity.Player;
import objects.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Tile settings
    final int ORIGINAL_TILE_SIZE = 16; // One tile is 16x16 pixels.
    public final int SCALE = 4; // Scale up ORIGINAL_TILE_SIZE by 4x.
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // The size of a tile after scaling.

    // Screen settings
    public final int MAX_SCREEN_ROWS = 12; // The maximum number of rows that can be displayed on the screen.
    public final int MAX_SCREEN_COLUMNS = 16; // The maximum number of columns that can be displayed on the screen.
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // The width of the screen in pixels.
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // The height of the screen in pixels.

    // World settings
    public final int MAX_WORLD_COLUMNS = 50; // The maximum number of columns in the world.
    public final int MAX_WORLD_ROWS = 50; // The maximum number of rows in the world.
    public final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COLUMNS; // The width of the world in pixels.
    public final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROWS; // The height of the world in pixels.

    // Window settings
    final int FPS = 60; // The target frames per second for the game.

    // Object settings
    public final int MAX_OBJECTS_ARRAY = 15; // The maximum number of objectsArray in the game.

    // Class objectsArray
    Thread gameThread; // The thread that runs the game loop.
    KeyHandler kh = new KeyHandler(); // The key handler that listens for key events.
    public Player player = new Player(this, kh); // The player object.
    TileManager tileManager = new TileManager(this); // The tile manager object.
    public CollisionManager collisionManager = new CollisionManager(this); // The collision manager object.
    public SuperObject[] objectsArray = new SuperObject[MAX_OBJECTS_ARRAY]; // The array of objectsArray in the game.
    public AssetSetter assetSetter = new AssetSetter(this); // The object initializer object.

    // Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set the preferred size of the panel.
        this.setBackground(Color.BLACK); // Set the background color of the panel to black.
        this.setDoubleBuffered(true); // Enable double buffering to reduce flickering.

        this.addKeyListener(kh); // Adds a listener for key events to the panel.
        // These listeners manage the events from keys through the KeyHandler class.

        this.setFocusable(true); // Makes the panel focusable, allowing it to receive input events such as keyboard events.
        // Without this setting, the panel may not respond to keyboard events even if a KeyListener is registered.
    }

    public void initializeGame() {
        assetSetter.placeObject(); // Place the objectsArray in the game.
    }

    // Start the game thread. (Called in main)
    public void startGameThread() {
        gameThread = new Thread(this); // "This" means this class is the target of the thread.
        gameThread.start(); // Start the game thread.
    }


    // Game loop.
    @Override
    public void run() {
        double targetFrameTime = 1_000_000_000.0 / FPS; // The delay between frames in nanoseconds.
        double nextFrameTime = System.nanoTime() + targetFrameTime; // The time when the next frame should be drawn.

        while (gameThread != null) { // As long as the game thread is running...

            // Important: These methods will be called every frame. Keep this in mind.
            updateComponents(); // Update the components of the panel.
            repaint(); // Repaint the panel.

            try {
                double remainingTime = nextFrameTime - System.nanoTime(); // Calculate the remaining time until the next frame.
                if (remainingTime > 0) {
                    Thread.sleep((long) remainingTime / 1_000_000); // Sleep until the next frame time is reached.
                }
                nextFrameTime += targetFrameTime; // Calculate the time when the next frame should be drawn.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Every update will be handled here
    private void updateComponents() {
        player.update();
    }

    // Draw the components of the panel.
    private void drawAllComponents(Graphics2D g2d) {
        tileManager.draw(g2d);

        for(int i = 0; i < objectsArray.length; i++) {
            if(objectsArray[i] != null) {
                objectsArray[i].draw(g2d, this);
            }
        }
        player.draw(g2d);

    }

    // Paint the components of the panel.
    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g; // Cast the Graphics object to a Graphics2D object.

        drawAllComponents(g2d);

        g2d.dispose();
    }
}