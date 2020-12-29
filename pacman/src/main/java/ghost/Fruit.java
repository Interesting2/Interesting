package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;


public class Fruit {

    private int x;
    private int y;

    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int get_X() {
        return this.x;
    }
    public int get_Y() {
        return this.y;
    }

}
