package ch17;

import javax.swing.*;
import java.awt.*;

public class RectMojo extends JFrame {
    RectMojo(){
        setTitle("다양한 사각형 그리기");

        add(new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.RED);
                g.drawRect(30,10,50,50);
                g.fillRoundRect(120,10,50,50,30,20);
                g.draw3DRect(210,10,50,50,false);
                g.draw3DRect(300,10,50,50,true);

                g.setColor(Color.GREEN);
                g.fillRect(30,80,50,50);
                g.fillRoundRect(120,80,50,50,30,20);
                g.fill3DRect(210,80,50,50,false);
                g.fill3DRect(300,80,50,50,true);

            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RectMojo();
    }
}
