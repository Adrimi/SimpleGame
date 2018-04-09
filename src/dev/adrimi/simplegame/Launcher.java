package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("SIMPLE GAME!", 800, 600);
        game.start();
    }
}