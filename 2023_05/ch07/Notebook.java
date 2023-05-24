package ch07;

// TODO: 2023-05-23 개발자 김경돈

interface Portable{
    void inMyBag();
}
public class Notebook extends Computer implements Portable {
    public void inMyBag() {
        System.out.println("노트북은 가방에 있다.");
    }
    public void turnOn(){
        System.out.println("노트북을 켠다");
    }
    public void turnOff(){
        System.out.println("노트북을 끈다");
    }

    public static void main(String[] args) {
        Notebook n = new Notebook();
        n.turnOn();
        n.turnOff();
        n.inMyBag();
    }
}
