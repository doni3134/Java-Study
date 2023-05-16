package ch0203;
// TODO: 2023-05-16 개발자 김경돈 
import java.util.Scanner;

public class ReturnDemo {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("숫자를 입력하세요");
        int x = in.nextInt();

        System.out.print("숫자를 입력하세요");
        int y = in.nextInt();

        int printGauss= 0;

        for (int i = x; i<=y ; i++) {
            printGauss += i;
        }
        System.out.printf("%d ~ %d 까지 더한 값은 %d 입니다", x ,y, printGauss);

//        printScore(99);
//        printScore(120);
    }
//        if (score<0 || score >100){
//            System.out.println("잘못된 점수 : "+ score);
//            return;
//        }
//        System.out.println("점수 : " +score);
}

