package Tanks;

import org.checkerframework.checker.units.qual.A;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int CELLSIZE = 32; //8;
    public static final int CELLHEIGHT = 32;

    public static final int CELLAVG = 32;
    public static final int TOPBAR = 0;
    public static int WIDTH = 864; //CELLSIZE*BOARD_WIDTH;
    public static int HEIGHT = 640; //BOARD_HEIGHT*CELLSIZE+TOPBAR;
    public static final int BOARD_WIDTH = WIDTH/CELLSIZE;
    public static final int BOARD_HEIGHT = 20;

    public static final int INITIAL_PARACHUTES = 1;

    public static final int FPS = 30;

    public String configPath;

    public static Random random = new Random();
    private PImage img;
    private PImage img_tree;
    private JSONArray json_array;
    private JSONObject json_obj;
    private String layout;
    private String background;
    private String foreground_colour;
    private String trees;
    private ArrayList<float[]> list_of_points_terrian = new ArrayList<float[]>();
    private ArrayList<float[]> list_of_points_tree = new ArrayList<float[]>();
    
    
	
	// Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player and map elements.
     */
	@Override
    public void setup() {
        frameRate(FPS);
        json_obj = loadJSONObject(configPath);
        
        json_array=json_obj.getJSONArray("levels");
        layout=json_array.getJSONObject(0).getString("layout");
        // System.out.println(layout);
        background=json_array.getJSONObject(0).getString("background");
        foreground_colour=json_array.getJSONObject(0).getString("foreground-colour");
        trees=json_array.getJSONObject(0).getString("trees");
        if(trees==null){
            trees="tree2.png";
        }
        img=loadImage("src/main/resources/Tanks/"+background);
        img_tree=loadImage("src/main/resources/Tanks/"+trees);
        
        File f_in;
        
        Scanner scan=null;
        Scanner scan2=null;

        try{
            
            f_in=new File(layout);
            scan = new Scanner(f_in);
            scan2=new Scanner(f_in);
            
            }catch(FileNotFoundException e){
                System.out.println("ERROR ON FILE PARSING");
            }
        terrian_setup(scan);
        tree_setup(scan2);
        
		//See PApplet javadoc:
		//loadJSONObject(configPath)
		//loadImage(this.getClass().getResource(filename).getPath().toLowerCase(Locale.ROOT).replace("%20", " "));
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(KeyEvent event){
        
    }

    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO - powerups, like repair and extra fuel and teleport


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Draw all elements in the game by current frame.
     */
   
    public void terrian_setup(Scanner scan){
        String f_input;
        
        char char_of_f;
        float y_cord=0;
        float x_cord=0;
        
        while(scan.hasNextLine()){
            f_input=scan.nextLine();
            int i=0;
            
            while(i<f_input.length()){  //The file contain spaces which is 28*20
                char_of_f= f_input.charAt(i);
                if(char_of_f=='X'){
                    x_cord=i*32;
                   
                    for(int k=0;k<32;k++){
                        float[] cord=new float[2];
                        cord[0]=x_cord;
                        cord[1]=y_cord*32;
                        list_of_points_terrian.add(cord);
                        x_cord+=1;
                    }
                }
              
                
            
                i+=1;
            }
            y_cord+=1;
    }
    Terrian t=new Terrian();
    
        list_of_points_terrian=t.sort_from_leftToRight(list_of_points_terrian);
        list_of_points_terrian=t.moveavrage(list_of_points_terrian);
        list_of_points_terrian=t.moveavrage(list_of_points_terrian);
}
    public void tree_setup(Scanner scan){
        String f_input;
        
        char char_of_f;
        float y_cord=0;
      
        
        while(scan.hasNextLine()){
            f_input=scan.nextLine();
            int i=0;
            
            while(i<f_input.length()){  //The file contain spaces which is 28*20
                char_of_f= f_input.charAt(i);
                
                if(char_of_f=='T'){
                    
                    float[] cord=new float[2];
                    int min = -15; 
                    int max = 15; 
                    
                    int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                    cord[0]=i*32+random_int;
                    float[] temp=list_of_points_terrian.get((int)cord[0]);
                    y_cord=temp[1]-32;
                    cord[1]=y_cord;
                    list_of_points_tree.add(cord);
                }  
                
            
                i+=1;
            }
            y_cord+=1;
    }
   

    }
	
     @Override
    public void draw() {
        image(img,0,0);
        // image(img_tree,100,100,32, 32);
       
        
      
        
         //middle point x=432 y=320
      
     

        Terrian t=new Terrian();
        // Tree tree=new Tree();
        t.draw(this, list_of_points_terrian,list_of_points_tree,img_tree);
        // tree.draw(this,list_of_points_tree,img_tree)
            
        

        //----------------------------------
        //display HUD:
        //----------------------------------
        //TODO

        //----------------------------------
        //display scoreboard:
        //----------------------------------
        //TODO
        
		//----------------------------------
        //----------------------------------

        //TODO: Check user action
    }


    public static void main(String[] args) {
        PApplet.main("Tanks.App");
    }

}