package ch09;

import java.util.Scanner;

// TODO: 2023-05-24 개발자 김경돈
public class ThrowsMojo {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        try {
            square(in.nextLine());
        }catch (NumberFormatException e){
            System.out.println("정수가 아닙니다");
        }
    }
    private static void square(String s) throws NumberFormatException {
        int n = Integer.parseInt(s);
        System.out.println(n*n);
    }

}
