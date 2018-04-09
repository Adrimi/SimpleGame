package dev.adrimi.simplegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;
    public static BufferedImage character4, wall_black, groundGravel_Grass, groundGravel_Dirt, groundGravel_Sand, endPoint_Blue, crate_Blue;

    public static void init() {         // wołamy tylko raz
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

        character4          = sheet.crop(362, 248, 37,59);

        wall_black          = sheet.crop(width, 0, width, height);

        groundGravel_Grass  = sheet.crop(width,height*3,width,height);
        groundGravel_Dirt   = sheet.crop(width, height*4, width, height);
        groundGravel_Sand   = sheet.crop(width, height*2, width, height);


        endPoint_Blue       = sheet.crop(128,384,32,32);

        crate_Blue          = sheet.crop(192,192,width,height);

    }
}
