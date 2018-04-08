package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;

public class Game implements Runnable{

    private Display display;
    public String title;
    public int width, height;

    private boolean running = false;
    private Thread thread;

    public Game(String title, int width, int heigth) {
        this.width = width;
        this.height = heigth;
        this.title = title;
    }

    private void init() {       // coś tam ustawia, tylko raz na początku

    }

    private void tick() {

    }

    private void render() {

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
        if (running) {
            return;                         // być może gra już działa!!!
        }
        running = true;
        thread = new Thread(this);
        thread.start();                     // to odpala metode run()
    }

    public synchronized void stop() {
        if (!running) {
            return;                         // być może gra już NIE działa!!!
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
