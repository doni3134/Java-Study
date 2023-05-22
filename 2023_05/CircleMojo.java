package ch04;

// TODO: 2023-05-22 개발자 김경돈
class Circle{
//
//    private double radius;
//    public double getRadius(){
//        return radius;
//    }
//    public void setRadius(double r) {
//        this.radius = r;
//    }
//    double findArea(){
//        return 3.14 * radius * radius;
//    }
//    void show(double x, double y){
//        System.out.printf("반지름 = %.1f, 넓이 = %.1f\n", x ,y);
//
//    }
    double radius;
    String color;

    public Circle(double r, String c){
        radius = r;
        color = c;
    }

    public Circle(double r){
        radius = r;
        color = "파랑";
    }


}
public class CircleMojo {
//    public static void main(String[] args) {
//        Circle myCircle = new Circle();
//        myCircle.setRadius(10.0);
//        myCircle.show(myCircle.getRadius(),myCircle.findArea());
//    }

    public static void main(String[] args) {

        Circle c4 = new Circle(10.0 , "빨강");
        Circle c5= new Circle(10.0 );

        System.out.print(c4);
    }
}
