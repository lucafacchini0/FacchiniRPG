package main;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    final int ORIGINAL_TILE_SIZE = 16; // One tile is 16x16 pixels.
    final int SCALE = 4; // Scale up ORIGINAL_TILE_SIZE by 4x.
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // The size of a tile after scaling.

    final int MAX_SCREEN_ROWS = 12; // The maximum number of rows that can be displayed on the screen.
    final int MAX_SCREEN_COLUMNS = 16; // The maximum number of columns that can be displayed on the screen.

    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // The width of the screen in pixels.
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // The height of the screen in pixels.

    Thread gameThread; // The thread that runs the game loop.

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set the preferred size of the panel.
        this.setBackground(Color.BLACK); // Set the background color of the panel to black.
        this.setDoubleBuffered(true); // Enable double buffering to reduce flickering.
    }

    public void startGameThread() {
        gameThread = new Thread(this); // This means this class is the target of the thread.
        gameThread.start(); // Start the game thread.
    }

    @Override
    public void run() {
        // Next: Game Loop
    }
}

