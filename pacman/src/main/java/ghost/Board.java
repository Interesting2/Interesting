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
import java.io.IOException;
import java.util.HashMap;
import java.util.*;



public class Board {

    private ArrayList<Ghost> badGuys;
    private PImage[][] board_Piece;
    private int position_X;                        
    private int position_Y;
    
    public Board() {
        board_Piece = new PImage[36][28];
        badGuys = new ArrayList<Ghost>();
        position_X = 0;
        position_Y = 0;
    }

    public void setBoard(PImage[][] board_Piece) {
        this.board_Piece = board_Piece;
    }
    
    public void setCoords(int x, int y) {
        this.position_X = x;
        this.position_Y = y;
    }

    public void setGhost(ArrayList<Ghost> badGuys) {
        this.badGuys = badGuys;
    }
    public void draw(PApplet app) {    
        int x = 0;
        int y = 0; 
        for (int row = 0; row < board_Piece.length; row ++) {
            for (int col = 0; col < board_Piece[row].length; col ++) {
                for (Ghost ghost : this.badGuys) {
                    if (this.position_X == col && this.position_Y == row) {
                        board_Piece[row][col] = null;
                    }
                
                }
                if (board_Piece[row][col] != null) {  
                    app.image(board_Piece[row][col], x, y);    
                }

                x += 16;
            }
            x = 0;
            y += 16;
        }
    
    }
    

    





}
