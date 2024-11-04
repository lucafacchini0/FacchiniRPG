package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); // create a new JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Automatically close the window when the user clicks the close button
        window.setResizable(false); // Prevent the user from resizing the window

        GamePanel gamePanel = new GamePanel(); // Create a new GamePanel
        window.add(gamePanel); // Add the GamePanel to the window
        window.pack(); // Resize the window to fit the GamePanel

        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setVisible(true); // Make the window visible
    }
}