package dev.adrimi.simplegame.entity;

import dev.adrimi.simplegame.Game;
import dev.adrimi.simplegame.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    private int timer, touch = 15;

    public Player(Game game, float xPos, float yPos, int width, int height) {
        super(game, xPos, yPos, width, height);
    }

    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(game.getKeyManager().up)
            yMove = -speed;
        if(game.getKeyManager().down)
            yMove = speed;
        if(game.getKeyManager().left)
            xMove = -speed;
        if(game.getKeyManager().right)
            xMove = speed;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.character4, (int) (xPos - game.getGameCamera().getxOffset()), (int) (yPos - game.getGameCamera().getyOffset()), width, height,null);
    }


}
