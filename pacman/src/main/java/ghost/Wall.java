package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    private int position_X;
    private int position_Y;
    private PImage wallImage;

    public Wall(int position_X, int position_Y, PImage wallImage) {
        this.position_X = position_X;
        this.position_Y = position_Y;
        this.wallImage = wallImage;
    }

    public int get_X() {
        return position_X;
    }
    public int get_Y() {
        return position_Y;
    }
   






}