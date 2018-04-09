package dev.adrimi.simplegame.states;

import dev.adrimi.simplegame.Game;
import dev.adrimi.simplegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;

    public GameState() {
        world = new World("res/worlds/world1.txt");
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
