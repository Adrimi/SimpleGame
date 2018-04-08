package dev.adrimi.simplegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;
    public static BufferedImage character4, wall_black, groundGravel_Grass;

    public static void init() {         // wołamy tylko raz
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

        character4 = sheet.crop(362, 248, 37,59);
        wall_black = sheet.crop(64, 0, 64, 64);
        groundGravel_Grass = sheet.crop(64,192,64,64);
    }
}
