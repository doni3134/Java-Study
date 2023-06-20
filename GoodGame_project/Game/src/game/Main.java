package game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Game5());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 }
