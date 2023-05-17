package ch15;

import java.awt.*;
import javax.swing.*;
//import javax.swing.JButton;


public class BorderLayoutDemo extends JFrame {
    BorderLayoutDemo(){
        setTitle("보더 레이아웃!");
//        setLayout(new java.awt.BorderLayout());

        add("East", new JButton("백호"));
        add("West", new JButton("청룡"));
        add("South", new JButton("주작"));
        add(new JButton("현무"), BorderLayout.NORTH);
        add(new JButton("개발자 김경돈"), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

    }

    public static void main(String[] args) {
        new BorderLayoutDemo();
    }
}
