package ch04;

// TODO: 2023-05-22 개발자 김경돈
class Circle3 {
    double radius;
    static int numOfCircles =0;
    static int numCircles = 0;

    public Circle3(double radius){
        this.radius = radius;
        numOfCircles++;
        numCircles++;
    }
}
public class Circle03Mojo {
    public static void main(String[] args) {
        Circle3 myCircle = new Circle3(10.0);
        Circle3 yourCircle = new Circle3(5.0);

        System.out.println("원의 개수 : " + Circle3.numOfCircles);
        System.out.println("원의 개수 : " + Circle3.numCircles);
    }

    void print(){
        System.out.println("인스턴스 메서드 입니다.");
    }
}

