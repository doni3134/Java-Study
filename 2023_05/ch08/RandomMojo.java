package ch08;

// TODO: 2023-05-23 개발자 김경돈

import java.util.Random;
public class RandomMojo {
    public static void main(String[] args) {
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            System.out.print(r.nextInt(100)+ " ");
        }

    }
}
