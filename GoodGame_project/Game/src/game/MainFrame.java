package game;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MainFrame extends JFrame {
    private JButton startButton; //스타트 버튼
    private JButton recordButton; //기록보기 버튼
    private JButton exitButton; //나가기 버튼
    private JButton questButton; //도움말 버튼
    private JButton creditButton; //엔딩크레딧 버튼
    private JLabel backgroundMain;
    private ImageIcon startIcon;  //스타트 아이콘
    private ImageIcon sthoverIcon;  //스타트 호버
    private ImageIcon recordIcon; //기록보기 아이콘
    private ImageIcon rchoverIcon; //기록보기 호버
    private ImageIcon exitIcon; //나가기 아이콘
    private ImageIcon exhoverIcon; //나가기 호버
    private ImageIcon questIcon; //도움말 아이콘
    private ImageIcon quhoverIcon; //도움말 호버
    private ImageIcon creditIcon; //크레딧 아이콘
    private ImageIcon cdhoverIcon; //크레딧 호버

    public MainFrame() {
        initObject();
        initSetting();
        setVisible(true);
    }

    private void initObject() {
        backgroundMain = new JLabel(new ImageIcon("images/backgroundMain.jpg"));
        setContentPane(backgroundMain);

        //타이틀 이미지
        JLabel titleLabel = new JLabel(new ImageIcon("images/title.png"));
        titleLabel.setBounds(380, 40, 400, 200);
        getContentPane().add(titleLabel);

        //기록보기 업데이트 이미지
        JLabel updateLabel = new JLabel(new ImageIcon("images/update.gif"));
        updateLabel.setBounds(680, 380, 350, 150);
        getContentPane().add(updateLabel);


        //스타트 버튼
        startIcon = new ImageIcon("images/start1.png");
        Image startImage = startIcon.getImage().getScaledInstance(startIcon.getIconWidth() * 2, startIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(startImage);

        sthoverIcon = new ImageIcon("images/start2.png");
        Image sthoverImage = sthoverIcon.getImage().getScaledInstance(sthoverIcon.getIconWidth() * 2, sthoverIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        sthoverIcon = new ImageIcon(sthoverImage);

        startButton = new JButton(startIcon);
        startButton.setBounds(450, 250, startIcon.getIconWidth(), startIcon.getIconHeight());
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);

        // Add mouse listener to change the button's icon on hover
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(sthoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startIcon);
            }
        });

        //기록 보기 버튼
        recordIcon = new ImageIcon("images/record1.png");
        Image recordButtonImage = recordIcon.getImage().getScaledInstance(recordIcon.getIconWidth() * 2, recordIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        recordIcon = new ImageIcon(recordButtonImage);

        rchoverIcon = new ImageIcon("images/record2.png");
        Image rchoverImage = rchoverIcon.getImage().getScaledInstance(rchoverIcon.getIconWidth() * 2, rchoverIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        rchoverIcon = new ImageIcon(rchoverImage);

        recordButton = new JButton(recordIcon);
        recordButton.setBounds(450, 400, recordIcon.getIconWidth(), recordIcon.getIconHeight());
        recordButton.setBorderPainted(false);
        recordButton.setContentAreaFilled(false);
        recordButton.setFocusPainted(false);
        recordButton.setOpaque(false);

        recordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                recordButton.setIcon(rchoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                recordButton.setIcon(recordIcon);
            }
        });

        //나가기 버튼
        exitIcon = new ImageIcon("images/exit1.png");
        Image exitButtonImage = exitIcon.getImage().getScaledInstance(exitIcon.getIconWidth() * 2, exitIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(exitButtonImage);

        exhoverIcon = new ImageIcon("images/exit2.png");
        Image exhoverImage = exhoverIcon.getImage().getScaledInstance(exhoverIcon.getIconWidth() * 2, exhoverIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        exhoverIcon = new ImageIcon(exhoverImage);

        exitButton = new JButton(exitIcon);
        exitButton.setBounds(450, 550, exitIcon.getIconWidth(), exitIcon.getIconHeight());
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exhoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitIcon);
            }
        });

        //도움말
        questIcon = new ImageIcon("images/question1.png");
        Image questButtonImage = questIcon.getImage().getScaledInstance(questIcon.getIconWidth() * 2, questIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        questIcon = new ImageIcon(questButtonImage);

        quhoverIcon = new ImageIcon("images/question2.png");
        Image quhoverImage = quhoverIcon.getImage().getScaledInstance(quhoverIcon.getIconWidth() * 2, quhoverIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        quhoverIcon = new ImageIcon(quhoverImage);

        questButton = new JButton(questIcon);
        questButton.setBounds(1080, 30, questIcon.getIconWidth(), questIcon.getIconHeight());
        questButton.setBorderPainted(false);
        questButton.setContentAreaFilled(false);
        questButton.setFocusPainted(false);
        questButton.setOpaque(false);

        questButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                questButton.setIcon(quhoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                questButton.setIcon(questIcon);
            }
        });

        //엔딩 크레딧
        creditIcon = new ImageIcon("images/credit1.png");
        Image creditButtonImage = creditIcon.getImage().getScaledInstance(creditIcon.getIconWidth() * 2, creditIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        creditIcon = new ImageIcon(creditButtonImage);

        cdhoverIcon = new ImageIcon("images/credit2.png");
        Image cdhoverImage = cdhoverIcon.getImage().getScaledInstance(cdhoverIcon.getIconWidth() * 2, cdhoverIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        cdhoverIcon = new ImageIcon(cdhoverImage);

        creditButton = new JButton(creditIcon);
        creditButton.setBounds(40, 40, creditIcon.getIconWidth(), creditIcon.getIconHeight());
        creditButton.setBorderPainted(false);
        creditButton.setContentAreaFilled(false);
        creditButton.setFocusPainted(false);
        creditButton.setOpaque(false);

        creditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                creditButton.setIcon(cdhoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                creditButton.setIcon(creditIcon);
            }
        });

        // 연결 작업 ~~ 게임시작 버튼 클릭시 연결
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new Game5());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                setVisible(false); // MainFrame을 숨깁니다.
            }
        });

        // 엔딩 크레딧 버튼 클릭시 연결
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EndingCredit();
                setVisible(false);
            }
        });

        // 도움말 버튼 클릭시 연결
        questButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Question();
                setVisible(false);
            }
        });

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game5 game = new Game5();
                ArrayList<String> playTimes = game.loadPlayTimeFromFile();
                StringBuilder record = new StringBuilder();
                for (String playTime : playTimes) {
                    record.append(playTime).append("\n");
                }

                JTextArea textArea = new JTextArea(record.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 200));

                JOptionPane.showMessageDialog(null, scrollPane, "게임 시간 기록", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        //나가기 버튼 누르면 프로그램 종료
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });




        getContentPane().add(startButton);
        getContentPane().add(recordButton);
        getContentPane().add(exitButton);
        getContentPane().add(questButton);
        getContentPane().add(creditButton);
    }

    private void initSetting() {
        setTitle("보스피하기");
        setSize(1200, 740);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}