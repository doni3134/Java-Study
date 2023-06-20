package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Question extends JFrame{
    private JLabel backgroundMain; //배경화면이미지
    private JButton backButton; //뒤로가기 버튼
    private ImageIcon backIcon; //뒤로가기 아이콘
    private ImageIcon backhovericon; //뒤로가기 호버

    public Question() {
        initObject();
        initSetting();
        setVisible(true);
    }
    private void initObject() {
        backgroundMain = new JLabel(new ImageIcon("images/QuestionBackground.png"));
        setContentPane(backgroundMain);

        //뒤로가기 버튼 구현
        backIcon = new ImageIcon("images/back.png");
        Image backImage = backIcon.getImage().getScaledInstance(backIcon.getIconWidth() * 2, backIcon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(backImage);

        backhovericon = new ImageIcon("images/backhover.png");
        Image backhoverimage = backhovericon.getImage().getScaledInstance(backhovericon.getIconWidth() * 2, backhovericon.getIconHeight() * 2, Image.SCALE_SMOOTH);
        backhovericon = new ImageIcon(backhoverimage);

        backButton = new JButton(backIcon);
        backButton.setBounds(40, 40, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        //호버 설정~
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backhovericon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backhovericon);
            }
        });

        // 뒤로가기 버튼 클릭시 메인페이지 연결
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                setVisible(false);
            }
        });

        getContentPane().add(backButton);
    }
    private void initSetting() {
        setTitle("제작진");
        setSize(1200,740);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}