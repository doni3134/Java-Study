package ch08;

// TODO: 2023-05-23 개발자 김경돈

import java.util.StringTokenizer;
public class StringTokenizerMojo {
    public static void main(String[] args) {
        String s = "of the people ......, for the people ";

        StringTokenizer st = new StringTokenizer(s, " ,");

        System.out.println(st.countTokens());

        while (st.hasMoreTokens()){
            System.out.print("["+ st.nextToken() + "] ");
        }
    }
}
