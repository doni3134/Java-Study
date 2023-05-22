package ch0203;
// TODO: 2023-05-16 개발자 김경돈

public class While2Demo {
    public static void main(String[] args) {

        int row = 2;
        while (row <20){
            System.out.println(row+" 단입니다 ");
            int colum = 1;
            while (colum<10){
                System.out.println(row +" X "+ colum + " = " + (row * colum ));
                colum++;
            }
            System.out.println();
            row++;
        }
    }
}

class While3Demo {
    public static void main(String[] args) {


        int row = 2;
        do {
            System.out.println(row + " 단입니다 ");
            int colum = 1;
            do {
                System.out.print(row + " X " + colum + " = " + (row * colum)+"\t");   // \t 를 넣어주면 가독성이 좋아짐
                colum++;
            } while (colum < 10);
            System.out.println();
            row++;
        } while (row <20);

    }
}