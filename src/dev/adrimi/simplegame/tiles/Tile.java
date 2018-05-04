package dev.adrimi.simplegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Obiekty bloków statycznych, czyli ścian, podłoża, przeszkód, itp.
 * Tworzymy sobie pewien Tile, następnie dopisujemy do niego atrybut na stałe, za co będzie odpowiadał i dodajemy do
 * niego grafikę.
 */
public class Tile {

    // STATIKI

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0); // trawa będzie oznaczona jako 0
    public static Tile ditTile   = new DirtTile(1);  // ziemia będzie jako 1
    public static Tile sandTile  = new SandTile(2);  // piasek jako 2
    public static Tile wallTile  = new WallTile(3);  // NIEPRZENIKALNA ściana jako 3


    // CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;  // bo dziedziczenie
    protected final int id;


    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
