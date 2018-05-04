package dev.adrimi.simplegame.states;

import dev.adrimi.simplegame.Game;
import dev.adrimi.simplegame.entity.Player;
import dev.adrimi.simplegame.worlds.World;

import java.awt.*;

/**
 * Odpowiada za obsługę stanów gry, czyli MENU, GRA, TABELA itp.
 */
public class GameState extends State{

    private World world;
    private Player player;

    public GameState(Game game) {
        super(game);
        world = new World(game, "res/worlds/world1.txt");
        player = new Player(game, world.getSpawnX()*64 + 16,
                world.getSpawnY()*64, 37, 59);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

}
