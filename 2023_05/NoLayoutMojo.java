package ch15;
import java.awt.*;
import javax.swing.*;
public class NoLayoutMojo extends JFrame {
    NoLayoutMojo(){
        setTitle("절대 위치로 배치!");

        JPanel p = new JPanel();
        p.setLayout(null);

        JButton b1 = new JButton("B 1");
        b1.setBounds(10,10,60,30);

        JButton b2 = new JButton("버튼 2");
        b2.setBounds(80,20,80,25);

        JButton b3 = new JButton("button 3");
        b3.setBounds(350,30,100,30);

        p.add(b1);
        p.add(b2);
        p.add(b3);
        add(p);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NoLayoutMojo();
    }
}
