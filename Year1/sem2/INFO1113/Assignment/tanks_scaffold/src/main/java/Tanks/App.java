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
    private PImage img_wind_left;
    private PImage img_wind_right;
    private PImage img_fuel;
    private PImage img_parachute;
    private JSONArray json_array;
    private JSONObject json_obj;
    private String layout;
    private String background;
    private String foreground_colour;
    private String trees;
    private long start;
    private long finish;
    private long duration;
    private float radius;
    private ArrayList<float[]> list_of_points_terrian = new ArrayList<float[]>();
    private ArrayList<float[]> list_of_points_tree = new ArrayList<float[]>();
    private ArrayList<Tankpiece> list_of_Tankpieces=new ArrayList<Tankpiece>();
    private ArrayList<Tankpiece> list_of_Tankpieces_final=new ArrayList<Tankpiece>();
    private ArrayList<Projectile> list_of_Projectiles=new ArrayList<Projectile>();
    private Tankpiece current_player;
    private boolean turret_mov_left;
    private boolean turret_mov_right;
    private boolean tank_move_left;
    private boolean tank_move_right;
    private boolean w_press;
    private boolean s_press;
    private boolean init;
    private boolean if_execute=true;
    Wind wind;
    private int level=0;
    private int current_level;

    
    
    
	
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
        reset();
        init=true;

        
        
		//See PApplet javadoc:
		//loadJSONObject(configPath)
		//loadImage(this.getClass().getResource(filename).getPath().toLowerCase(Locale.ROOT).replace("%20", " "));
    }
    public void reset(){
    img=null;
    img_tree=null;
    json_array=null;
    json_obj=null;
    layout=null;
    background=null;
    foreground_colour=null;
    trees=null;
    list_of_points_terrian = new ArrayList<float[]>();
    list_of_points_tree = new ArrayList<float[]>();
    list_of_Projectiles=new ArrayList<Projectile>();
    // list_of_Tankpieces=new ArrayList<Tankpiece>();
    current_player=null;
    json_obj = loadJSONObject(configPath);
    current_level=0;
    
    json_array=json_obj.getJSONArray("levels");
    layout=json_array.getJSONObject(level).getString("layout");
    // System.out.println(layout);
    background=json_array.getJSONObject(level).getString("background");
    foreground_colour=json_array.getJSONObject(level).getString("foreground-colour");
    trees=json_array.getJSONObject(level).getString("trees");
    if(trees==null){
        trees="tree2.png";
    }
    img=loadImage("src/main/resources/Tanks/"+background);
    img_tree=loadImage("src/main/resources/Tanks/"+trees);
    img_wind_left=loadImage("src/main/resources/Tanks/wind-1.png");
    img_wind_right=loadImage("src/main/resources/Tanks/wind.png");
    img_fuel=loadImage("src/main/resources/Tanks/fuel.png");
    img_parachute=loadImage("src/main/resources/Tanks/parachute.png");
    
    File f_in;
    
    Scanner scan=null;
    Scanner scan2=null;
    Scanner scan3=null;

    try{
        
        f_in=new File(layout);
        scan = new Scanner(f_in);

        scan2=new Scanner(f_in);
        scan3=new Scanner(f_in);
        
        }catch(FileNotFoundException e){
            System.out.println("ERROR ON FILE PARSING");
        }
    terrian_setup(scan);
    tree_setup(scan2);
    tank_setup(scan3);
    current_player=list_of_Tankpieces.get(0);
    wind=new Wind(img_wind_left,img_wind_right);


    }

    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(KeyEvent event){
        if(key==CODED){
            if(keyCode==UP){
                System.out.println("UP"); //turrent move +-3 rand
                turret_mov_left=true;
               
                
            }
            if(keyCode==DOWN){
                System.out.println("DOWN");
                turret_mov_right=true;
              
            }
            if(keyCode==LEFT){
                System.out.println("LEFT");//tank move +-60 pixel
                tank_move_left=true;
               

            }
            if(keyCode==RIGHT){
                System.out.println("RIGHT"); 
                tank_move_right=true;
              
            }

        }

        if(key==' '){
            finish=System.nanoTime();
            if(finish-start>0.05*1000000000){
                if_execute=true;
            }
            if(if_execute){
                System.out.println("Space");//fire
                
                // if(list_of_Tankpieces.size()>0){
                // list_of_Tankpieces_final.add(list_of_Tankpieces.get(0));
                // list_of_Tankpieces.remove(0);
                // System.out.println(list_of_Tankpieces_final.get(0).get_call_sign());
                if(list_of_points_terrian.get((int)(current_player.get_turret_pos()[0]))[1]>current_player.get_turret_pos()[1]){ 
                    //EDGE CASE ATTENTION
                    Projectile p=new Projectile(current_player, wind,list_of_Tankpieces,list_of_points_terrian);
                    list_of_Projectiles.add(p); 
                    wind.random_turn();
                    current_level+=1;
                    if(list_of_Tankpieces.size()>0){
                        current_player=list_of_Tankpieces.get(current_level);
                    }
                    
                    if(current_level>=list_of_Tankpieces.size()-1){
                        current_level=-1;
                    }
            }
            else{
                System.out.println("INVALID TURRET POSITION");
            }
            start = System.nanoTime();
            if_execute=false;
        }
        
        
           
            
            // }
        }
        if(Character.toUpperCase(key)=='S'){
            System.out.println("S");
            s_press=true;
           
        }
        if(Character.toUpperCase(key)=='W'){
            System.out.println("W");
            w_press=true;
         
        }
        if(Character.toUpperCase(key)=='F'){
            current_player.add_fuel(200);
        }
        if(Character.toUpperCase(key)=='R'){

        }
        if(Character.toUpperCase(key)=='X'){

        }
        if(Character.toUpperCase(key)=='P'){
            
        }
        
    }

    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){
        if(key==CODED){
            if(keyCode==UP){
                System.out.println("UP"); //turrent move +-3 rand
                turret_mov_left=false;
            
                
            }
            if(keyCode==DOWN){
                System.out.println("DOWN");
                turret_mov_right=false;
          
                
            }
            if(keyCode==LEFT){
                System.out.println("LEFT");//tank move +-60 pixel
                tank_move_left=false;
        
                

            }
            if(keyCode==RIGHT){
                System.out.println("RIGHT"); 
                tank_move_right=false;
           
                
            }
    }
    if(Character.toUpperCase(key)=='S'){
       
        s_press=false;
     
    }
    if(Character.toUpperCase(key)=='W'){
        
        w_press=false;
      
    }
    
}

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO - powerups, like repair and extra fuel and teleport
        System.out.println(e.getX());
        System.out.println(e.getY());



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
    public void tank_setup(Scanner scan){
        String f_input;
        
        JSONObject json_obj1;
        char char_of_f;
        float y_cord=0;
        json_obj1=json_obj.getJSONObject("player_colours");
        ArrayList<Tankpiece> temp_ArrayList=new ArrayList<Tankpiece>();
        int y=0; //debug purpose
      
        
        while(scan.hasNextLine()){
            f_input=scan.nextLine();
            int i=0;
            y++;
            
            while(i<f_input.length()){  //The file contain spaces which is 28*20
                char_of_f= f_input.charAt(i);
                String c=String.valueOf(char_of_f).trim();
                if(char_of_f!='T'&& char_of_f!='X'&& char_of_f!='T'&& char_of_f!='X'&& !String.valueOf(char_of_f).trim().equals("")){  //WEIRD char_of_f==" "
                    // System.out.println(char_of_f); debug 
                    float[] cord=new float[2];
                    
                    cord[0]=i*32;
                    float[] temp=list_of_points_terrian.get((int)cord[0]);
                    y_cord=temp[1];
                    cord[1]=y_cord;
                    String color=json_obj1.getString(String.valueOf(char_of_f));
                   
                    if(init==false){
                        Tankpiece a=new Tankpiece(cord[0],cord[1],color,char_of_f,0);
                        temp_ArrayList.add(a);
                    }
                    else{
                        int p=0;
                        for(Tankpiece t:list_of_Tankpieces_final){
                            if(t.get_call_sign()==char_of_f){
                                p=t.get_point();
                            }
                        }
                        Tankpiece a=new Tankpiece(cord[0],cord[1],color,char_of_f,p);
                        temp_ArrayList.add(a);
                    }
                    
                  
                    
                }  
                
            
                i+=1;
            }
            y_cord+=1;
    }
    list_of_Tankpieces=sort_tank(temp_ArrayList);
    list_of_Tankpieces_final=new ArrayList<Tankpiece>();
        
    }
    
    public ArrayList<Tankpiece> sort_tank(ArrayList<Tankpiece> list){
        ArrayList<Tankpiece> new_ArrayList=new ArrayList<Tankpiece>();
        while(list.size()>0){
            Tankpiece min_array=list.get(0);
            char min_num=min_array.get_call_sign();

            for(int i=1;i<list.size();i++){
                Tankpiece temp_array=list.get(i);
                char temp_num=temp_array.get_call_sign();
                if(temp_num<min_num){
                    min_array=temp_array;
                    min_num=temp_num;
                }


            }
            new_ArrayList.add(min_array);
            list.remove(list.indexOf(min_array));

    }
    
    return new_ArrayList;
}

    public void process_move(){
        if(turret_mov_left&&!turret_mov_right){
            current_player.turret_mov_left();
        }
        if(turret_mov_right&&!turret_mov_left){
            current_player.turret_mov_right();
        }
        if(tank_move_left&&!tank_move_right){
            current_player.tank_move_left(list_of_points_terrian);
        }
        if(tank_move_right&&!tank_move_left){
            current_player.tank_move_right(list_of_points_terrian);

        }
        if(w_press&&!s_press){
            current_player.power_increase();
            
        }
        if(s_press&&!w_press){
            current_player.power_decrease();
            
        }

    }

    public void process_projectile(ArrayList<Projectile> list_of_Projectiles){
        ArrayList<Projectile> list_of_Projectiles_copy=new ArrayList<Projectile>(list_of_Projectiles);
        for(Projectile projectile:list_of_Projectiles_copy){
            if(projectile.get_if_move()){
                projectile.projectile_move();
            
            }
            if(projectile.get_cord_x()<0||projectile.get_cord_x()>864||projectile.get_cord_y()<0||projectile.get_cord_y()>640){
                
                list_of_Projectiles.remove(projectile);
                continue;
            }

            // if(projectile.get_cord_x()<0||projectile.get_cord_x()>864||projectile.get_cord_y()<0||projectile.get_cord_y()>640){
                
            //     list_of_Projectiles.remove(projectile);
            //     continue;
                
            // }

           
 
           
            if(!projectile.get_is_init()){
                projectile.clash();
            }
            
        }

    }
	
     @Override
    public void draw() {
        image(img,0,0);
        // image(img_tree,100,100,32, 32);
       
        
      
        
         //middle point x=432 y=320
      
     

        Terrian t=new Terrian();
        // Tree tree=new Tree();
        t.draw(this, list_of_points_terrian,list_of_points_tree,img_tree,foreground_colour);
        // tree.draw(this,list_of_points_tree,img_tree)
        process_move();
        for(Tankpiece tank:list_of_Tankpieces){
            tank.draw(this);
        }

        process_projectile(list_of_Projectiles);
        
        for(Projectile projectile:list_of_Projectiles){
            if(projectile.get_if_move()){
                projectile.draw(this);
            }
            
        }

        for(Projectile projectile:list_of_Projectiles){
            if(!projectile.get_if_move()&&radius<4){
                radius+=0.5;
                this.noStroke();
                
                if(radius<3){
                    this.noStroke();
                    this.fill(255,0,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), radius*10, radius*10);
                    System.out.println("r<30");
                }
                else{
                    
                    this.noStroke();
                    this.fill(255,0,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), 30, 30);
                    System.out.println("r=30");
                }
                
                if(radius<1.5){
                    this.fill(255,165,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), radius*10, radius*10);
                    System.out.println("r<15");

                }
                else{
                    this.fill(255,165,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), 15, 15);
                    System.out.println("r=15");
                }

                if(radius<0.6){
                    this.fill(255,255,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), radius*10, radius*10);
                    System.out.println("r<6");
                }
                else{
                    this.fill(255,255,0);
                    ellipse(projectile.get_cord_x(), projectile.get_cord_y(), 6, 6);
                    System.out.println("r=6");
            }
            }
        }
        ArrayList<Projectile> list_of_Projectiles_copy=new ArrayList<Projectile>(list_of_Projectiles);
       
         for(Projectile projectile:list_of_Projectiles_copy){
            if(!projectile.get_if_move()&&radius>=4){
                System.out.println("Enter");
                list_of_Projectiles.remove(projectile);
                radius=0;
            }
        }

     
       
        
        wind.draw(this);
        UI ui=new UI(current_player, list_of_Tankpieces);
        ui.draw(this, img_fuel, img_parachute);
        
        if(list_of_Tankpieces.size()==0){
            level+=1;
            reset();
        }
        //TODO missing logic remove and add to final tankpiece;
        

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
