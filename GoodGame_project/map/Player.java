package map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
    private int x;
    private int y;
    private int direction;
    private boolean attackKeyPressed;
    private int prevKeyCode;

    private Image img;

    Player(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void move() {
        switch (direction) {
            case KeyEvent.VK_UP:
                y -= 5;
                break;
            case KeyEvent.VK_DOWN:
                y += 5;
                break;
            case KeyEvent.VK_LEFT:
                x -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                break;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrevKeyCode() {
        return prevKeyCode;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        if (direction != 0) {
            this.prevKeyCode = direction;
        }
    }

    public boolean isAttackKeyPressed() {
        return attackKeyPressed;
    }

    public void setAttackKeyPressed(boolean attackKeyPressed) {
        this.attackKeyPressed = attackKeyPressed;
    }

}
