package ch04;

// TODO: 2023-05-22 개발자 김경돈
public class LocalVariableMojo {
    public static void main(String[] args) {
        int a = 0;
        double b = 0;

        System.out.println(b);
        int c = 0;
        System.out.println(a+c);

        c = 0;

//        double d = 0.0;

        for (int e = 0; e < 10; e++) {
//            a = 1;
            System.out.println(e);
//            System.out.println(a+b+c);


        }
        //cast 연습
        short x = 1;
        a = (int) x;
        System.out.println("a를 x로 형변환  a ="+ x);

    }
}
