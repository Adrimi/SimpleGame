package dev.adrimi.simplegame.entity;

import dev.adrimi.simplegame.Game;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed, xMove, yMove;

    public Creature(Game game, float xPos, float yPos, int width, int height) {
        super(game, xPos, yPos, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public abstract void tick();

    public void move() {
        xPos += xMove;
        yPos += yMove;
    }

    public abstract void render(Graphics g);
}
