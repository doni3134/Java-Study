package ch09;

// TODO: 2023-05-24 개발자 김경돈


public class TryCatch1Mojo {
    public static void main(String[] args) {

        int[] array = {1, 2, 3};
        try{
            System.out.println("last => " + array[3]);
            System.out.println("1st => " + array[0]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not Exist");
        }
        System.out.println("no !!");
    }



}
