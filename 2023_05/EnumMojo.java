package ch05;

// TODO: 2023-05-22 개발자 김경돈
public class EnumMojo {
    public static void main(String[] args) {
        Gender gender = Gender.XMALE;
        if(gender==Gender.MALE)
            System.out.println(Gender.MALE+"은 병역 의무가 있다");
        else if (gender == Gender.FEMALE) {
            System.out.println(Gender.FEMALE+"은 병역 의무가 곧 생길수도 있다");
        }
        else
            System.out.println(Gender.XMALE+"은... 난감하다");

        for(Gender g : Gender.values())
            System.out.println(g.name());
        System.out.println(Gender.valueOf("FEMALE"));

    }
}
enum Gender { MALE("남성"), FEMALE("여성"), XMALE("중성");
    private String s;

    Gender(String s){
        this.s = s;
    }
    public String toString(){
        return s;
    }


}
enum Direction { EAST, WEST, SOUTH, NORTH }
