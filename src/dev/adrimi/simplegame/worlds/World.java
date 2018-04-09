package dev.adrimi.simplegame.worlds;

import dev.adrimi.simplegame.tiles.Tile;
import dev.adrimi.simplegame.utils.Utils;

import java.awt.*;

public class World {

    private int width, height, spawnX, spawnY;
    private int[][] tiles;

    public World(String path) {
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT); // musimy przekonwertować z jednostki obiektu na piksele
            }
        }
    }

    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.sandTile;
        }
        return t;
    }

    // tu będzie ładowanie świata z pliku
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);        // rozmiar świata
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);       // miejsce spawnu gracza
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
            }
        }
    }

}
