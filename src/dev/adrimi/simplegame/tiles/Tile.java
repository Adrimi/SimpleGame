package dev.adrimi.simplegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIKI

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0); // trawa będzie oznaczona jako 0
    public static Tile ditTile   = new DirtTile(1);
    public static Tile sandTile  = new SandTile(2);
    public static Tile wallTile  = new WallTile(3);


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
