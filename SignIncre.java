package ch0203;

public class SignIncre {
    public static void main(String[] args) {
        int plusOne =1;
        int minusOne = -plusOne;

        System.out.println("plusOne은 "+plusOne+"입니다");
        System.out.println("minusOne은 "+minusOne+"입니다");

//        int x = 1, y =1;
//        System.out.println("x ="+x+",++x ="+ ++x);
//        System.out.println("y ="+y+",y++ ="+ y++);
//        System.out.println("x ="+x+",y ="+ y)
//        ++x = 연산전 x값 증가 , x++ = 연산후 x값 증가 중요!

        int x =1;
        int y;
        y = (x==1) ? 10:20;
        System.out.println(y);
        y = (x > 1) ? x++ : x+20;
        System.out.println(x);
        System.out.println(y);



    }


}
