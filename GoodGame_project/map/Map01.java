package map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Map01 extends JFrame {

    Map01() {
        setTitle("맵적용테스트");

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        add(backgroundPanel);

        JTextField startTextField = new JTextField("START");
        startTextField.setEditable(false);
        startTextField.setHorizontalAlignment(JTextField.CENTER);

        JPanel startPanel = new JPanel(null); // 레이아웃 null로 설정
        startPanel.setOpaque(false);
        startPanel.add(startTextField);
        startTextField.setBounds(0, 0, 100, 30); // 텍스트 상자 위치와 크기 설정

        startTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(Map01.this, "START 버튼을 클릭했습니다!");
            }
        });
        startTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Map01.this, "START 버튼을 눌렀습니다!");
            }
        });

        add(startPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Map01();
    }

    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

//        BackgroundPanel() {
//            backgroundImage = Toolkit.getDefaultToolkit().getImage("D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\map");
//        }
        BackgroundPanel() {
            // 이미지 파일 경로
            String imagePath = "D:\\workspase_intelliJ_IDEA\\javaJazz\\gg\\src\\map\\map.png";
            // 이미지 파일을 Image 객체로 로드
            backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
