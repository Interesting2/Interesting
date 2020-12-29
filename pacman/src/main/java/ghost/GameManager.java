package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;


public class GameManager {

    private char[][] map;
    private ArrayList<Wall> wall_Positions;
    private ArrayList<Fruit> fruits;
    private ArrayList<Ghost> badGuys;
    private Board board;

    private PImage[][] board_Piece;

    private int position_X;                         // waka position
    private int position_Y;
    private int lives;
    private int speed;
    private int[] modes;

    public GameManager() {
        map = new char[36][28];
        wall_Positions = new ArrayList<Wall>();
        fruits = new ArrayList<Fruit>();
        badGuys = new ArrayList<Ghost>();

        board_Piece = new PImage[36][28];
        board = new Board();

        position_X = 0;
        position_Y = 0;
        lives = 0;
        speed = 0;
    }

    public String loadMap() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("config.json"));
            JSONObject jsonobject = (JSONObject) obj;
            JSONArray arr = new JSONArray();
            
            String map_Name = (String) jsonobject.get("map");
            long temp = (long) jsonobject.get("lives");  
            long temp2 = (long) jsonobject.get("speed");

            lives = Integer.parseInt(String.valueOf(temp));
            speed = Integer.parseInt(String.valueOf(temp2));
            arr = (JSONArray) jsonobject.get("modeLengths");
            modes = new int[arr.size()];
            for (int i = 0; i < modes.length; i ++) {
                long convert = (long) arr.get(i);
                modes[i] = (int) convert;
            }            

            return map_Name; 

        } catch(FileNotFoundException e) {
            System.out.println("");
        } catch(ParseException f) {
            System.out.println("");
        } catch(IOException d) {
            System.out.println("");
        }
        return ""; 
    }

    public void loaded() {   
        String map_Name = loadMap(); 
        try {
            int count = 0;
            File file = new File(map_Name);         
            Scanner scn = new Scanner(file);
            for (int row = 0; row < 36; row ++) {
                String x = scn.nextLine();
                for (int col = 0; col < 28; col ++) {
                    char c = x.charAt(col);
                    map[row][col] = c;
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("im here");
        }
    }

    public void initializeImage(PApplet app) {
        for (int i = 0; i < this.map.length; i ++) {
            for (int j = 0; j < this.map[i].length; j ++) {
                if (i == map.length - 2 && j < lives) {
                    match('l', app, i, j, wall_Positions);
                }
                match(this.map[i][j], app, i, j, wall_Positions);        // match each image
            }
        }
    }
    public void match(char c, PApplet app, int row, int col, ArrayList<Wall> wally) {
        switch(c) {
            case '1':
                app.loadImage("src/main/resources/horizontal.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/horizontal.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));
                break;
                
            case '2':
                app.loadImage("src/main/resources/vertical.png");  
                board_Piece[row][col] = app.loadImage("src/main/resources/vertical.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));

                break;
            case '3':                   
                app.loadImage("src/main/resources/upLeft.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/upLeft.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));
                break;
            case '4':                   
                app.loadImage("src/main/resources/upRight.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/upRight.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));
                break;
                
            case '5':                   
                app.loadImage("src/main/resources/downLeft.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/downLeft.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));
                break;
                
            case '6':                   
                app.loadImage("src/main/resources/downRight.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/downRight.png");
                wally.add(new Wall(col * 16, row * 16, board_Piece[row][col]));
                break;
                
            case '7':                   
                app.loadImage("src/main/resources/fruit.png");
                board_Piece[row][col] = app.loadImage("src/main/resources/fruit.png");
                fruits.add(new Fruit(col * 16, row * 16));
                
                break;
            case 'p':
                position_X = row ;
                position_Y = col;
                
                break;
                                    
            case 'a':
                app.loadImage("src/main/resources/ghost.png");
                badGuys.add(new Ghost(col * 16 + 8, row * 16 + 8, app.loadImage("src/main/resources/ambusher.png"), this.speed, new int[] {400, 48}));
                break;

            case 'c':
                app.loadImage("src/main/resources/ghost.png");
                badGuys.add(new Ghost(col * 16 + 8, row * 16 + 8, app.loadImage("src/main/resources/chaser.png"), this.speed, new int[] {0, 40}));
                break;
            
            case 'i':
                app.loadImage("src/main/resources/ghost.png");
                badGuys.add(new Ghost(col * 16 + 8, row * 16 + 8, app.loadImage("src/main/resources/ignorant.png"), this.speed, new int[] {0, 560}));
                break;

            case 'w':
                app.loadImage("src/main/resources/ghost.png");
                badGuys.add(new Ghost(col * 16 + 8, row * 16 + 8, app.loadImage("src/main/resources/whim.png"), this.speed, new int[] {448, 576}));
                break;
            
            case 'l':
                app.loadImage("src/main/resources/playerRight.png");
                board_Piece[row][col * 2] = app.loadImage("src/main/resources/playerRight.png");
                break;
            default:
                
                break;
            
        }
    }


    public void load_Sprite() {
        int x = 0;
        int y = 0; 
        for (int row = 0; row < this.map.length; row ++) {
            for (int col = 0; col < this.map[row].length; col ++) {
                if (row == position_X && col == position_Y) {
                    position_X = col * 16;
                    position_Y = row * 16;
                }
            
            }
           
        }
    }
    public Player initializeWaka(PApplet app) {
        return new Player(this.position_X + 8, this.position_Y + 8, this.speed);
    }
    
    public void wakaConfig(Player waka) {
        waka.setWalls(wall_Positions);
        waka.setFruits(this.fruits);
    }

    public ArrayList<Ghost> ghostConfig() {
        for (Ghost ghost : this.badGuys) {
            ghost.setWalls(wall_Positions);
            ghost.setMode(this.modes);
        }
        return badGuys;
    }
    public void boardConfig(Board board) {
        board.setBoard(this.board_Piece);                           // set up
        board.setCoords(this.position_X, this.position_Y);          // player position
        board.setGhost(this.badGuys);
    }
    
    public void setWakaBoard(Board board, Player waka) {
        waka.setBoard(board);
    }

}

