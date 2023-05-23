package ch08;

// TODO: 2023-05-23 개발자 김경돈
import java.text.DecimalFormat;
public class DecimalFormatMojo {
    public static void main(String[] args) {
        DecimalFormat f1 = new DecimalFormat("#");
        DecimalFormat f2 = new DecimalFormat("00000000.00");
        DecimalFormat f3 = new DecimalFormat("#.000");
        DecimalFormat f4 = new DecimalFormat("#,###,##");
        DecimalFormat f5 = new DecimalFormat("-#.#");
        DecimalFormat f6 = new DecimalFormat("#.##E00");
        DecimalFormat f7 = new DecimalFormat("+#,#;-#.#");
        DecimalFormat f8 = new DecimalFormat("#.00%");

        System.out.println(f1.format(1234567.890));
        System.out.println(f2.format(1234567.890));
        System.out.println(f3.format(1234567.890));
        System.out.println(f4.format(1234567.890));
        System.out.println(f5.format(1234567.890));
        System.out.println(f6.format(1234567.890));
        System.out.println(f7.format(1234567.890));
        System.out.println(f8.format(1234567.890));



    }
}
