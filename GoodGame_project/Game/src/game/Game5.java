package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;




public class Game5 extends JPanel implements ActionListener, KeyListener {

    private long startTime;
    private long endTime;

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 900;
    private static final int PLAYER_WIDTH = 30;
    private static final int PLAYER_HEIGHT = 50;

    public static final int PROJECTILE_WIDTH = 10;
    public static final int PROJECTILE_HEIGHT = 10;
    private static final int PLAYER_PROJECTILE_SPEED = 5;
    private static final int MONSTER_PROJECTILE_SPEED = 3;

    private static final int DB_ROTATION_COUNT = 8;
    private static final int DB_RADIUS = 200;
    private static final double DB_ROTATION_SPEED = 0.005;
    private double dbRotationAngle;  // DB 이미지 회전 각도
    private BufferedImage dbImage;
    private long lastDBDamageTime;  // 마지막 DB 데미지 적용 시간

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
    private Image background;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private int lastPressedDirection;



    private boolean isCollisionDisabled = false;
    private Timer collisionDisableTimer;

    private Item key;
    private Item glass;
    private boolean isMonsterAlive;
    private boolean isTeacherAlive;
    private boolean keyCard;
    private boolean isWearGlass;
    private boolean temp;

    private String mImage;
    private Timer monsterAttackTimer;  // 몬스터 공격 타이머
    private int projectileCount;
    private int stage;
    private boolean p_direction; // 플레이어 왼쪽보면 true, 오른쪽보면 false

    private int MONSTER_WIDTH = 100;
    private int MONSTER_HEIGHT = 100;

    public Game5() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        monsterAttackTimer = new Timer(1000, this);  // 1초마다 타이머 이벤트 발생
        monsterAttackTimer.start();
        projectileCount = 0;

        playerHealth = 100;
        monsterHealth = 300;
        stage = 0;

        dbRotationAngle = 0;
        lastDBDamageTime = 0;

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

        isMonsterAlive = true;
        keyCard = false;
        isTeacherAlive = false;
        isWearGlass = false;
        temp = false;

        key = new Item(800, 100, 40, 40);
        glass = new Item(800, 600, 40, 40);

        mImage = "images/Monster.png";

        playerHealthImages = new ArrayList<>();

