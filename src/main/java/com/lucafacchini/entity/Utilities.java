package com.lucafacchini.entity;

import java.awt.*;

public class Utilities {
    public Utilities() {
    }

    public void printError(String className, String message, String color) {
        System.out.println(color + "[" + className + "] " + message + "\033[0m");
    }
}
