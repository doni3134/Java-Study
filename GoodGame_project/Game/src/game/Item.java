package game;

import javax.swing.*;
import java.awt.*;

public class Item extends Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g, String imagePath) {

        ImageIcon icon = new ImageIcon(imagePath);
        g.drawImage(icon.getImage(), x, y, width, height, null);
    }
}
