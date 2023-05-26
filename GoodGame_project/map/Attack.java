import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Attack {
    private int x;
    private int y;
    private int direction;
    private Image img;

    Attack(int x, int y, int direction, Image img) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.img = img;
    }

    public void move() {
        switch (direction) {
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                x += 10;
                break;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, x + 150, y + 30, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
