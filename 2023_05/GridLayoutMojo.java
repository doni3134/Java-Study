package ch15;

import javax.swing.*;
import java.awt.*;

public class GridLayoutMojo extends JFrame {
    GridLayoutMojo(){
        setTitle("그리드 레이아웃!");
        setLayout(new GridLayout(0, 3));

        add(new JButton("B 1"));
        add(new JButton("버튼 2"));
        add(new JButton("Button 3"));
        add(new JButton("Button Four"));
        add(new JButton("Button Four!"));
        add(new JButton("Button Four!!"));
        add(new JButton("Button Four!!!"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350,110);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutMojo();
    }
}
