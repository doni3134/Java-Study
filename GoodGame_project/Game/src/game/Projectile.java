package game;

import javax.swing.*;
import java.awt.*;

import static game.Game.PROJECTILE_WIDTH;
import static game.Game.PROJECTILE_HEIGHT;

public class Projectile extends Rectangle {
    private static final long serialVersionUID = 1L;
    private double dx;
    private double dy;

    public Projectile(int x, int y, double dx, double dy) {
        super(x, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.dx = dx;
        this.dy = dy;
    }

//    public void move() {
//        setLocation(x + dx, y + dy);
//    }

    public void move() { x += dx; y += dy; }
    public boolean isOffScreen(int screenHeight) {
        return y < 0 || y > screenHeight;
    }

    public void draw(Graphics g, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        g.drawImage(icon.getImage(), x, y, width, height, null);
    }
}