# Main.java Documentation

## Overview

The `Main` class serves as the entry point for the application. It is responsible for creating and displaying the main window (`JFrame`) of the application, which contains the `GamePanel`.

## Code Explanation

### Package Declaration

```java
package main;
```

This line declares that the `Main` class is part of the `main` package.

### Imports

```java
import javax.swing.*;
```

This import statement includes the necessary classes from the `javax.swing` package, which are used to create the graphical user interface (GUI) components.

### Main Class

```java
public class Main {
```

The `Main` class is declared as `public`, meaning it can be accessed from other classes.

### Main Method

```java
public static void main(String[] args) {
```

The `main` method is the entry point of the application. It is `public` so it can be called by the Java runtime, `static` so it can be called without creating an instance of the class, and `void` because it does not return any value. The `String[] args` parameter allows the method to accept command-line arguments.

### JFrame Creation

```java
JFrame window = new JFrame(); // create a new JFrame
```

A new `JFrame` object is created to serve as the main window of the application.

### JFrame Configuration

```java
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Automatically close the window when the user clicks the close button
window.setResizable(false); // Prevent the user from resizing the window
```

- `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`: This method sets the default close operation to exit the application when the user clicks the close button.
- `setResizable(false)`: This method prevents the user from resizing the window.

### GamePanel Creation and Addition

```java
GamePanel gamePanel = new GamePanel(); // Create a new GamePanel
window.add(gamePanel); // Add the GamePanel to the window
```

- A new `GamePanel` object is created.
- The `GamePanel` is added to the `JFrame` using the `add` method.

### JFrame Sizing and Positioning

```java
window.pack(); // Resize the window to fit the GamePanel
window.setLocationRelativeTo(null); // Center the window on the screen
```

- `pack()`: This method sizes the frame so that all its contents are at or above their preferred sizes.
- `setLocationRelativeTo(null)`: This method centers the window on the screen.

### Making the JFrame Visible

```java
window.setVisible(true); // Make the window visible
```

This method makes the `JFrame` visible on the screen.

## Summary

The `Main` class is responsible for setting up and displaying the main window of the application. It creates a `JFrame`, configures it, adds a `GamePanel` to it, sizes and positions the window, and finally makes it visible. This setup ensures that the application window is properly initialized and displayed to the user.