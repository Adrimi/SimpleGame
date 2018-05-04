package dev.adrimi.simplegame.display;

import javax.swing.*;
import java.awt.*;

/**
 * Odpowiada za charakterystykę okienka gry, jego rozmiar i właściwości (położenie, rozszerzanie)
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);          // do zmiany!!!!
        frame.setLocationRelativeTo(null);  // na środku ekranu
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        canvas.setFocusable(false); // ważne, z tego powodu zacina się wczytywanie klawiatury

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {   // To pozwala nam rysować rzeczy, będzie zwracało potrzebne śmieci
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
