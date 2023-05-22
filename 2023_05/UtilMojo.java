package ch04;

class Util {
    static int forTimes(int i){
        return i * 4;

    }
}
public class UtilMojo{
    public static void main(String[] args) {
        System.out.println(Util.forTimes(5));
    }
}