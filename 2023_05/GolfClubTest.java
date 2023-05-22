package ch04;



class GolfClub {
    String s;
    int n;

    GolfClub(int n ) {
        this.s = "아이언";
        this.n = n;
    }

    GolfClub(String s) {
        this.s = "s";
        this.n = 0;
    }
    GolfClub(){
        this.s ="아이언";
        this.n = 7;
    }

    void print() {
        if (n == 0) {
            System.out.printf("%s입니다", s);
        } else
            System.out.printf("%d번 아이언입니다.",n);

    }
}

// TODO: 2023-05-22 개발자 김경돈
public class GolfClubTest {
    public static void main(String[] args) {
        GolfClub g1 = new GolfClub();
        g1.print();

        GolfClub g2 = new GolfClub(8);
        g2.print();

        GolfClub g3 = new GolfClub("퍼터");
        g3.print();



    }

}
