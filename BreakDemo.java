package ch0203;

// TODO: 2023-05-16 개발자 김경돈
public class BreakDemo {
    public static void main(String[] args) {
        int i = 1, j = 5;

        while(true){
            System.out.println(i++);

            if (i >= j)
                break;
        }
    }
}
