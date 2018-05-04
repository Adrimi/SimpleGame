package dev.adrimi.simplegame.tiles;

import dev.adrimi.simplegame.gfx.Assets;

/**
 * Obiekt ściany
 */
public class WallTile extends Tile{

    public WallTile(int id) {
        super(Assets.wall_black, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
