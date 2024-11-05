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

        if(key == KeyEvent.VK_LEFT) {
            isLeftPressed = true;
        }

        if(key == KeyEvent.VK_RIGHT) {
            isRightPressed = true;
        }

        if(key == KeyEvent.VK_UP) {
            isUpPressed = true;
        }

        if(key == KeyEvent.VK_DOWN) {
            isDownPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT) {
            isLeftPressed = false;
        }

        if(key == KeyEvent.VK_RIGHT) {
            isRightPressed = false;
        }

        if(key == KeyEvent.VK_UP) {
            isUpPressed = false;
        }

        if(key == KeyEvent.VK_DOWN) {
            isDownPressed = false;
        }
    }
}
