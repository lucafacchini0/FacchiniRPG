package com.lucafacchini.entity;

import java.awt.image.BufferedImage;

public class Utilities {
    public Utilities() {
    }

    // Print error message with class name and color
    public void printError(String className, String message, String color) {
        System.out.println(color + "[" + className + "] " + message + "\033[0m");
    }

    // Performance utilities
}
