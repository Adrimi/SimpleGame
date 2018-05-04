package dev.adrimi.simplegame;

/**
 * Created by Adrian Szymanowski on 9/04/2018.
 *  Klasa, która odpowiada za odpalenie głównego procesu gry.
 *  W przyszłości posłuży do stworzenia pliku .jar do uruchamiania gry.
 */

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Sokoban The Game", 800, 600);
        game.start();
    }
}