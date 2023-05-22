package ch15;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 2023-05-17 개발자 김경돈
public class HelloEventMojo extends JFrame {
    HelloEventMojo(){
        setTitle("이벤트 맛보기");

        ActionListener l = e -> System.out.println("버튼을 클릭했습니다.");
        JButton b = new JButton("클릭");
        b.addActionListener(l);

        add(b);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HelloEventMojo();
    }
}
