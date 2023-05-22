package ch15;

import javax.swing.*;

// TODO: 2023-05-17 개발자 김경돈
public class CalculatorTest extends JFrame {
    CalculatorTest(){
        setTitle("계산기");

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.add(new JTextField(15));
        add("North",p1);

        JPanel p21 = new JPanel();
        p21.add(new JButton("on"));
        p21.add(new JButton("off"));

        JPanel p31 = new JPanel();
        p31.add(new JButton("7"));
        p31.add(new JButton("8"));
        p31.add(new JButton("9"));
        p31.add(new JButton("+"));

        JPanel p32 = new JPanel();
        p32.add(new JButton("4"));
        p32.add(new JButton("5"));
        p32.add(new JButton("6"));
        p32.add(new JButton("-"));

        JPanel p33 = new JPanel();
        p33.add(new JButton("1"));
        p33.add(new JButton("2"));
        p33.add(new JButton("3"));
        p33.add(new JButton("x"));

        JPanel p34 = new JPanel();
        p34.add(new JButton("0"));
        p34.add(new JButton("."));
        p34.add(new JButton("="));
        p34.add(new JButton("%"));

        p2.add(p21);
        p2.add(p31);
        p2.add(p32);
        p2.add(p33);
        p2.add(p34);

        add("Center",p2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,300);
        setVisible(true);

    }

    public static void main(String[] args) {
        new CalculatorTest();
    }
}
