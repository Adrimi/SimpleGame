package dev.adrimi.simplegame;

import dev.adrimi.simplegame.KeyManager.KeyManager;
import dev.adrimi.simplegame.display.Display;
import dev.adrimi.simplegame.gfx.Assets;
import dev.adrimi.simplegame.gfx.GameCamera;
import dev.adrimi.simplegame.states.GameState;
import dev.adrimi.simplegame.states.MenuState;
import dev.adrimi.simplegame.states.State;
import dev.adrimi.simplegame.tiles.Tile;


import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Główny wątek gry, taki kontroler tego co się dzieje w grze
 */
public class Game implements Runnable{

    /**
     * Obiekt, który określa szczegółwy wyświetlania okienka gry (fizycznego, bez dodatków w postaci Canvas itp.)
     */
    private Display display;

    /**
     * Tytuł gry
     */
    public String title;

    /**
     * Rozmiary okna
     */
    private int width, height;

    /**
     * Zmienna ta pozwala na używanie "GameLoop"
     */
    private boolean running = false;

    /**
     * Tworzenie wątków, które usprawniają działanie gry
     */
    private Thread thread;

    /**
     * Instrukcje JAK rysować obiekty, obrazy, co najważniejsze - ZAPOBIEGA MIGOTANIU.
     */
    private BufferStrategy bs;

    /**
     * Podstawowa klasa (narzędzie) do rysowania na Canvas
     */
    private Graphics g;

    //Stany
    private State gameState;
    private State menuState;

    //Sterowanie
    private KeyManager keyManager;

    //Kamera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int heigth) {
        this.width = width;
        this.height = heigth;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() {       // tylko raz, na początku
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler); // tutaj powstaje obiekt który dziedziczy po abstrakcyjnej State
        menuState = new MenuState(handler);
        State.setState(gameState);



    }

    private void tick() {
        keyManager.tick();

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

        if (State.getState() != null) {     // Wywołujemy metode renderu zależną, od stanu programu (MENU, GRA, itp.)
            State.getState().render(g);
        }

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
        // Metoda która zwraca prywatny parametr
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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


/*
display = new Display(title, handler.getWorld().getWidth() * Tile.TILEWIDTH,
                handler.getWorld().getHeight() * Tile.TILEHEIGHT);
 */