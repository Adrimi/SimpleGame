package dev.adrimi.simplegame.worlds;

import dev.adrimi.simplegame.Handler;
import dev.adrimi.simplegame.tiles.Tile;
import dev.adrimi.simplegame.utils.Utils;

import java.awt.*;

/**
 * Odpowiada za generowanie świata, przetwarza dane uzyskane z Utils i dopasowuje odpowiednie obiekty planszy gry.
 */
public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }

    public void tick() {
        handler.getGameCamera().centerOnMap(this);
    }

    public void render(Graphics g) {

        // ograniczamy renderowanie JEDYNIE do klocków, które są obecnie widoczne na oknie. Aby sprawdzić działanie,
        // można dodać/odjąć  1
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int xEnd =   (int) Math.min(width,  (handler.getGameCamera().getxOffset() + handler.getWidth())  / Tile.TILEWIDTH + 1);
        int yEnd =   (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);                                                                         //

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset())); // musimy przekonwertować z
                                                                                        // jednostki obiektu na piksele
            }
        }
    }

    public Tile getTile(int x, int y) {
        // upewniamy się, że gracz nie wykroczy po za mape!
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;


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

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getSpawnX() {
        return spawnX;
    }
    public int getSpawnY() {
        return spawnY;
    }
}
