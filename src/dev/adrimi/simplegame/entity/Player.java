package dev.adrimi.simplegame.entity;

import dev.adrimi.simplegame.Handler;
import dev.adrimi.simplegame.gfx.Animation;
import dev.adrimi.simplegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    // Animations
    private Animation aDown, aUp, aLeft, aRight, hold;
    private int AnimationSpeed;

    public Player(Handler handler, float xPos, float yPos, int width, int height) {
        super(handler, xPos, yPos, width, height);

        // Krawędzie gracza (ukryte)
        bounds.x = 0;           // 9
        bounds.y = 0;           // 40
        bounds.width = 63;      // 18
        bounds.height= 63;      // 20

        // Animacje
        AnimationSpeed = 300;
        aUp = new Animation(AnimationSpeed, Assets.player_up);
        aDown = new Animation(AnimationSpeed, Assets.player_down);
        aLeft = new Animation(AnimationSpeed, Assets.player_left);
        aRight = new Animation(AnimationSpeed, Assets.player_right);
        hold = new Animation(AnimationSpeed, Assets.player_hold);
    }

    @Override
    public void tick() {
        // Animacje
        aUp.tick();
        aDown.tick();
        aRight.tick();
        aLeft.tick();
        hold.tick();
        // Poruszanie się
        getInput();
        move();
        //handler.getGameCamera().centerOnEntity(this); // -- chcemy wyśrodkować mape a nie gracza
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;
        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (xPos - handler.getGameCamera().getxOffset() + 15),
                (int) (yPos - handler.getGameCamera().getyOffset() + 5), width, height,null);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0) {             // idziemy w lewo
            return aLeft.getCurrentFrame();
        } else if (xMove > 0) {     // idziemy w prawo
            return aRight.getCurrentFrame();
        } else if (yMove < 0) {     // idziemy w góre
            return  aUp.getCurrentFrame();
        } else if (yMove > 0) {     // idziemy w dół
            return aDown.getCurrentFrame();
        } else {
            return hold.getCurrentFrame();
        }
    }

}
