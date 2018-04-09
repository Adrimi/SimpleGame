package dev.adrimi.simplegame;

import dev.adrimi.simplegame.display.Display;
import dev.adrimi.simplegame.gfx.Assets;
import dev.adrimi.simplegame.states.GameState;
import dev.adrimi.simplegame.states.State;


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

    // STANY
    private State gameState; // Inicjalizacja obiektu ABSTRAKCYJNEGO, ale nie powstanie, to ważne!


    public Game(String title, int width, int heigth) {
        this.width = width;
        this.height = heigth;
        this.title = title;
    }

    private void init() {       // coś tam ustawia, tylko raz na początku
        display = new Display(title, width, height);
        Assets.init();

        gameState = new GameState(); // tutaj powstaje obiekt który dziedziczy po abstrakcyjnej State
        State.setState(gameState);
    }

    private void tick() {
        if (State.getState() != null) { // bardzo ważne, żeby sprawdzić, czy istnieje jakikolwiek stan gry (MENU, GR, itpA)
            State.getState().tick();
        }
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

        /*g.drawImage(Assets.wall_black, 0, 64, null);
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

        g.drawImage(Assets.endPoint_Blue, 192+Assets.endPoint_Blue.getWidth()/2,192+Assets.endPoint_Blue.getHeight()/2,null);

        g.drawImage(Assets.crate_Blue, 128,128,null);*/

        if (State.getState() != null) {
            State.getState().render(g);
        }

        g.drawImage(Assets.character4, 64+Assets.character4.getWidth()/2, 64+64-Assets.character4.getHeight(), null);

        // Koniec rysowania

        bs.show();
        g.dispose();            // te linijki są zabezpieczeniem przed awarią
    }

    public void run() {

        init();

        // bardzo trudna do zapamiętania część RENDERU

        int fps = 60;
        double timePerTick = 1000000000/fps; //liczymy czas w nanosekundach
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames:" + ticks);
                ticks = 0;
                timer = 0;
            }
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