package ch09;

// TODO: 2023-05-24 개발자 김경돈


import java.util.StringTokenizer;

public class UnChecked1Mojo {
    public static void main(String[] args) {

        String s = "Time is Money";
        StringTokenizer st = new StringTokenizer(s);

        while (st.hasMoreTokens()){
            System.out.print(st.nextToken() + "+");

        }
        System.out.println(st.nextToken());


        //nextToken() = 다음 토큰을 꺼내온다 String
        //countTokens() = 남아있는 토큰 개수를 반환 int
        //hasMoreTokens() = 남아있는 토큰이 있는지 여부 반환 boolean(true/false)
    }
}
