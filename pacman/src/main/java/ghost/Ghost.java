package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.stream.*;

public class Ghost {

    private int x;
    private int y;

    private int X_velocity;
    private int Y_velocity;
    private PImage badGuyImage;
    private int speed;
    private GhostModes[] modes;

    private int[] scatterTarget;
    private int index;
    private ArrayList<Wall> wall_Positions;
    private String lastDirection;
    private boolean scatterPos;
    private int[] targetPos;


    public Ghost(int x, int y, PImage badGuyImage, int speed, int[] scatterTarget) {
        this.x = x;
        this.y = y;
        this.badGuyImage = badGuyImage;
        this.speed = speed;
        this.scatterTarget = scatterTarget;
        wall_Positions = new ArrayList<Wall>();
        index = 0;
        lastDirection = null;
        scatterPos = false;
        targetPos = scatterTarget;


    }

    public void setMode(int[] arr) {                // intialize the modes
        modes = new GhostModes[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            if (i % 2 == 0) {
                Type type = Type.SCATTER;
                modes[i] = new GhostModes(type, arr[i]);
                
            }else {
                Type type = Type.CHASE;
                modes[i] = new GhostModes(type, arr[i]);
            }
        }
    }

    public void tick(Player waka) {            // handles logic
        changeMode();
        targetPosition(waka);
        HashMap<String, Integer> directions = new HashMap<>();

        
        if (this.x != targetPos[0] && this.y != targetPos[1] ) {

            //System.out.println(this.x + " " + this.y );
            boolean r = collision(speed, 0);
            boolean l = collision(-1 * speed, 0);
            boolean u = collision(0, -1 * speed);
            boolean d = collision(0, speed);

            if (lastDirection == null) {
                if (r == false) {             // right
                    int right = checkDistance(speed, 0);   
                    directions.put("right", right);
                }
                if (l == false) {        // left
                    int left = checkDistance(-1 * speed, 0);
                    directions.put("left", left);
                }
                
                if (d == false) {             // down
                    int down = checkDistance(0, speed);
                    directions.put("down", down);
                }
                if (u == false) {        // up
                    int up = checkDistance(0, -1 * speed);  
                    directions.put("up", up);         
                }
                
            }
            else {
                //System.out.println(l + " " + r + " " + u + " " + d + " " + lastDirection);
                switch (lastDirection) {
                    case "right":
                        if (r == false) {
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }
                        if (r == true && d == false) {
                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                        }
                        
                        if (u == false && d == true) {
                            if ( (this.x + 8 ) % 16 == 0) {
                                int up = checkDistance(0, -1 * speed);  
                                directions.put("up", up); 
                                
                            }
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }
                        if (r == true && u == true && lastDirection != "up") {
                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                            
                        }
                        
                        if (r == true && d == true && u == false) {
                            int up = checkDistance(0, -1 * speed);  
                            directions.put("up", up);
                        }
                        if (r == false && d == false && u == false && l == false) {
                            if ( (this.x + 8) % 16 == 0) {
                                int up = checkDistance(0, -1 * speed);  
                                directions.put("up", up);
                            }
                        }
                        

                        break;
                        
                    case "down":
                        if (d == false) {
                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                        }
                        if (r == false && d == false) {
                            if ( (this.y + 8) % 16 == 0){
                                int right = checkDistance(speed, 0);   
                                directions.put("right", right);
                            }
                        }
                        if (d == true && l == false && r == false) {
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                        }
                        if (d == true && l == true && r == false && lastDirection != "right") {
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }
                        if (r == true && d == true && l == false) {
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                        }
                        if (l == false && d == false && r == true) {
                            
                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                        }
                        if (l == true && r == false) {
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }

                        break;
                                            
                    
                    case "up":
                        if (u == false) {
                            int up = checkDistance(0, -1 * speed);  
                            directions.put("up", up);
                        }
                        if (u == true && r == true) {
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                        }
                        if (u == true && l == false && r == false) {
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }
                        if (l == true && u == true) {
                            int right = checkDistance(speed, 0);   
                            directions.put("right", right);
                        }
                        
                        
                        break;
                    
                    case "left":
                        if (l == false) {
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                        }
                        if (u == false && d == true) {
                            if ( (this.x + 8) % 16 == 0) {
                                int up = checkDistance(0, -1 * speed);  
                                directions.put("up", up);
                            }
                        }
                        if (l == true && d == true) {
                            int up = checkDistance(0, -1 * speed);  
                            directions.put("up", up);
                        }
                        if (l == true && d == false && u == false) {
                            int up = checkDistance(0, -1 * speed);  
                            directions.put("up", up);

                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                        }
                        if (l == true && u == true && d == false) {
                            int down = checkDistance(0, speed);
                            directions.put("down", down);
                        }
                        if (r == false && d == false && u == false && l == false) {
                            if ( (this.x + 8) % 16 == 0) {
                                int up = checkDistance(0, -1 * speed);  
                                directions.put("up", up);
                            }
                        }

                }   
                

                
                
            }
                
        }else {
            scatterPos = true;
            boolean r = collision(speed, 0);
            boolean l = collision(-1 * speed, 0);
            boolean u = collision(0, -1 * speed);
            boolean d = collision(0, speed);
            switch (lastDirection) {

                case "right":
                    if (r == false && u == true) {
                        int right = checkDistance(speed, 0);   
                        directions.put("right", right);
                    } 
                    if (r == true && u == true) {
                        lastDirection = "down";
                    }
                    break;

                case "down":
                    if (d == false && r == true)  {
                        int down = checkDistance(0, speed);
                        directions.put("down", down);
                    }
                    if (l == false && r == true) {
                        if ( (this.y + 9) % 16 == 0) {
                            int left = checkDistance(-1 * speed, 0);
                            directions.put("left", left);
                        }
                    }
                    
                    
                    break;

                case "left":
                    
                    if (l == true) {
                        int down = checkDistance(0, speed);
                        directions.put("down", down);
                    }
                    if (l == false) {
                        int left = checkDistance(-1 * speed, 0);
                        directions.put("left", left);
                    }
                    if (r == false && d == false && u == false && l == false) {
                            if ( (this.x + 8) % 16 == 0) {
                                int up = checkDistance(0, -1 * speed);  
                                directions.put("up", up);
                            }
                    }

                    
                    
                    break;

                case "up":
                    
                    if (u == false) {
                        int up = checkDistance(0, -1 * speed);  
                        directions.put("up", up);
                    }
                    if (u == true) {
                        int left = checkDistance(-1 * speed, 0);
                        directions.put("left", left);
                        int right = checkDistance(speed, 0);   
                        directions.put("right", right);
                    }
                    
            }
            
        }
        if (directions.size() != 0) {
            for (String x : directions.keySet()) {
                if (directions.get(x) == getMin(directions)) {
                    lastDirection = x;
                    //System.out.println(x);
                    move(x);
                }
            }
        }
            
            //targetPosition(waka);    
        
    }

    
    public int getMin(HashMap<String, Integer> directions) {
        int minValue = Integer.MAX_VALUE;
        for(String key : directions.keySet()) {
            int value = directions.get(key);
            if(value < minValue) {
                minValue = value;        
            }
        }
        return minValue;
    }

