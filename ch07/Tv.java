package ch07;

// TODO: 2023-05-23 개발자 김경돈

public class Tv implements Controllable{
    @Override
    public void turnOn(){
        System.out.println("TV를 켠다");
    }
    @Override
    public void turnOff(){
        System.out.println("TV를 끈다");
    }
}