package ch0203;

public class IncrementDemo {
    public static void main(String[] args) {

        // x 값은 main 에서 0으로 변하지 않는다.
        //하지만 increment 에서 n++이 있기 때문에 0에서 1로 변함.

        int x = 0;
        System.out.println("increment 메서드를 호출하기 전의 x는 " + x);
        increment(x);
        System.out.println("increment 메서드를 호출한 후의 x는 " + x);
    }

    public static void increment(int n){
        System.out.println("increment 메서드를 시작할 때의 n은 " + n);
        n++;
        System.out.println("increment 메서드가 끝날 때의 n은 " + n);
    }
}
