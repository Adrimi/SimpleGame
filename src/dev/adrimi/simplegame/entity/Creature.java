package dev.adrimi.simplegame.entity;


import dev.adrimi.simplegame.Handler;
import dev.adrimi.simplegame.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 2.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed, xMove, yMove;

    public Creature(Handler handler, float xPos, float yPos, int width, int height) {
        super(handler, xPos, yPos, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

        // podzielenie kodu na dwie funkcjie obsługujące dwie osie ruchu
        // pozwala na łątwiejsze zapisanie mechanizmu kolizji
    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {
        if(xMove > 0) {         // idziemy w prawo

            int tempX = (int) (xPos + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tempX, (int) (yPos + bounds.y) / Tile.TILEHEIGHT) &&
                    (!collisionWithTile(tempX, (int) (yPos + bounds.y + bounds.height) / Tile.TILEHEIGHT))) {
                xPos += xMove;
            } else {            // jeśli jest kolizja
                xPos = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        } else if(xMove < 0) {  // idziemy w lewo

            int tempX = (int) (xPos + xMove + bounds.x ) / Tile.TILEWIDTH;
            if(!collisionWithTile(tempX, (int) (yPos + bounds.y) / Tile.TILEHEIGHT) &&
                    (!collisionWithTile(tempX, (int) (yPos + bounds.y + bounds.height) / Tile.TILEHEIGHT))) {
                xPos += xMove;
            }else { // jeśli jest kolizja
                xPos = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }

        }
    }

    public void moveY() {
        if(yMove < 0) {         // idziemy w góre

            int tempY = (int) (yPos + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile(((int) (xPos + bounds.x + bounds.width) / Tile.TILEWIDTH), tempY) &&
                    (!collisionWithTile(((int) (xPos + bounds.x + bounds.width) / Tile.TILEWIDTH), tempY))) {
                yPos += yMove;
            }else {
                yPos = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        } else if(yMove > 0) {  // idziemy w dół

            int tempY = (int) (yPos + yMove  + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile(((int) (xPos + bounds.x) / Tile.TILEWIDTH), tempY) &&
                    (!collisionWithTile(((int) (xPos + bounds.x + bounds.width) / Tile.TILEWIDTH), tempY))){
                yPos += yMove;
            } else {
                yPos = tempY * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
