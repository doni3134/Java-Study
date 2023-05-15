package ch0203;

import java.util.Scanner;

public class ch02p80 {
    public static void main(String[] args) {

//        char c = 'C';
//
//        Scanner in = new Scanner(System.in);
//        char c = in.nextLine();
//        System.out.println("");
//
        double F;

        double C = 5/9 * F - 32;

        Scanner in = new Scanner(System.in);
        System.out.println("화씨 온도를 입력하세요 :");
        double F = in.nextDouble();
        System.out.println("섭씨 온도로 변환 하였습니다 : %d도",C );




    }

}
