package Tanks;

import java.util.ArrayList;
import processing.core.PImage;
public class UI {
    private Tankpiece current;
    private ArrayList<Tankpiece> list_of_Tankpieces;
    public UI(Tankpiece current,ArrayList<Tankpiece> list_of_Tankpieces){
        this.current=current;
        this.list_of_Tankpieces=list_of_Tankpieces;

    }
    public void draw(App app,PImage fuel_img,PImage parachute_img ){
        app.textSize(18);
        app.text("Player "+current.get_call_sign()+"'s turn", 15, 25);
        
        fuel_img.resize(25,25);
        app.image(fuel_img, 160, 5);
        app.text(current.get_fuel(), 190, 25); //disp fuel
        
        parachute_img.resize(25, 25);
        app.image(parachute_img, 160, 35);
        app.text(current.get_parachute(), 195, 55); //disp parachute
        
        app.noFill();
        app.strokeWeight(3);
        app.rect(435,8,102*1.5f,25); //disp hp outline
        
        

        app.fill(Float.parseFloat(current.get_color()[0]),Float.parseFloat(current.get_color()[1]),Float.parseFloat(current.get_color()[2]));
        app.noStroke();
        app.rect(437, 10.25f, current.get_hp()*1.5f, 21.5f);// draw hp rect
        

        app.strokeWeight(5);
        app.stroke(169,169,169);
        app.rect(435, 9.5f, current.get_power()*1.5f, 25);//disp power range on hp

        
        
        app.stroke(255,0,0);//draw power line
        app.strokeWeight(1);
        app.line(435+current.get_power()*1.5f, 4, 435+current.get_power()*1.5f, 40);

        app.fill(0);
        app.text("Health:", 365, 25);
        app.text(current.get_hp(), 445+100*1.5f, 27.5f);
        app.text("Power:", 365, 52f);
        app.text(current.get_power(), 430, 52.5f);

        //scoreboard
        app.noFill();
        app.stroke(0);
        app.strokeWeight(4);
        app.rect(715, 52.25f, 140, list_of_Tankpieces.size()*20+25);
        app.rect(715, 52.25f, 140, 20f);
      
        app.text("Scores", 720, 69.5f);
        int i=0;
        for(Tankpiece t: list_of_Tankpieces){
            
            app.fill(Float.parseFloat(t.get_color()[0]),Float.parseFloat(t.get_color()[1]),Float.parseFloat(t.get_color()[2]));
            app.text("Player "+t.get_call_sign(),720,90+i*20);
            i+=1;
            app.fill(0);
            app.text(t.get_point(), 820, 70+i*20);
            
            // if(i==2){
            //     break;
            // }

        }



        
        
    }


    
}
