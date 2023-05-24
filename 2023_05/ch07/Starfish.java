package ch07;

// TODO: 2023-05-23 개발자 김경돈

class Starfish extends Shape {
    int radius1;
    int radius2;
    int radius3;


    public Starfish(int radius1, int radius2, int radius3){
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.radius3 = radius3;
    }
    public void draw(){
        System.out.println("불가사리 그림");
    }
    public double findArea(){
        return pi*((radius1*radius2)- radius3);   // 대충 아무 공식이나 넣음
    }


    public static void main(String[] args) {
        Starfish s = new Starfish(3, 2, 3);
        s.draw();
        System.out.printf("불가사리의 넓이는 %.1f\n", s.findArea());
    }


}


