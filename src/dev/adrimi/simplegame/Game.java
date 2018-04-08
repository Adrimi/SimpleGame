package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;
import dev.adrimi.simplegame.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;

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

        g.fillRect(0,0, width, height);
        g.setColor(Color.gray);
        g.fillRect(0,0,width,25);

        // Koniec rysowania

        bs.show();
        g.dispose();            // bez tych dwóch linijek program się sypie - nwm czemu
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