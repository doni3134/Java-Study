package ch0203;

public class SwitchDemo {
    public static void main(String[] args) {

        whoIsit("호랑이");
        whoIsit("참새");
        whoIsit("고등어");
        whoIsit("곰팡이");
    }

    static void whoIsit(String bio){

        String kind ="";
//        int number = 1;
//        switch (number){
//            case 3:
//                System.out.print("*");
//            case 2:
//                System.out.print("*");
//            case 1:
//                System.out.print("*");
//            case 0:
//                System.out.print("*");
//            case -1:
//                System.out.println("*");
//        }
        switch (bio){                // [ alt + / ] 는 앞줄에 있는 키워드를 복사 붙여넣기 함.
            case "호랑이":
            case "사자":
                kind = "포유류";
                break;
            case "독수리":
            case "참새":
                kind = "조류";
                break;
            case "고등어":
            case "연어":
                kind = "어류";
                break;
            default:
                System.out.print("어이쿠 ! " );
                kind ="...";
        }
        System.out.printf("%s는 %s이다.\n",bio ,kind);
    }
}
