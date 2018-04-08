package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("SIMPLE GAME!", 640, 1280);
        game.start();
    }
}
