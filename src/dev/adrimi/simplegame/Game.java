package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;
import dev.adrimi.simplegame.gfx.Assets;
import dev.adrimi.simplegame.gfx.ImageLoader;
import dev.adrimi.simplegame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display display;
    public String title;
    public int width, height;

    private boolean running = false;        // pozwala na używanie GameLoop
    private Thread thread;                  // wątkowanie, na tym polega pr. zd.

    private BufferStrategy bs;      // mówi komputerowi, JAK rysować rzeczy, używa buffery, zapobiega Błyskom!!
    private Graphics g;

    public Game(String title, int width, int heigth) {
        this.width = width;
        this.height = heigth;
        this.title = title;
    }

    private void init() {       // coś tam ustawia, tylko raz na początku
        display = new Display(title, width, height);
        Assets.init();
    }

    private void tick() {

    }

    private void render() {                             // render ciągle jest odpalane, więc tutaj
        bs = display.getCanvas().getBufferStrategy();   // rysujemy co jest potrzebne do prawidłowego działania gry
        if(bs == null) {                                // jeśli gra nie ma bufferów, to go tworzymy
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();       // to jest taki nasz pędzel do rysowania

        // Czyszczenie ekranu

        g.clearRect(0,0, width, height);

        // Początek rysowania

        g.drawImage(Assets.wall_black, 0, 64, null);
        g.drawImage(Assets.wall_black, 0, 128, null);
        g.drawImage(Assets.wall_black, 0, 192, null);
        g.drawImage(Assets.wall_black, 0, 256, null);
        g.drawImage(Assets.wall_black, 64, 256, null);
        g.drawImage(Assets.wall_black, 128, 256, null);
        g.drawImage(Assets.wall_black, 192, 256, null);
        g.drawImage(Assets.wall_black, 256, 256, null);
        g.drawImage(Assets.wall_black, 256, 192, null);
        g.drawImage(Assets.wall_black, 256, 128, null);
        g.drawImage(Assets.wall_black, 256, 64, null);
        g.drawImage(Assets.wall_black, 256, 0, null);
        g.drawImage(Assets.wall_black, 192, 0, null);
        g.drawImage(Assets.wall_black, 128, 0, null);
        g.drawImage(Assets.wall_black, 64, 0, null);
        g.drawImage(Assets.wall_black, 0, 0, null);

        g.drawImage(Assets.groundGravel_Grass, 64, 64, null);
        g.drawImage(Assets.groundGravel_Grass, 64, 128, null);
        g.drawImage(Assets.groundGravel_Grass, 64, 192, null);
        g.drawImage(Assets.groundGravel_Grass, 128, 64, null);
        g.drawImage(Assets.groundGravel_Grass, 128, 128, null);
        g.drawImage(Assets.groundGravel_Grass, 128, 192, null);
        g.drawImage(Assets.groundGravel_Grass, 192, 64, null);
        g.drawImage(Assets.groundGravel_Grass, 192, 128, null);
        g.drawImage(Assets.groundGravel_Grass, 192, 192, null);


        g.drawImage(Assets.character4, 64+(Assets.character4.getWidth()/2), 64+(64-Assets.character4.getHeight()), null);

        // Koniec rysowania

        bs.show();
        g.dispose();            // te linijki są zabezpieczeniem przed awarią
    }

    public void run() {
        init();
        while (running) {
            tick();
            render();
        }
        stop();
    }

    public synchronized void start() {
        if (running) {                      // być może gra już działa, sprawdzdamy
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();                     // to odpala metode run()
    }

    public synchronized void stop() {
        if (!running) {                      // być może gra już NIE działa!!!
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}