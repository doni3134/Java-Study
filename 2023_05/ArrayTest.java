package ch05;

public class ArrayTest {
    public static void main(String[] args) {
        int[][] array = {{1,2}, {1}, {3,4,5}};
        for(int[]i : array)
            System.out.print(i.length);
    }
}
