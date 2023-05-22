package ch0203;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int x = in.nextInt();
//        int y = in.nextInt();
        String x = in.nextLine();
        String y = in.nextLine();

        System.out.printf("%s\n%s\n%s 입니다.\n", x, y, x+y);

        int remainder = 25%2;
        System.out.println("25 % 2 의 나머지는 "+ remainder +"입니다");

        int value =1;
        value += 1;
        System.out.println("값 = " + value);
        value -= 1;
        System.out.println("값 = " + value);
        value <<=3;
        System.out.println("값 = " + value);
        value %=3;
        System.out.println("값 = " + value);


    }
}

class BitOperator {
    public static void main(String[] args) {
        System.out.printf("%x\n", 0b0101 & 0b0011);
        System.out.printf("%x\n", 0b0101 | 0b0011);
        System.out.printf("%x\n", 0b0101 ^ 0b0011);
        System.out.printf("%x\n", 0b0110 >> 2);
        System.out.printf("%x\n", 0b0110 << 2);

        int i1 = -10;
        int i2 = i1 >>1;
        int i3 = i1 >>>1;

        System.out.printf("%x -> %d\n", i1, i1);
        System.out.printf("%x -> %d\n", i2, i2);
        System.out.printf("%x -> %d\n", i3, i3);
    }
}


class Assign {
    public static void main(String[] args) {
        int value =1;
        value += 1;
        System.out.println("값 = " + value);
        value -= 1;
        System.out.println("값 = " + value);
        value <<=3;
        System.out.println("값 = " + value);
        value %=3;
        System.out.println("값 = " + value);
    }
}


