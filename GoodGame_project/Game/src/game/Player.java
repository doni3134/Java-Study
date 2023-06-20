package game;

import javax.swing.*;
import java.awt.*;

import static game.Game.SCREEN_HEIGHT;
import static game.Game.SCREEN_WIDTH;

public class Player extends Rectangle {
    private static final int PLAYER_SPEED = 3;
    private static final int MAX_HEALTH = 100;
    private static final int HEAL_AMOUNT = 10;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public void heal() {
        if (Game.playerHealth + HEAL_AMOUNT <= MAX_HEALTH) {
            Game.playerHealth += HEAL_AMOUNT;
        } else {
            Game.playerHealth = MAX_HEALTH;
        }
        //System.out.println(Game.playerHealth);
    }

    public void move(boolean up, boolean down, boolean left, boolean right) {
        if (up && y > 0) {
            setLocation(x, y - PLAYER_SPEED);
        }
        if (down && y + height < SCREEN_HEIGHT) {
            setLocation(x, y + PLAYER_SPEED);
        }
        if (left && x > 0) {
            setLocation(x - PLAYER_SPEED, y);
        }
        if (right && x + width < SCREEN_WIDTH) {
            setLocation(x + PLAYER_SPEED, y);
        }
    }

    public void draw(Graphics g, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        g.drawImage(icon.getImage(), x, y, width, height, null);
    }
}