        String imagePath = "images/player_hp.png";
        try {
            playerHealthImage = ImageIO.read(new File("images/player_0hp.png"));
            background = ImageIO.read(new File("images/Background_window.png"));
            BufferedImage originalDBImage = ImageIO.read(new File("images/DB.png"));
            int dbImageWidth = 50;  // 원하는 이미지 가로 크기
            int dbImageHeight = 50;  // 원하는 이미지 세로 크기
            dbImage = resizeImage(originalDBImage, dbImageWidth, dbImageHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <10; i++) { //하트 10개 생성
            try {
                Image healthImage = ImageIO.read(new File(imagePath));
                playerHealthImages.add(healthImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        collisionDisableTimer = new Timer(2000, e -> {
            isCollisionDisabled = false;
            collisionDisableTimer.stop();
        });
        collisionDisableTimer.setRepeats(false);

        startTime = System.currentTimeMillis();



    }

    public ArrayList<String> loadPlayTimeFromFile() {
        ArrayList<String> playTimes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("playtime.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("플레이 시간 : ")) {
                    playTimes.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playTimes;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (monsterHealth <= 0 || playerHealth <= 0) {
            endTime = System.currentTimeMillis();
            long playTime = endTime - startTime;
            savePlayTimeToFile(playTime);
        }

        player.move(upPressed, downPressed, leftPressed, rightPressed);
        monster.chasePlayer(player);

        if(isMonsterAlive && !isTeacherAlive) {
            attack1();
        }
        if (isMonsterAlive && isTeacherAlive) {
            if (stage == 1) {
                attack2(e);
            } else if (stage == 2) {
                attack3(e);
            } else{
                attack4(e);
            }
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
                if(monsterHealth > 0)
                    monsterHealth -= 50;
                if(isTeacherAlive) {
                    if (monsterHealth <= 0) {
                        // 몬스터 격파, 게임 오버 로직 처리
                        JOptionPane.showMessageDialog(null, "승리하였습니다!");
                        System.exit(0);
                    }
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
                if(!temp) {
                    if (playerHealth <= 9900) {
                        isWearGlass = true;
                        if (player.getBounds().intersects(glass.getBounds())) {
                            //System.out.println("작동");
                            isWearGlass = false;
                            playerHealth = 100;
                            temp = true;
                        }

                    }
                }
                if (playerHealth == 0) {
                    // 플레이어 격파, 게임 오버 로직 처리
                    JOptionPane.showMessageDialog(null, "패배하였습니다!");
                    System.exit(0);
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
//                item = new Item(SCREEN_WIDTH / 2-25, SCREEN_HEIGHT / 2- 25 , 15, 15);
//                isItemActive = true;
            }
        }
        ///만약 몬스터의 체력이 0이면 키가 나온다
        if (!isTeacherAlive) {
            if (monsterHealth == 0) {
                isMonsterAlive = false;
                keyCard = true;
                if (player.getBounds().intersects(key.getBounds())) {
                    //System.out.println("작동");
                    keyCard = false;
                    JOptionPane.showMessageDialog(null, "선생님이 등장합니다!");
                    stage = 1;
                    resetGame();
                }
            }
        } else{
            if (monsterHealth <= 700 && monsterHealth > 300) {
                stage = 2;
            } else if (monsterHealth <= 300) {
                stage = 3;
                mImage = "images/Teacher_2.png";
            }
        }

        if(isItemActive) {
            checkItemCollision();
        }

        if (stage == 3) {

            if (isItemActive && !isCollisionDisabled) {
                double dbCenterX = monster.getX() + monster.getWidth() / 2;
                double dbCenterY = monster.getY() + monster.getHeight() / 2;

                double playerCenterX = player.getX() + player.getWidth() / 2;
                double playerCenterY = player.getY() + player.getHeight() / 2;

                double distance = Math.sqrt(Math.pow(dbCenterX - playerCenterX, 2) + Math.pow(dbCenterY - playerCenterY, 2));

                if (distance < (DB_RADIUS + player.getWidth() / 2)) {
                    // 충돌 시 플레이어의 체력 감소
                    playerHealth -= 10;
                    isCollisionDisabled = true;
                    collisionDisableTimer.start();
                }
                if (playerHealth <= 0) {
                    // 플레이어 격파, 게임 오버 로직 처리
                    JOptionPane.showMessageDialog(null, "패배하였습니다!");

                }

                checkItemCollision();

            }
        }

        dbRotationAngle += DB_ROTATION_SPEED;
        if (dbRotationAngle >= 2 * Math.PI) {
            dbRotationAngle = 0;

        }
        System.currentTimeMillis();

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
        g.setColor(Color.WHITE);
        g.drawString("플레이어 체력: " + playerHealth, 10, 20);
        g.drawString("몬스터 체력: " + monsterHealth, SCREEN_WIDTH - 150, 20);
        String PlayerD;
        if (p_direction) {
            PlayerD = "images/Player_L.png";
        } else {
            PlayerD = "images/Player_R.png";
        }

        if (temp) {
            if (p_direction) {
                PlayerD = "images/Player_Glasses_L.gif";
            } else {
                PlayerD = "images/Player_Glasses_R.gif";
            }
        }
        //player.draw(g, "images/P.png");
        player.draw(g, PlayerD);
        if (isMonsterAlive) {
            monster.draw(g, mImage);
        }

        int healthImageWidth = playerHealthImages.get(0).getWidth(null);
        int healthImageHeight = playerHealthImages.get(0).getHeight(null);
        int maxHealthImages = 10;
        int x = 10;
        int y = 30;

        if (isItemActive) {
            item.draw(g, "images/player_hp.png"); // 아이템 이미지 경로를 적절히 수정해야 합니다.
        }
        if (monsterHealth == 0 && keyCard == true) {
            key.draw(g, "images/Key.png");
        }
        if (isWearGlass == true) {
            glass.draw(g,"images/Glasses.gif");
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
            projectile.draw(g, "images/Player_Protectile.png");
        }

        for (Projectile projectile : monsterProjectiles) {
            projectile.draw(g, "images/DB.png");
        }

        if(stage==3) {
            double centerX = monster.getX() + monster.getWidth() / 2;
            double centerY = monster.getY() + monster.getHeight() / 2;
            double radius = DB_RADIUS;

            for (int i = 0; i < DB_ROTATION_COUNT; i++) {
                double angle = i * (2 * Math.PI / DB_ROTATION_COUNT) + dbRotationAngle;
                int dbX = (int) (centerX + radius * Math.cos(angle) - dbImage.getWidth() / 2);
                int dbY = (int) (centerY + radius * Math.sin(angle) - dbImage.getHeight() / 2);
                g.drawImage(dbImage, dbX, dbY, null);
            }
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
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            p_direction = true;
            leftPressed = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            p_direction = false;
            rightPressed = true;
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

    public void resetGame() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = false;

        player.x = SCREEN_WIDTH / 2 - PLAYER_WIDTH / 2;
        player.y = SCREEN_HEIGHT - PLAYER_HEIGHT - 10;
        monster.x = SCREEN_WIDTH / 2 - MONSTER_WIDTH / 2;
        monster.y = 10;


        playerHealth = 100;
        monsterHealth = 1000;

        playerProjectiles.clear();
        monsterProjectiles.clear();
        mImage = "images/Teacher_1.png";

        isMonsterAlive = true;
        keyCard = false;
        isItemActive = false;
        isTeacherAlive = true;
        itemTimer.setDelay(0);

        itemTimer.restart();
        timer.restart();
    }

    public void attack1() {
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
    }

    public void attack2(ActionEvent e) {
        if (e.getSource() == monsterAttackTimer) {
            if (projectileCount < 36) {  // 발사체 수가 36개 미만일 때에만 발사
                double angle = projectileCount * (360.0 / 36);  // 원형으로 발사체를 배치하기 위한 각도 계산
                double radians = Math.toRadians(angle);  // 각도를 라디안으로 변환
                double dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;  // X축 이동량 계산
                double dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;  // Y축 이동량 계산

                int projectileX = (int) (monster.getX() + monster.getWidth() / 2 - PROJECTILE_WIDTH / 2);
                int projectileY = (int) (monster.getY() + monster.getHeight() / 2 - PROJECTILE_HEIGHT / 2);

                for (int i = 0; i < 36; i++) {
                    Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                    monsterProjectiles.add(projectile);

                    angle += 36; // 10도씩 증가하여 다음 발사체의 각도 계산
                    radians = Math.toRadians(angle);
                    dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;
                    dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;
                }

                projectileCount += 36;  // 발사체 수 증가
            } else {
                projectileCount = 0;
            }
        }
    }
    public void attack3(ActionEvent e) {
        if (e.getSource() == monsterAttackTimer) {
            if (projectileCount < 500) {
                int projectileX = (int) (monster.getX() + monster.getWidth() / 2 - PROJECTILE_WIDTH / 2);
                int projectileY = (int) (monster.getY() + monster.getHeight() / 2 - PROJECTILE_HEIGHT / 2);

                if (projectileCount % 5 == 0) {
                    // 원형으로 배치
                    for (int i = 0; i < 36; i++) {
                        double angle = i * (360.0 / 36);
                        double radians = Math.toRadians(angle);
                        double dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;
                        double dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;

                        Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                        monsterProjectiles.add(projectile);
                    }
                } else if (projectileCount % 5 == 1) {
                    // 직선으로 배치
                    for (int i = 0; i < 10; i++) {
                        double dx = 0;
                        double dy = MONSTER_PROJECTILE_SPEED * (i + 1);

                        Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                        monsterProjectiles.add(projectile);
                    }
                } else if (projectileCount % 5 == 2) {
                    // 대각선으로 배치
                    double diagonalRadians = Math.toRadians(45);
                    double diagonalDx = Math.cos(diagonalRadians) * MONSTER_PROJECTILE_SPEED;
                    double diagonalDy = Math.sin(diagonalRadians) * MONSTER_PROJECTILE_SPEED;

                    for (int i = 0; i < 10; i++) {
                        Projectile projectile = new Projectile(projectileX, projectileY, diagonalDx * (i + 1), diagonalDy * (i + 1));
                        monsterProjectiles.add(projectile);
                    }
                } else if (projectileCount % 5 == 3) {
                    // 부채꼴 모양으로 배치
                    for (int i = 0; i < 20; i++) {
                        double angle = i * (360.0 / 20);
                        double radians = Math.toRadians(angle);
                        double dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;
                        double dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;

                        Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                        monsterProjectiles.add(projectile);
                    }
                } else {
                    // 날개 모양으로 배치
                    for (int i = 0; i < 40; i++) {
                        double angle = i * (360.0 / 40);
                        double radians = Math.toRadians(angle);
                        double dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;
                        double dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;

                        Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                        monsterProjectiles.add(projectile);
                    }
                }

                projectileCount++;
            }
        }
    }
    public void attack4(ActionEvent e) {
        if (e.getSource() == monsterAttackTimer) {
            if (projectileCount < 15) {  // 발사체 수가 36개 미만일 때에만 발사
                double angle = projectileCount * (360.0 / 36);  // 원형으로 발사체를 배치하기 위한 각도 계산
                double radians = Math.toRadians(angle);  // 각도를 라디안으로 변환
                double dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;  // X축 이동량 계산
                double dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;  // Y축 이동량 계산

                int projectileX = (int) (monster.getX() + monster.getWidth() / 2 - PROJECTILE_WIDTH / 2);
                int projectileY = (int) (monster.getY() + monster.getHeight() / 2 - PROJECTILE_HEIGHT / 2);

                for (int i = 0; i < 15; i++) {
                    Projectile projectile = new Projectile(projectileX, projectileY, dx, dy);
                    monsterProjectiles.add(projectile);

                    angle += 24; // 10도씩 증가하여 다음 발사체의 각도 계산
                    radians = Math.toRadians(angle);
                    dx = Math.cos(radians) * MONSTER_PROJECTILE_SPEED;
                    dy = Math.sin(radians) * MONSTER_PROJECTILE_SPEED;
                }

                projectileCount += 36;  // 발사체 수 증가
            } else {
                projectileCount = 0; // 0으로 초기화 안하면 한번 쏘고 땡
            }
        }
    }

    private void savePlayTimeToFile(long playTime) {
        String filename = "playtime.txt";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = dateFormat.format(new Date());

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println("플레이 시간 : " + playTime + "ms");
            writer.println("종료 시간 : " + formattedTime);
            writer.println("--------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}