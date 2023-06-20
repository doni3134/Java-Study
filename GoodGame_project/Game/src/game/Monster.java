package game;

import javax.swing.*;
import java.awt.*;

import static game.Game.MONSTER_PROJECTILE_COOLDOWN;

public class Monster extends Rectangle {
    private static final int MONSTER_SPEED = 1;
    private int projectileCooldown;

    public Monster(int x, int y, int width, int height) {
        super(x, y, width, height);
        projectileCooldown = 0;
    }

    public void chasePlayer(Player player) {
        int playerX = (int) player.getX();
        int playerY = (int) player.getY();
        if (x < playerX) {
            setLocation(x + MONSTER_SPEED, y);
        }
        if (x > playerX) {
            setLocation(x - MONSTER_SPEED, y);
        }
        if (y < playerY) {
            setLocation(x, y + MONSTER_SPEED);
        }
        if (y > playerY) {
            setLocation(x, y - MONSTER_SPEED);
        }
    }

    public int getProjectileCooldown() {
        return projectileCooldown;
    }

    public void resetProjectileCooldown() {
        projectileCooldown = MONSTER_PROJECTILE_COOLDOWN;
    }

    public void decreaseProjectileCooldown() {
        if (projectileCooldown > 0) {
            projectileCooldown--;
        }
    }

    public void draw(Graphics g, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        g.drawImage(icon.getImage(), x, y, width, height, null);
    }
}
