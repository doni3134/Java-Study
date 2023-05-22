package ch0203;

// TODO: 2023-05-16 개발자 김경돈
public class ContinueDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i%2 == 1) // 1일땐짝수 0일땐 홀수
                continue; //홀수일경우  for문의 증감식 i++로 이동한다
            System.out.println( i );

        }
    }
}

class ContinueDemo2{
    public static void main(String[] args) {
//        char c ='a';
//        while (c <= 'z')
//            System.out.print(c++);

        for (int i = 1; i < 3; ++i) {
            System.out.print(i);

        }
//        System.out.println(i);

    }

}
