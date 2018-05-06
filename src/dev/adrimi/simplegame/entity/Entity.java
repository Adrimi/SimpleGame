package dev.adrimi.simplegame.entity;

import dev.adrimi.simplegame.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float xPos, yPos;
    protected int width, height;
    protected Rectangle bounds;   // system kolizji, "ciało" gracza

    public Entity(Handler handler, float xPos, float yPos, int width, int height) {
        this.handler = handler;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