    public void move(String direction) {
        switch(direction) {
            case "right":
                this.x += speed;
                break;
            case "left":
                this.x -= speed;
                break;
            case "up":
                this.y -= speed;

                break;
            case "down":
                this.y += speed;
                break;
            default:
                break;
        }

    }

    public int checkDistance(int next_X, int next_Y) {
        double a = Math.pow(this.x + next_X - targetPos[0], 2);
        double b = Math.pow(this.y + next_Y - targetPos[1], 2);
        int distance = (int) Math.sqrt(a + b);
        return distance;

    }

    public void setWalls(ArrayList<Wall> wall_Positions) {
        this.wall_Positions = wall_Positions;
    }

    public boolean collision(int x, int y) {
        // right +8 offset  /    left - 8 offset
        if (x == speed && y == 0) {
            x += 8;
        } else if (x == -1 * speed && y == 0) {
            x -= 8;
        } else if (x == 0 && y == speed) {
            y += 8;
        } else if (x == 0 && y == -1 * speed) {
            y -= 8;
        }
        for (Wall wall : wall_Positions) { 
            if ( (this.x + x) / 16  == wall.get_X() / 16 && (this.y + y) / 16 == wall.get_Y() / 16) { // same grid position
                return true;
            }  
        }
        return false;
    }
    



    public void changeMode() {
        GhostModes curr = modes[index];          // current mode
        curr.framesLeft -= 1;   
        if (curr.framesLeft == 0) {
            index += 1;
        }
    }

    public void targetPosition(Player waka) {
        Type mode = this.modes[index].getMode();
        System.out.println(mode);

        switch (mode) {
            case CHASE:
                targetPos[0] = waka.get_X();
                targetPos[0] = waka.get_Y();
                break;

            case SCATTER:
                targetPos[0] = scatterTarget[0];
                targetPos[1] = scatterTarget[1];
                break;
        }


    }


    public void draw(PApplet app) {            // handling graphics
        app.image(this.badGuyImage, this.x - 13, this.y - 13);

    }

}
