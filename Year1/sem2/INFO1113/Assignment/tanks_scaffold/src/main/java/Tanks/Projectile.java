package Tanks;
import java.util.*;
public class Projectile {
    private float cord_x;
    private float cord_y;
    private float cord_x_init;
    private float cord_y_init;
    private Tankpiece current;
    private float length;
    private double degree;
    private int power;
    private float y_val;
    private float x_val;
    private Wind w;
    private boolean is_init=true;
    private boolean proj_small=true;
    private boolean if_move=true;

    private ArrayList<Tankpiece> list_of_Tankpieces;
    private ArrayList<float[]> list_of_points_terrian;
    


    public Projectile(Tankpiece t,Wind w, ArrayList<Tankpiece> list_of_Tankpieces,ArrayList<float[]> list_of_points_terrian){
        this.current=t;
        this.cord_x=t.get_cord_x();
        this.cord_y=t.get_cord_y();
        this.cord_x_init=t.get_cord_x();
        this.cord_y_init=t.get_cord_y()-3;
        this.degree=t.get_degree();
        this.power=t.get_power();
        this.w=w;
        this.list_of_Tankpieces=list_of_Tankpieces;
        this.list_of_points_terrian=list_of_points_terrian;
    }

    public void projectile_init(){
        
        if(is_init){
            
            double turn= degree;
            // System.out.println(degree);
            turn=Math.toRadians(turn);
            double turn_sin=Math.sin(turn);
            double turn_cos=Math.cos(turn);
            y_val=(float)((1+(2*power/25))*turn_cos);
            x_val=(float)((1+(2*power/25))*turn_sin);
            
            length+=(1+(2*power/25));

            cord_x=(float)(turn_sin*length+cord_x_init);
            cord_y=(float)(cord_y_init-turn_cos*length);
            // System.out.println(length);
            
            // System.out.println(cord_y);
            if(length>=15){
                is_init=false;
            }
           
          
        }
           
       

    }
    
    public void projectile_move(){
        projectile_init();
        if(!is_init){
            cord_x+=(w.get_windspeed()*0.03)/60+x_val;
            cord_y-=y_val;
            y_val=(float)(y_val-(3.6/60));
            // System.out.println(cord_x);

        }


    }

    public void clash(){
        System.out.println("cordx: "+cord_x);
        float[] tmp=list_of_points_terrian.get((int)cord_x);
        System.out.println("cordx: "+cord_x+" terrain: "+tmp[1]);
       
        if(cord_y>tmp[1]){
            if_move=false;
        }
        
        
    }
    public void explosion(){
        
    }

    public boolean get_if_move(){
        return if_move;
    }

    public boolean get_is_init(){
        return is_init;
    }

    public float get_cord_x(){
        return cord_x;
    }

    public float get_cord_y(){
        return cord_y;
    }


    public void draw(App app){

        
        app.fill(Float.parseFloat(current.get_color()[0]),Float.parseFloat(current.get_color()[1]),Float.parseFloat(current.get_color()[2]));
        app.noStroke();
        if(proj_small){
            app.ellipse(cord_x, cord_y, 10, 10);
        }
        else{
            app.ellipse(cord_x, cord_y, 20, 20);
        }
        app.stroke(0);
        app.fill(0,0,0);
        app.ellipse(cord_x,cord_y,1,1);



    }




    
}
