# GamePanel.java Documentation

## Overview

The `GamePanel` class is a custom `JPanel` that serves as the main drawing surface for the game. It implements the `Runnable` interface to support a game loop running in a separate thread.

## Code Explanation

### Package Declaration

```java
package main;
```

This line declares that the `GamePanel` class is part of the `main` package.

### Imports

```java
import java.awt.*;
import javax.swing.*;
```

These import statements include the necessary classes from the `java.awt` and `javax.swing` packages, which are used for creating the graphical user interface (GUI) components and handling graphics.

### Class Declaration

```java
public class GamePanel extends JPanel implements Runnable {
```

The `GamePanel` class extends `JPanel` and implements the `Runnable` interface, allowing it to be used as a custom component and run in a separate thread.

### Constants

```java
final int ORIGINAL_TILE_SIZE = 16; // One tile is 16x16 pixels.
final int SCALE = 4; // Scale up ORIGINAL_TILE_SIZE by 4x.
final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // The size of a tile after scaling.

final int MAX_SCREEN_ROWS = 12; // The maximum number of rows that can be displayed on the screen.
final int MAX_SCREEN_COLUMNS = 16; // The maximum number of columns that can be displayed on the screen.

final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // The width of the screen in pixels.
final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // The height of the screen in pixels.
```

These constants define the tile size, screen dimensions, and scaling factors used in the game.

### Instance Variables

```java
Thread gameThread; // The thread that runs the game loop.
```

This instance variable holds the thread that will run the game loop.

### Constructor

```java
public GamePanel() {
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set the preferred size of the panel.
    this.setBackground(Color.BLACK); // Set the background color of the panel to black.
    this.setDoubleBuffered(true); // Enable double buffering to reduce flickering.
}
```

The constructor sets the preferred size, background color, and enables double buffering for the panel.

### Methods

#### startGameThread

```java
public void startGameThread() {
    gameThread = new Thread(this); // This means this class is the target of the thread.
    gameThread.start(); // Start the game thread.
}
```

This method initializes and starts the game thread.

#### run

```java
@Override
public void run() {
    // Next: Game Loop
}
```

This method is the entry point for the game loop, which will be implemented in the future.

## Summary

The `GamePanel` class is a custom `JPanel` that sets up the main drawing surface for the game. It defines the screen dimensions, initializes the panel properties, and supports running a game loop in a separate thread. This setup ensures that the game can be rendered and updated efficiently.