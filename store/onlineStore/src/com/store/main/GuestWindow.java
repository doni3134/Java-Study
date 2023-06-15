package com.store.main;

import com.store.member.UserInIt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestWindow extends JFrame {

    public GuestWindow(String title, int x, int y, int width, int height) {

        initContainer(title, x, y, width, height);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("src/images/shop.png").getImage());
    }

    private void initContainer(String title, int x, int y, int width, int height) {
        setTitle(title);
        setBounds(x, y, width, height);
        setLayout(null);

        Font ft;
        ft = new Font("함초롬돋움", Font.BOLD, 15);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - 1000) / 2, (screenSize.height - 750) / 2);

        JPanel userPanel = new JPanel();
        userPanel.setBounds(0, 100, 1000, 256);

        ImageIcon imageIcon = new ImageIcon("src/images/user.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
        JLabel userLabel = new JLabel(imageIcon);
        userPanel.add(userLabel);
        add(userPanel);

        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 350, 1000, 50);
        add(titlePanel);

        JLabel titleLabel = new JLabel("-- 고객 정보를 입력하세요 --");
        titleLabel.setFont(ft);
        titleLabel.setForeground(Color.BLUE);
        titlePanel.add(titleLabel);

        JPanel namePanel = new JPanel();
        namePanel.setBounds(0, 400, 1000, 50);
        add(namePanel);

        JLabel nameLabel = new JLabel("이   름 : ");
        nameLabel.setFont(ft);
        namePanel.add(nameLabel);

        JTextField nameField = new JTextField(10);
        nameField.setFont(ft);
        namePanel.add(nameField);

        JPanel phonePanel = new JPanel();
        phonePanel.setBounds(0, 450, 1000, 50);
        add(phonePanel);

        JLabel phoneLabel = new JLabel("연락처 : ");
        phoneLabel.setFont(ft);
        phonePanel.add(phoneLabel);

        JTextField phoneField = new JTextField(10);
        phoneField.setFont(ft);
        phonePanel.add(phoneField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 500, 1000, 100);
        add(buttonPanel);

        JLabel buttonLabel = new JLabel("쇼핑하기", new ImageIcon("images/shop.png"), JLabel.LEFT);
        buttonLabel.setFont(ft);
        JButton enterButton = new JButton();
        enterButton.add(buttonLabel);
        buttonPanel.add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JLabel message = new JLabel("고객 정보를 입력하세요");
                message.setFont(ft); // 대화상자의 메시지 폰트 설정

                if (nameField.getText().isEmpty() || phoneField.getText().isEmpty())
                    JOptionPane.showMessageDialog(enterButton, message, "고객 정보", JOptionPane.ERROR_MESSAGE);
                else {

                    UserInIt.init(nameField.getText(), Integer.parseInt(phoneField.getText())); // 입력한 고객 정보 저장
                    dispose(); // 대화상자 닫기
                    new MainWindow("온라인 Store", 0, 0, 1000, 750);
// MainWindow 프레임 호출
                }
            }
        });
    }
    // 임시 Test !!!
    public static void main(String[] args) {
        new GuestWindow("Test&Debugging", 0,0,1000,1000);
    }
}