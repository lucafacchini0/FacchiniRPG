package com.lucafacchini;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Utilities {
    public Utilities() {
    }

    // Print error message with class name and color
    public void printError(String className, String message, String color) {
        System.out.println(color + "[" + className + "] " + message + "\033[0m");
    }

    // Performance utilities
    // Rescale image to new width and height
    public BufferedImage rescaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }
}
