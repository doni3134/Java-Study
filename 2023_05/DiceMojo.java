package ch04;

// TODO: 2023-05-22 개발자 김경돈

class Dice{
    int face;
    int roll(){
        return (int)(Math.random()*6)+1;
    }
}
public class DiceMojo {
    public static void main(String[] args) {
        Dice d = new Dice();
        System.out.println("주사위의 숫자 : " +d.roll());
    }
}
