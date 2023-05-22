package ch04;

// TODO: 2023-05-22 개발자 김경돈

class Circle4 {       //ch04 안에 Circle 클래스가 많이 있음.. 1, 2, 3 ... 으로 해줘야됨
    double radius;

    public Circle4(double radius){
        this.radius = radius;
    }
    public double getRadius(){
        return radius;
    }
    double findArea2(){
        return 3.14*radius*radius;
    }
}
public class CircleArrayMojo {

    public static void main(String[] args) {
        Circle4[] circles = new Circle4[5];

        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle4(i+1.0);
            System.out.printf("원의 넓이(반지름 : %.1f) = %.2f\n",
                    circles[i].radius, circles[i].findArea2());


        }
    }

}
