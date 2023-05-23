package ch07;

// TODO: 2023-05-23 개발자 김경돈
public class ControllableMojo {
    public static void main(String[] args) {
        Tv tv = new Tv();
        Computer com = new Computer();
        Notebook nb = new Notebook();

        tv.turnOn();
        tv.turnOff();
        tv.repair();

        com.turnOn();
        com.turnOff();
        com.repair();

        nb.inMyBag();
        nb.turnOn();
        nb.turnOff();

        Controllable.reset();


    }
}
