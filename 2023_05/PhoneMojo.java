package ch04;

// TODO: 2023-05-22 개발자 김경돈
class Phone{
    String model;
    int value;

    void print(){
        System.out.println(value+ "원" +model);
    }
}

public class PhoneMojo {
    public static void main(String[] args) {
        Phone myphone = new Phone();
        myphone.model = "Gal s9";
        myphone.value = 100;
        myphone.print();

        Phone yourPhone = new Phone();
        yourPhone.model = "Gal s10";
        yourPhone.value = 10;
        yourPhone.print();
    }
}
