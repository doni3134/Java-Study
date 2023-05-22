package ch0203;

import java.util.Scanner;

public class ch02test {
    public static void main(String[] args) {
        double w, h, area;
        Scanner in = new Scanner(System.in);

        System.out.print("직사각형의 가로 길이를 입력하세요");
        w = in.nextDouble();
        System.out.print("직사각형의 세로 길이를 입력하세요");
        h = in.nextDouble();
        area = w * h;
        System.out.println("직사각형의 넓이는 "+area+"입니다.");

    }
}
class ch02test01{
    public static void main(String[] args) {
//        int x;
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("정수를 입력하세요 :");
//        x = in.nextInt();
//
//        if (x%2 == 0 )    // x/2 와 햇갈리면 안됨! x/2는 x를 2로 나눈 값임(음수포함이라 안됨) %는 나머지 값!
//            System.out.println("짝수입니다");
//
//        else
//            System.out.println("홀수입니다");
        int x = 1, y = 2;
        System.out.println(x++);
        System.out.println(++x + y--);
        System.out.println(++x / 3 + x * ++y);

    }
}
