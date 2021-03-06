package dev.adrimi.simplegame.states;

import dev.adrimi.simplegame.Handler;

import java.awt.*;

/**
 * Dla lepszej implementacji, oraz wydajniejszego organizowania stanów programów.
 */
public abstract class State {

    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    // CLASS

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

}
