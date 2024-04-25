package Tanks;
import processing.core.PImage;
public class Wind {
    private int windspeed;
    private PImage wind_left;
    private PImage wind_right;
    public Wind(PImage wind_left,PImage wind_right){
        random_init();
        this.wind_left=wind_left;
        this.wind_right=wind_right;

    }
    public int get_windspeed(){
        return windspeed;
    }

    public void random_init(){
        int min = -35; 
        int max = 35; 
        
        int random_wind_init = (int)Math.floor(Math.random() * (max - min + 1) + min);
        windspeed=random_wind_init;
        
    }

    public void random_turn(){
        int min = -5; 
        int max = 5; 
        
        int random_wind_init = (int)Math.floor(Math.random() * (max - min + 1) + min);
        windspeed+=random_wind_init;

    }
    public void draw(App app){
        // windspeed=10;
        if(windspeed<0){
        wind_left.resize(55,55);
        app.image(wind_left, 760, 0);
        }
        if(windspeed>0){
            wind_right.resize(55,55);
            app.image(wind_right, 760, 0);

        }
        app.fill(0,0,0);



        app.textSize(20);
        app.text(Math.abs(windspeed),820,35);
    }


    
}
