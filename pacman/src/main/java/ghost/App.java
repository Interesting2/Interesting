package ghost;

import processing.core.PApplet;
import processing.core.PImage;          // load image
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;


public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;
    
    // objects
    private Player waka;
    private Board board;
    private ArrayList<Ghost> badGuys;
    private GameManager game;
    private int speed;

    public App() {
        game = new GameManager();
        board = new Board();
    }

    public void setup() {
        frameRate(60);                  // pre processing work 
        this.game.loaded();  
        this.game.initializeImage(this);                    
        this.game.load_Sprite();                            // found waka's position
        this.waka = this.game.initializeWaka(this); 
        this.waka.setDirections(this);
        this.game.wakaConfig(this.waka);
        this.badGuys = this.game.ghostConfig();
        this.game.boardConfig(this.board);
        this.game.setWakaBoard(this.board, this.waka);
        
    }
 
    public void settings() {
        size(WIDTH, HEIGHT);            // set up size of the window
    }

    public void draw() {                // 60 times a second
        this.background(80, 50, 80);

        this.board.draw(this);          // draws the board
        this.waka.tick();
        this.waka.draw(this);           // pass PApplet to Player class
        for (Ghost ghost : this.badGuys) {
            ghost.tick(this.waka);

            ghost.draw(this);
        }
    }

    public void keyPressed() {
        switch(keyCode) {
            case 37:
                this.waka.moveLeft(this);
                break;
            case 38:
                this.waka.moveUp(this);
                break;
            case 39:
                this.waka.moveRight(this);
                break;
            case 40:
                this.waka.moveDown(this);
                break;
            default:

        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");          // running the app
    }

}
