package ch15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

// TODO: 2023-05-18 개발자 김경돈
public class PlayGame extends JFrame {
        private List<Ball> balls;
        private boolean gameover = false;

        PlayGame() {
            balls = new ArrayList<>();
            balls.add(new Ball(60, 100, 2, 2, 50));
            balls.add(new Ball(20, 300, 2, 2, 30));
            balls.add(new Ball(600, 600, 2, 2, 10));
            balls.add(new Ball(200, 400, 2, 2, 20));
            balls.add(new Ball(450, 800, 2, 2, 25));
            balls.add(new Ball(400, 700, 2, 2, 25));
            balls.add(new Ball(50, 700, 2, 2, 25));
            balls.add(new Ball(50, 550, 2, 2, 25));
            balls.add(new Ball(110, 950, 2, 2, 25));
            balls.add(new Ball(550, 820, 2, 2, 25));
            balls.add(new Ball(150, 50, 2, 2, 25));
            balls.add(new Ball(250, 40, 2, 2, 25));
            balls.add(new Ball(380, 30, 2, 2, 25));
            balls.add(new Ball(490, 40, 2, 2, 25));
            balls.add(new Ball(650, 40, 2, 2, 25));
            balls.add(new Ball(750, 40, 2, 2, 25));
            balls.add(new Ball(830, 40, 2, 2, 25));
            balls.add(new Ball(920, 40, 2, 2, 25));
            balls.add(new Ball(120, 40, 2, 2, 25));
            balls.add(new Ball(220, 40, 2, 2, 25));
            balls.add(new Ball(320, 40, 2, 2, 25));
            balls.add(new Ball(420, 20, 2, 2, 25));
            balls.add(new Ball(520, 40, 2, 2, 25));
            balls.add(new Ball(620, 440, 2, 2, 25));
            balls.add(new Ball(720, 440, 2, 2, 25));

            // 플레이어 공 추가
            PlayerBall playerBall = new PlayerBall(950, 950, 0, 0, 15);
            balls.add(playerBall);

            Timer timer = new Timer(20, e -> {
                for (Ball ball : balls) {
                    ball.move(getWidth(), getHeight());
                }
                repaint();
            });

            timer.start();

            Timer collisionTimer = new Timer(100, e -> {
                Rectangle playerBounds = new Rectangle(playerBall.getX(), playerBall.getY(), playerBall.getRadius(), playerBall.getRadius());
                for (int i = 0; i < balls.size() - 1; i++) {
                    Rectangle circleBounds = new Rectangle(balls.get(i).getX(), balls.get(i).getY(), balls.get(i).getRadius(), balls.get(i).getRadius());
                    if (circleBounds.intersects(playerBounds)) {
                        gameover = true;
                        timer.stop();
                        repaint();
                        JOptionPane.showMessageDialog(null, "Game Over");
                        System.exit(0);
                    }
                }
            });

            collisionTimer.start();

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            playerBall.moveUp();
                            break;
                        case KeyEvent.VK_DOWN:
                            playerBall.moveDown();
                            break;
                        case KeyEvent.VK_LEFT:
                            playerBall.moveLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            playerBall.moveRight();
                            break;
                    }
                }
            });

            setFocusable(true);

            add(new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (gameover) {
                        g.setColor(Color.BLACK);
                        Font font = new Font("Arial", Font.BOLD, 40);
                        g.setFont(font);
                        g.drawString("Game Over", getWidth() / 2 - 70, getHeight() / 2);
                    } else {
                        for (Ball ball : balls) {
                            ball.draw(g);
                        }
                    }
                }
            });

            setTitle("공피하기");
            setSize(1000, 1000);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) {
            new PlayGame();
        }

        class Ball {
            protected int x;
            protected int y;
            protected int dx;
            protected int dy;
            protected int radius;

            public Ball(int x, int y, int dx, int dy, int radius) {
                this.x = x;
                this.y = y;
                this.dx = dx;
                this.dy = dy;
                this.radius = radius;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public int getRadius() {
                return radius;
            }
            private int getRandomDirection() {
                return Math.random() > 0.5 ? 1 : -1; // 벽에 부딪히는 순간부터 랜덤하게 움직임 확률은 50% 1 or -1
            }

            public void move(int screenWidth, int screenHeight) {
                x += dx;
                y += dy;

                if (x <= 0 || x >= screenWidth - radius || y <= 0 || y >= screenHeight - radius) {
                    int randomDirectionX = getRandomDirection();
                    int randomDirectionY = getRandomDirection();

                    // 새로운 방향으로 설정
                    dx = randomDirectionX * Math.abs(dx);
                    dy = randomDirectionY * Math.abs(dy);
                }
            }

            public void draw(Graphics g) {
                g.setColor(Color.RED);
                g.drawOval(x, y, radius, radius);
            }
        }

        class PlayerBall extends Ball {
            private int targetX = -1;
            private int targetY = -1;

            public PlayerBall(int x, int y, int dx, int dy, int radius) {
                super(x, y, dx, dy, radius);
            }

            public void setTargetX(int targetX) {
                this.targetX = targetX;
            }

            public void setTargetY(int targetY) {
                this.targetY = targetY;
            }

            public void moveUp() {
                targetY = y - 60;     //이동거리
            }

            public void moveDown() {
                targetY = y + 60;
            }

            public void moveLeft() {
                targetX = x - 60;
            }

            public void moveRight() {
                targetX = x + 60;
            }

            @Override
            public void move(int screenWidth, int screenHeight) {
                super.move(screenWidth, screenHeight);


                if (targetX != -1) {
                    if (x < targetX) {
                        x += 3;    // x와 y의 + - 부분의 값을 조정해주면 플레이어 공의 속도가 빨라짐
                    } else if (x > targetX) {
                        x -= 3;
                    }
                }
                if (targetY != -1) {
                    if (y < targetY) {
                        y += 3;
                    } else if (y > targetY) {
                        y -= 3;
                    }
                }
            }

            @Override
            public void draw(Graphics g) {
                g.setColor(Color.BLUE);
                g.drawOval(x, y, radius, radius);
            }
        }
    }
