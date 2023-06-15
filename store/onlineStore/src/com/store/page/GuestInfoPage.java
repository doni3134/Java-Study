package com.store.page;

import com.store.member.UserInIt;

import javax.swing.*;
import java.awt.*;


public class GuestInfoPage extends  JPanel {
    public GuestInfoPage(JPanel panel){
        Font ft;
        ft = new Font("함초롬돋움", Font.BOLD, 15);

        setLayout(null);

        Rectangle rect = panel.getBounds();
        System.out.println(rect);
        setPreferredSize(rect.getSize());

        JPanel namePanel = new JPanel();
        namePanel.setBounds(0,100,1000,50);
        add(namePanel);
        JLabel nameLabel = new JLabel("성 명");
        nameLabel.setFont(ft);
        nameLabel.setBackground(Color.BLUE);

        JLabel nameField = new JLabel();
        nameField.setText(UserInIt.getmUser().getName());
        nameField.setFont(ft);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel phonePanel = new JPanel();
        phonePanel.setBounds(0,150,1000,100);
        add(phonePanel);
        JLabel phoneLabel = new JLabel("연락처 :");
        phoneLabel.setFont(ft);
        JLabel phoneField = new JLabel();
        phoneField.setText(String.valueOf(UserInIt.getmUser().getPhone()));
        phoneField.setFont(ft);

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,1000,750);
        frame.setLayout(null);

        JPanel mPagePanel = new JPanel();
        mPagePanel.setBounds(0,0,1000,750);

        frame.add(mPagePanel);
        mPagePanel.add("", new GuestInfoPage(mPagePanel));
        frame.setVisible(true);
    }
}
