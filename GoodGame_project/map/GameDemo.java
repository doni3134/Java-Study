package ch15;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;



public class GameDemo extends JPanel implements ActionListener, KeyListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int MONSTER_WIDTH = 50;
    private static final int MONSTER_HEIGHT = 50;
    private static final int PROJECTILE_WIDTH = 10;
    private static final int PROJECTILE_HEIGHT = 10;
    private static final int PLAYER_PROJECTILE_SPEED = 5;
    private static final int MONSTER_PROJECTILE_SPEED = 3;
    private static final int MONSTER_PROJECTILE_COOLDOWN = 100;


    private Item item;
    private Timer itemTimer;
    private boolean isItemActive;
    private Player player;
    private Monster monster;
    private ArrayList<Projectile> playerProjectiles;
    private ArrayList<Projectile> monsterProjectiles;
    private Timer timer;
    private int playerHealth;
    private int monsterHealth;
    private ArrayList<Image> playerHealthImages;
    private Image playerHealthImage;





    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private int lastPressedDirection;
    private Graphics g;

    public GameDemo() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        playerHealth = 100;
        monsterHealth = 1000;

        player = new Player(SCREEN_WIDTH / 2 - PLAYER_WIDTH / 2, SCREEN_HEIGHT - PLAYER_HEIGHT - 10, PLAYER_WIDTH, PLAYER_HEIGHT);
        monster = new Monster(SCREEN_WIDTH / 2 - MONSTER_WIDTH / 2, 10, MONSTER_WIDTH, MONSTER_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
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
        for (int i = 0; i <5; i++) { //하트 5개 생성
            String imagePath = "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\player_hp.png";

            try {
                Image healthImage = ImageIO.read(new File(imagePath));

                playerHealthImages.add(healthImage);
                playerHealthImage = ImageIO.read(new File("D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\player_0hp.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GameDemo());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
                item = new Item(SCREEN_WIDTH / 2 - 25, SCREEN_HEIGHT / 2 - 25, 15, 15);
                isItemActive = true;
            }
        }
        //플레이어와 아이템이 충돌했을때 플레이어의 체력을 회복함!
        if (isItemActive) {
            //System.out.println("작동");
            if (player.intersects(item)) {
                player.heal(); // 체력 회복
                isItemActive = false; // 아이템 비활성화
                // 아이템을 먹었을 때 추가로 처리해야 할 작업이 있다면 여기에 작성합니다.
            }
        }
        // 플레이어와 아이템의 충돌을 확인하고 처리합니다.
        checkItemCollision();


        if (!isItemActive && itemTimer.getDelay() == 0) {
            int itemX = (int) (Math.random() * (SCREEN_WIDTH - item.getWidth()));
            int itemY = (int) (Math.random() * (SCREEN_HEIGHT - item.getHeight()));

            item.setLocation(itemX, itemY);
            isItemActive = true;

            itemTimer.setDelay(5000);
            itemTimer.setInitialDelay(5000);
        }



        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.WHITE);
        g.drawString("플레이어 체력: " + playerHealth, 10, 20);
        g.drawString("몬스터 체력: " + monsterHealth, SCREEN_WIDTH - 150, 20);

        player.draw(g, "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\P.png");
        monster.draw(g, "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\M.png");

        int healthImageWidth = playerHealthImages.get(0).getWidth(null);
        int healthImageHeight = playerHealthImages.get(0).getHeight(null);
        int maxHealthImages = 5;
        int x = 10;
        int y = 30;

        if (isItemActive) {
            item.draw(g, "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\player_hp.png"); // 아이템 이미지 경로를 적절히 수정해야 합니다.
        }

        for (int i = 0; i < maxHealthImages; i++) {
            if (i >= playerHealth / 20) {
                // 체력이 i * 20보다 작으면 player_hp.png 이미지로 채움
                g.drawImage(playerHealthImage, x, y,30,30, null);
            } else {
                // 체력이 i * 20 이상이면 player_0hp.png 이미지로 채움
                g.drawImage(playerHealthImages.get(i), x, y, 30,30,null);
            }
            x += 20; // 이미지 간격 조절
        }

        for (Projectile projectile : playerProjectiles) {
            projectile.draw(g, "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\A.png");
        }

        for (Projectile projectile : monsterProjectiles) {
            projectile.draw(g, "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\imgfiles\\A.png");
        }
    }
    private void checkItemCollision() {
        if (player.getBounds().intersects(item.getBounds())) {
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

    private class Player extends Rectangle {
        private static final long serialVersionUID = 1L;
        private static final int PLAYER_SPEED = 3;
        private static final int MAX_HEALTH = 100;
        private static final int HEAL_AMOUNT = 20;





        public Player(int x, int y, int width, int height) {
            super(x, y, width, height);

        }
        public void heal() {
            if (playerHealth + HEAL_AMOUNT <= MAX_HEALTH) {
                playerHealth += HEAL_AMOUNT;
            } else {
                playerHealth = MAX_HEALTH;
            }
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

    private class Monster extends Rectangle {
        private static final long serialVersionUID = 1L;
        private static final int MONSTER_SPEED = 1;

        private int health;
        private int projectileCooldown;

        public Monster(int x, int y, int width, int height, int screenWidth, int screenHeight) {
            super(x, y, width, height);
            health = 3;
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

    private class Projectile extends Rectangle {
        private static final long serialVersionUID = 1L;

        private int dx;
        private int dy;

        public Projectile(int x, int y, int dx, int dy) {
            super(x, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
            this.dx = dx;
            this.dy = dy;
        }

        public void move() {
            setLocation(x + dx, y + dy);
        }

        public boolean isOffScreen(int screenHeight) {
            return y < 0 || y > screenHeight;
        }

        public void draw(Graphics g, String imagePath) {
            ImageIcon icon = new ImageIcon(imagePath);
            g.drawImage(icon.getImage(), x, y, width, height, null);
        }
    }

    private class Item extends Rectangle {
        private static final long serialVersionUID = 1L;
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
}