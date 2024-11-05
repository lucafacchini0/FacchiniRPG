package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    public boolean isLeftPressed = false;
    public boolean isRightPressed = false;
    public boolean isUpPressed = false;
    public boolean isDownPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            isLeftPressed = true;
        }

        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            isRightPressed = true;
        }

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            isUpPressed = true;
        }

        if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            isDownPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            isLeftPressed = false;
        }

        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            isRightPressed = false;
        }

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            isUpPressed = false;
        }

        if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            isDownPressed = false;
        }
    }
}
