package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Constants for the tile size and screen dimensions.
    final int ORIGINAL_TILE_SIZE = 16; // One tile is 16x16 pixels.
    final int SCALE = 4; // Scale up ORIGINAL_TILE_SIZE by 4x.
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // The size of a tile after scaling.

    public final int MAX_SCREEN_ROWS = 12; // The maximum number of rows that can be displayed on the screen.
    public final int MAX_SCREEN_COLUMNS = 16; // The maximum number of columns that can be displayed on the screen.

    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // The width of the screen in pixels.
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // The height of the screen in pixels.

    // Constants for the refresh rate of the game.
    final int FPS = 60;

    // Player/NPCs speed
    public final int DEFAULT_PLAYER_SPEED = 4;

    // Class objects
    Thread gameThread; // The thread that runs the game loop.
    KeyHandler keyHandler = new KeyHandler(); // The key handler that listens for key events.
    Player player = new Player(this, keyHandler); // The player object.

    // Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set the preferred size of the panel.
        this.setBackground(Color.BLACK); // Set the background color of the panel to black.
        this.setDoubleBuffered(true); // Enable double buffering to reduce flickering.

        this.addKeyListener(keyHandler); // Adds a listener for key events to the panel.
        // These listeners manage the events from keys through the KeyHandler class.

        this.setFocusable(true); // Makes the panel focusable, allowing it to receive input events such as keyboard events.
        // Without this setting, the panel may not respond to keyboard events even if a KeyListener is registered.
    }

    // Start the game thread.
    public void startGameThread() {
        gameThread = new Thread(this); // "This" means this class is the target of the thread.
        gameThread.start(); // Start the game thread.
    }

    // The game loop.
    @Override
    public void run() {
        double targetFrameTime = 1_000_000_000.0 / FPS; // The delay between frames in nanoseconds.
        double nextFrameTime = System.nanoTime() + targetFrameTime; // The time when the next frame should be drawn.

        while (gameThread != null) { // As long as the game thread is running...
            updateComponents();
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
    private void drawComponents(Graphics2D g2d) {
        player.draw(g2d);
    }

    // Paint the components of the panel.
    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g; // Cast the Graphics object to a Graphics2D object.

        drawComponents(g2d);

        g2d.dispose();
    }
}

