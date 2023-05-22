package ch17;

import javax.swing.*;
import java.awt.*;

public class StringMojo extends JFrame {
    class Mypanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            g.drawString("문자열을 그려 보자!",20,20);
            g.drawString("나도 ! 문자열을 그려 보자!",50,50);


        }
    }

    StringMojo(){
        setTitle("문자열 그리기");

        add(new Mypanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StringMojo();
    }
}
