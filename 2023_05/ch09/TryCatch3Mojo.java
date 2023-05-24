package ch09;

// TODO: 2023-05-24 개발자 김경돈
public class TryCatch3Mojo {
    public static void main(String[] args) {

        //구성편집 -> 프로그램 인수 -> 다양한 인수 입력하면서 실행

        int dividend = 10;
        try {
            int divisor = Integer.parseInt(args[0]);
            System.out.println(dividend / divisor);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("원소가 존재하지 않습니다");
        }catch (NumberFormatException e){
            System.out.println("숫자가 아닙니다");
        }catch (ArithmeticException e){
            System.out.println("0으로 나눌수 없습니다");
        }finally {
            System.out.println("항상 실행");
        }
        System.out.println("종료");
    }
}
