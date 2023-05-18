package ch17;

import java.awt.*;

public class FontMojo {
    public static void main(String[] args) {
        GraphicsEnvironment e =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontNames = e.getAvailableFontFamilyNames();
        for (String s: fontNames)
            System.out.println(s);
    }
}
