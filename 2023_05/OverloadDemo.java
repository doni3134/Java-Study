package ch0203;

public class OverloadDemo {
    public static void main(String[] args) {
        int i1 =3, i2= 7, i3 =10;
        double d1=7.0, d2=3.0;

        System.out.printf("max(%d, %d) =%d\n", i1, i2 ,max(i1,i2));
        System.out.printf("max(%.1f, %.1f) =%.1f\n", d1, d2 ,max(d1,d2));
        System.out.printf("max(%d, %d, %d) =%d\n", i1, i2 ,max(i1,i2,i3));
    }
}
