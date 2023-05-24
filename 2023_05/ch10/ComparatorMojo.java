package ch10;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorMojo {
    public static void main(String[] args) {
        String[] strings = { "로마에", "시간은금","펜은"};

        Arrays.sort(strings, Comparator.comparingInt(String::length));

        for (String s : strings)
            System.out.println(s);
    }
}
