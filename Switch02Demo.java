package ch0203;
// TODO: 2023-05-16 개발자 김경돈

public class Switch02Demo {
    public static void main(String[] args) {
        whoIsIt("호랑이");
        whoIsIt("참새");
        whoIsIt("고등어");
        whoIsIt("곰팡이");
    }

    static void whoIsIt(String bio){
//        String kind = "...";
//        switch (bio){                // [ alt + / ] 는 앞줄에 있는 키워드를 복사 붙여넣기 함.
//            case "호랑이", "사자" -> kind ="포유류";
//            case "독수리", "참새" -> kind ="조류";
//            case "고등어", "연어" -> kind ="어류";
//            default -> System.out.print("어이쿠 ! ");
//
//        }
//        System.out.printf("%s는 %s이다.\n",bio ,kind);

        String kind = switch (bio){
            case "호랑이", "사자" -> kind ="포유류";
            case "독수리", "참새" -> kind ="조류";
            case "고등어", "연어" -> kind ="어류";
            default -> {
                System.out.print("어이쿠 ! ");
                yield "...";
            }
        };
        System.out.printf("%s는 %s이다.\n",bio ,kind);
    }
}
