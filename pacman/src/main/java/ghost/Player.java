package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Player {

    private int x;
    private int y;

    private int speed;
    private int X_velocity;
    private int Y_velocity;
    private PImage[] directions;

    private ArrayList<Fruit> fruits;
    private ArrayList<Wall> wall_Positions;
    private Board board;
    
    private int timer;

    public Player(int x, int y, int speed) {
        this.x = x;                         
        this.y = y;                         
        this.X_velocity = 0;
        this.Y_velocity = 0;
        this.speed = speed;
        directions = new PImage[5];
        
        
    }

    public void tick() {            // handles logic
        this.x += this.X_velocity;
        this.y += this.Y_velocity;
        if (checkCollision() == true) {                    // check if next move is valid
            this.x -= this.X_velocity;
            this.y -= this.Y_velocity;
        }

    }
    public int get_X() {
        return this.x;
    }
    public int get_Y() {
        return this.y;
    }
    public void draw(PApplet app) {            // handling graphics
        if (timer < 8) {
            app.image(directions[4], this.x - 13, this.y - 13 );
        }
        else if (timer % 16 >= 8) {          
            app.image(directions[4], this.x - 13, this.y - 13);    
        }
        else if (timer % 16 < 8) {
            if (X_velocity == -1 * speed) {
                app.image(directions[1], this.x - 13, this.y - 13 );
            }else if (X_velocity == speed) {
                app.image(directions[0], this.x - 13, this.y - 13 );
            }else if (Y_velocity == -1 * speed) {
                app.image(directions[2], this.x - 11, this.y - 11 );
            }else if (Y_velocity == speed){
                app.image(directions[3], this.x - 11, this.y - 11);
            }
        }
        timer ++;    

    }

    public void moveRight(PApplet app) {
        this.Y_velocity = 0;
        this.X_velocity = speed;
    }
    public void moveLeft(PApplet app) {
        this.Y_velocity = 0;
        this.X_velocity = -1 * speed;

    }public void moveUp(PApplet app) {
        this.X_velocity = 0;
        this.Y_velocity = -1 * speed;

    }public void moveDown(PApplet app) {
        this.X_velocity = 0;
        this.Y_velocity = speed;
    }

      
    public void setWalls(ArrayList<Wall> wall_Positions) {
        this.wall_Positions = wall_Positions;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setDirections(PApplet app) {
        directions[0] = app.loadImage("src/main/resources/playerRight.png");
        directions[1] = app.loadImage("src/main/resources/playerLeft.png");
        directions[2] = app.loadImage("src/main/resources/playerUp.png");
        directions[3] = app.loadImage("src/main/resources/playerDown.png");
        directions[4] = app.loadImage("src/main/resources/playerClosed.png");
    }

    public boolean checkCollision() {
        int x = 0;
        int y = 0;
        if (X_velocity == speed) {
            x += 8;
        }
        else if (Y_velocity == speed) {
            y += 8;
        }
        else if (Y_velocity == -1 * speed) {
            y -= 8;
        }
        else {
            x -= 8;
        }

        for (Fruit fruit : fruits) {
            if ( (this.x) / 16  == fruit.get_X() / 16 && (this.y) / 16 == fruit.get_Y() / 16) { // same grid position
                this.board.setCoords(fruit.get_X() / 16, fruit.get_Y() / 16);
            }  
        }


        
        for (Wall wall : wall_Positions) {
            
            if ( (this.x + x) / 16  == wall.get_X() / 16 && (this.y + y) / 16 == wall.get_Y() / 16) { // same grid position
                return true;
            }  
        }
        return false;    

    }


}
