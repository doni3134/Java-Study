package ch09;

public class TryCatch2Mojo {
    public static void main(String[] args) {

        int[]array={0,1,2,3};
        try{
            System.out.println("2번째 원소 => " + array[1]);
            System.out.println("10번째 원소 => " + array[9]);
            System.out.println("1번째 원소 => " + array[0]); // 이전 실행문에서 예외가 발생했으므로 이 실행문은 실행하지 않는다.

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("배열이 없습니다");
        }
        System.out.println("!!!!");

    }
}
