package ch05;

// TODO: 2023-05-22 개발자 김경돈
public class String02Mojo {
    public static void main(String[] args) {
        String s1 = new String("Hi,");
        String s2 = new String(" Java");

        System.out.println("문자열 길이(s1) : "+s1.length());
        System.out.println(s1.charAt(1));

        s1=s1.concat(s2);

        System.out.println(s1.concat(s2)+"!");
        System.out.println(s1.toLowerCase()+"!");
        System.out.println(s1.substring(4,8)+"!");

        String s3 = "*-*";
        System.out.println(s3.repeat(10));
        System.out.println(s2.trim().indexOf("v"));



    }
}
