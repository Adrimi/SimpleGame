package dev.adrimi.simplegame.worlds;

import dev.adrimi.simplegame.Game;
import dev.adrimi.simplegame.tiles.Tile;
import dev.adrimi.simplegame.utils.Utils;

import java.awt.*;

/**
 * Odpowiada za generowanie świata, przetwarza dane uzyskane z Utils i dopasowuje odpowiednie obiekty planszy gry.
 */
public class World {

    private Game game;
    private int width;
    private int height;
    private int spawnX;
    private int spawnY;
    private int[][] tiles;

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }


    public World(Game game, String path) {
        this.game = game;
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - game.getGameCamera().getxOffset()),
                        (int) (y*Tile.TILEHEIGHT - game.getGameCamera().getyOffset())); // musimy przekonwertować z jednostki obiektu na piksele
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
        spawnX = Utils.parseInt(tokens[2]);       // miejsce spawnu gracza (jeszcze nie obsługiwane)
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
            }
        }
    }

}
