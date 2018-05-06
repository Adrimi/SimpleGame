package dev.adrimi.simplegame.gfx;

import java.awt.image.BufferedImage;

/**
 * PRE-Implementacja obrazów, ikonek gry. Potem jest łatwiej korzystać z gotowych wzorów do tworzenia obiektów typu TILE
 */
public class Assets {

    private static final int width = 64, height = 64;
    public static BufferedImage wall_black, groundGravel_Grass, groundGravel_Dirt, groundGravel_Sand, endPoint_Blue, crate_Blue;
    public static BufferedImage[] player_up, player_down, player_left, player_right, player_hold;

    public static void init() {         // wołamy tylko raz
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

        player_up   = new BufferedImage[4];
        player_down = new BufferedImage[4];
        player_left = new BufferedImage[2];
        player_right= new BufferedImage[2];
        player_hold= new BufferedImage[1];

        player_down[0] = sheet.crop(320, 362, 37, 59); // 5
        player_down[1] = sheet.crop(362, 248, 37, 59); // 4
        player_down[2] = sheet.crop(357, 362, 37, 59); // 6
        player_down[3] = sheet.crop(362, 248, 37, 59); // 4

        player_up[0] = sheet.crop(362, 188, 37, 60); // 8
        player_up[1] = sheet.crop(384, 0, 37, 60);   // 7
        player_up[2] = sheet.crop(362, 128, 37, 60); // 9
        player_up[3] = sheet.crop(384, 0, 37, 60);   // 7

        player_left[0] = sheet.crop(320, 304, 42, 58); // 10
        player_left[1] = sheet.crop(320, 186, 42, 59); // 1

        player_right[0] = sheet.crop(320, 128, 42, 58); // 3
        player_right[1] = sheet.crop(320, 245, 42, 59); // 2

        player_hold[0] = sheet.crop(362, 248, 37, 59); // 4

        wall_black          = sheet.crop(width, 0, width, height);

        groundGravel_Grass  = sheet.crop(width,height*3,width,height);
        groundGravel_Dirt   = sheet.crop(width, height*4, width, height);
        groundGravel_Sand   = sheet.crop(width, height*2, width, height);


        endPoint_Blue       = sheet.crop(128,384,32,32);

        crate_Blue          = sheet.crop(192,192,width,height);

    }
}
