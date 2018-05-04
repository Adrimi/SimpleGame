package dev.adrimi.simplegame.entity;

import dev.adrimi.simplegame.Game;

import java.awt.*;

public abstract class Entity {

    protected Game game;
    protected float xPos, yPos;
    protected int width, height;

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

    public Entity(Game game, float xPos, float yPos, int width, int height) {
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
