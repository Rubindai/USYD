package Tanks;
import java.util.*;



import processing.core.PImage;
public class Tankpiece {
    private int fuel=250;
    private int hp=100;
    private int parachute=3;
    private float cord_x;
    private float cord_y;
    private float[] pos;
    private int power=50;
    private String[] color;
    private char call_sign;
    private float[] turret_pos=new float[2];
    
    private double degree=0;


    private double init_cord_x_turret;
    private double init_cord_y_turret;

    private int point;
    



    public Tankpiece(float cord_x,float cord_y,String color_String,char call_sign,int point){
        set_cordx(cord_x);
        set_cordy(cord_y);

        String[] temp=colorparser(color_String);
        this.color=temp;
        // set_pos(cord_x, cord_y);
        this.call_sign=call_sign;
        set_turret_init(cord_x, cord_y);
        init_cord_x_turret=cord_x;
        init_cord_y_turret=cord_y-18;

        // this.degree=degree;
        
    }

    public void set_cordx(float cord_x){
        this.cord_x=cord_x;
    }
    public void set_cordy(float cord_y){
        this.cord_y=cord_y;
    }

    public void set_turret_init(float cordx,float cordy){
        float[] pos={cordx,cord_y-18};
        
        this.turret_pos =pos;
        
    }

    // public void set_pos(float cordx,float cordy){
    //     float[] pos={cordx,cord_y};
    //     this.pos =pos;
    // }

    public void set_point(int point){
        this.point=point;
    }

    public float get_cord_x(){
        return cord_x;
    }

    public float get_cord_y(){
        return cord_y;
    }

    public int get_point(){
        return point;
    }

    // public float[] get_pos(){
        
    //     return pos;
    // }
    public char get_call_sign(){
        return call_sign;
    }

    public String[] get_color(){
        return color;
    }

    public int get_power(){
        return power;
    }
    
    public int get_fuel(){
        return fuel;
    }
    
    public int get_parachute(){
        return parachute;
    }
    
    public int get_hp(){
        
        return hp;
    }

    public double get_degree(){
        return degree;
    }

    public float[] get_turret_pos(){
        return turret_pos;
    }
    
    public String[] colorparser(String color){
        String[] temp=color.split(",");
        return temp;
    }
    

    // public float[] turrent_pos(){

    // }
    public void process_turret(){
        if(Math.abs(degree)<90){
            // System.out.println(degree);
            
            double turn= degree;
            turn=Math.toRadians(turn);
            double turn_sin=Math.sin(turn);
            double turn_cos=Math.cos(turn);
            turret_pos[0]=(float)(init_cord_x_turret+(15*turn_sin));
            // System.out.print(turret_pos[1]-15*turn_cos);
            turret_pos[1]=(float)(init_cord_y_turret+(15-15*turn_cos));
                }
    else if(degree>=90){
        degree=90;
        double turn= degree;
        turn=Math.toRadians(turn);
        double turn_sin=Math.sin(turn);
        double turn_cos=Math.cos(turn);
        turret_pos[0]=(float)(init_cord_x_turret+Math.abs(15*turn_sin));
        // System.out.print(turret_pos[1]-15*turn_cos);
        turret_pos[1]=(float)(init_cord_y_turret+(15-15*turn_cos));
        
    }
    else if(degree<=-90){
        degree=-90;
        double turn= degree;
        turn=Math.toRadians(turn);
        double turn_sin=Math.sin(turn);
        double turn_cos=Math.cos(turn);
        turret_pos[0]=(float)(init_cord_x_turret-Math.abs(15*turn_sin));
        // System.out.print(turret_pos[1]-15*turn_cos);
        turret_pos[1]=(float)(init_cord_y_turret+(15-15*turn_cos));
    }
}
    
    public void turret_mov_left(){  //move 30 deg each time
        // float[] temp=turrent_pos;
        degree=degree-171.887339/60;
        process_turret();
    }
    public void turret_mov_right(){  //move 30 deg each time
        // float[] temp=turrent_pos;
        degree=degree+171.887339/60;
        process_turret();
    }
    public void tank_move_left(ArrayList<float[]> list_of_points_terrian){
        if(cord_x>0 &&fuel>0){
        cord_x-=1;
        fuel-=1;
        float[] temp=list_of_points_terrian.get((int)cord_x);
        cord_y=temp[1];
        init_cord_x_turret=cord_x;
        init_cord_y_turret=cord_y-18;

        // set_turret_init(cord_x, cord_y);
        process_turret();
        // System.out.println(degree);
        // System.out.println(turret_pos[0]);
    }
}

    public void tank_move_right(ArrayList<float[]> list_of_points_terrian){
        if(cord_x<864 &&fuel>0){
        cord_x+=1;
        fuel-=1;
        float[] temp=list_of_points_terrian.get((int)cord_x);
        cord_y=temp[1];
        init_cord_x_turret=cord_x;
        init_cord_y_turret=cord_y-18;

        // set_turret_init(cord_x, cord_y);
        process_turret();
        // System.out.println(degree);
       
        
    }
    }
    
    public void check_power(){
        if(power>hp){
            power=hp;
        }
        if(power<0){
            power=0;
        }
    }

    
    public void power_increase(){
        
        power+=1;
        check_power();
    }
    public void power_decrease(){
        power-=1;
        check_power();
    }

    
    public void add_fuel(int a){
        fuel+=a;
        
    }
    

    public void draw(App app){
        app.noStroke();
        app.fill(Float.parseFloat(color[0]),Float.parseFloat(color[1]),Float.parseFloat(color[2]));
        app.rect(cord_x-10,cord_y,20,5);
        app.rect(cord_x-7.5f,cord_y-5,15,5);
        
       
        app.stroke(0,0,0);
        app.strokeWeight(3);
        app.line(turret_pos[0], turret_pos[1], cord_x, cord_y-3);
        
        
    }
}
