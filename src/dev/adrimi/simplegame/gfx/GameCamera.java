package dev.adrimi.simplegame.gfx;

import dev.adrimi.simplegame.Game;
import dev.adrimi.simplegame.Handler;
import dev.adrimi.simplegame.entity.Entity;
import dev.adrimi.simplegame.worlds.World;

public class GameCamera {

    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getxPos() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getyPos() - handler.getHeight()/ 2 + e.getHeight()/ 2;
    }

    public void centerOnMap(World w) {
        xOffset = w.getWidth() *64/2 - handler.getWidth() /2;
        yOffset = w.getHeight()*64/2 - handler.getHeight()/2;
    }
                //xAmount
    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
