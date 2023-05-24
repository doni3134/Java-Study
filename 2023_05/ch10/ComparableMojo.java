package ch10;

import java.util.ArrayList;
import java.util.Arrays;

class Rectangle implements Comparable<Rectangle>{
    // TODO: 2023-05-24 this

    private int width, height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }

    // TODO: 2023-05-24 that

    public int findArea(){
        return width * height;
    }
    public String toString(){
        return String.format("사각형[가로=%d, 세로=%d]", width, height);
    }

    // TODO: 2023-05-24 what


    public int compareTo(Rectangle o) {
        return findArea() - o.findArea();
    }
}
public class ComparableMojo{
    public static void main(String[] args) {
        Rectangle[] rectangles = {new Rectangle(3,5), new Rectangle(2,10), new Rectangle(5,5)};

        Arrays.sort(rectangles);

        for (Rectangle r : rectangles)
            System.out.println(r);
    }
}
