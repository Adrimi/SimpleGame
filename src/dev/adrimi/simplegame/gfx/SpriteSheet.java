package dev.adrimi.simplegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Odpowiada za poprawny podział i podział sam w sobie pliku z grafiką. Przyjmuje
 * Dane, które otrzyma, nie posiada sztywnych stałych, więc wmożemy w istocie wykorzystać tę klasę
 * do wycinania dowolnych obiektów w rekurencji.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
