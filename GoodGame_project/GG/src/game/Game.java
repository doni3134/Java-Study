package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int MONSTER_WIDTH = 50;
    private static final int MONSTER_HEIGHT = 50;
    public static final int PROJECTILE_WIDTH = 10;
    public static final int PROJECTILE_HEIGHT = 10;
    private static final int PLAYER_PROJECTILE_SPEED = 5;
    private static final int MONSTER_PROJECTILE_SPEED = 3;
    public static final int MONSTER_PROJECTILE_COOLDOWN = 100;


    private Item item;
    private Timer itemTimer;
    private boolean isItemActive;
    private Player player;
    private Monster monster;
    private ArrayList<Projectile> playerProjectiles;
    private ArrayList<Projectile> monsterProjectiles;
    private Timer timer;
    public static int playerHealth;
    private int monsterHealth;
    private ArrayList<Image> playerHealthImages;
    private Image playerHealthImage;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private int lastPressedDirection;



    public Game() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        playerHealth = 100;
        monsterHealth = 1000;

        player = new Player(SCREEN_WIDTH / 2 - PLAYER_WIDTH / 2, SCREEN_HEIGHT - PLAYER_HEIGHT - 10, PLAYER_WIDTH, PLAYER_HEIGHT);
        monster = new Monster(SCREEN_WIDTH / 2 - MONSTER_WIDTH / 2, 10, MONSTER_WIDTH, MONSTER_HEIGHT);
        playerProjectiles = new ArrayList<>();
        monsterProjectiles = new ArrayList<>();
        timer = new Timer(10, this);
        timer.start();

        item = new Item(SCREEN_WIDTH / 2 - 25, SCREEN_HEIGHT / 2 - 25, 50, 50);  // 아이템 인스턴스 생성
        itemTimer = new Timer(5000, this); // 5초마다 타이머 이벤트 발생
        itemTimer.setInitialDelay(0); // 초기 딜레이를 5초로 설정하여 처음 아이템 생성
        itemTimer.start();
        isItemActive = false;

        playerHealthImages = new ArrayList<>();
        String imagePath = "images/player_hp.png";
        try {
            playerHealthImage = ImageIO.read(new File("images/player_0hp.png"));
        } catch (IOException e) {
        }
        for (int i = 0; i <10; i++) { //하트 5개 생성
            try {
                Image healthImage = ImageIO.read(new File(imagePath));
                playerHealthImages.add(healthImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Override

    public void actionPerformed(ActionEvent e) {
        player.move(upPressed, downPressed, leftPressed, rightPressed);
        monster.chasePlayer(player);

        if (monster.getProjectileCooldown() == 0) {
            int direction = (int) (Math.random() * 4);
            int projectileX = (int) (monster.getX() + monster.getWidth() / 2 - PROJECTILE_WIDTH / 2);
            int projectileY = (int) (monster.getY() + monster.getHeight() / 2 - PROJECTILE_HEIGHT / 2);
            Projectile projectile = null;

            switch (direction) {
                case 0:  // Up
                    projectile = new Projectile(projectileX, projectileY, 0, -MONSTER_PROJECTILE_SPEED);
                    break;
                case 1:  // Down
                    projectile = new Projectile(projectileX, projectileY, 0, MONSTER_PROJECTILE_SPEED);
                    break;
                case 2:  // Left
                    projectile = new Projectile(projectileX, projectileY, -MONSTER_PROJECTILE_SPEED, 0);
                    break;
                case 3:  // Right
                    projectile = new Projectile(projectileX, projectileY, MONSTER_PROJECTILE_SPEED, 0);
                    break;
            }

            if (projectile != null) {
                monsterProjectiles.add(projectile);
                monster.resetProjectileCooldown();
            }
        } else {
            monster.decreaseProjectileCooldown();
        }

        for (int i = 0; i < playerProjectiles.size(); i++) {
            Projectile projectile = playerProjectiles.get(i);
            projectile.move();

            if (projectile.isOffScreen(SCREEN_HEIGHT)) {
                playerProjectiles.remove(i);
                i--;
            } else if (projectile.intersects(monster)) {
                playerProjectiles.remove(i);
                i--;
                monsterHealth -= 50;
                if (monsterHealth <= 0) {
                    // 몬스터 격파, 게임 오버 로직 처리
                    JOptionPane.showMessageDialog(null, "승리하였습니다!");
                }
            }
        }

        for (int i = 0; i < monsterProjectiles.size(); i++) {
            Projectile projectile = monsterProjectiles.get(i);
            projectile.move();

            if (projectile.isOffScreen(SCREEN_HEIGHT)) {
                monsterProjectiles.remove(i);
                i--;
            } else if (projectile.intersects(player)) {
                monsterProjectiles.remove(i);
                i--;
                playerHealth -= 10;
                if (playerHealth <= 0) {
                    // 플레이어 격파, 게임 오버 로직 처리
                    JOptionPane.showMessageDialog(null, "패배하였습니다!");

                }

            }

        }
        if (e.getSource() == itemTimer) {
            if (!isItemActive) {
                int itemX = (int) (Math.random() * (SCREEN_WIDTH - item.getWidth()));
                int itemY = (int) (Math.random() * (SCREEN_HEIGHT - item.getHeight()));
                if (itemX < 0) {
                    itemX = 0;
                }
                if (itemY < 0) {
                    itemY = 0;
                }
                if (itemX + item.getWidth() > SCREEN_WIDTH) {
                    itemX = (int) (SCREEN_WIDTH - item.getWidth());
                }
                if (itemY + item.getHeight() > SCREEN_HEIGHT) {
                    itemY = (int) (SCREEN_HEIGHT - item.getHeight());
                }

                item = new Item(itemX, itemY, 15, 15);
                isItemActive = true;
            }
        }
        // 플레이어와 아이템의 충돌을 확인하고 처리합니다.
        if (isItemActive) {
            checkItemCollision();

        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.WHITE);
        g.drawString("플레이어 체력: " + playerHealth, 10, 20);
        g.drawString("몬스터 체력: " + monsterHealth, SCREEN_WIDTH - 150, 20);

        player.draw(g, "images/P.png");
        monster.draw(g, "images/M.png");

        int healthImageWidth = playerHealthImages.get(0).getWidth(null);
        int healthImageHeight = playerHealthImages.get(0).getHeight(null);
        int maxHealthImages = 10;
        int x = 10;
        int y = 30;

        if (isItemActive) {
            item.draw(g, "images/player_hp.png"); // 아이템 이미지 경로를 적절히 수정해야 합니다.
        }

        for (int i = 0; i < maxHealthImages; i++) {
            if (i >= playerHealth / 10) {
                // 체력이 i * 20보다 작으면 player_hp.png 이미지로 채움
                g.drawImage(playerHealthImage, x, y,30,30, null);
            } else {
                // 체력이 i * 20 이상이면 player_0hp.png 이미지로 채움
                g.drawImage(playerHealthImages.get(i), x, y, 30,30,null);
            }
            x += 20; // 이미지 간격 조절
        }

        for (Projectile projectile : playerProjectiles) {
            projectile.draw(g, "images/A.png");
        }

        for (Projectile projectile : monsterProjectiles) {
            projectile.draw(g, "images/A.png");
        }
    }
    private void checkItemCollision() {
        if (player.getBounds().intersects(item.getBounds())) {
            //System.out.println("작동2");
            player.heal();
            isItemActive = false; // Deactivate the item
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed = true;
            lastPressedDirection = KeyEvent.VK_UP;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = true;
            lastPressedDirection = KeyEvent.VK_DOWN;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
            lastPressedDirection = KeyEvent.VK_LEFT;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            lastPressedDirection = KeyEvent.VK_RIGHT;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            spacePressed = true;
            firePlayerProjectile();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    private void firePlayerProjectile() {
        int projectileX = (int) (player.getX() + player.getWidth() / 2 - PROJECTILE_WIDTH / 2);
        int projectileY = (int) (player.getY() + player.getHeight() / 2 - PROJECTILE_HEIGHT / 2);
        int dx = 0;
        int dy = 0;

        if (upPressed) {
            dy = -PLAYER_PROJECTILE_SPEED;
        } else if (downPressed) {
            dy = PLAYER_PROJECTILE_SPEED;
        } else if (leftPressed) {
            dx = -PLAYER_PROJECTILE_SPEED;
        } else if (rightPressed) {
            dx = PLAYER_PROJECTILE_SPEED;
        }

        if (dx != 0 || dy != 0) {
            Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
            playerProjectiles.add(projectile);
        }
    }
}