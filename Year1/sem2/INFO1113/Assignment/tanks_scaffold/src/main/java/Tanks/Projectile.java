package Tanks;

import java.util.*;

public class Projectile {
    private float cordX;
    private float cordY;
    private float cordXInit;
    private float cordYInit;
    private Tankpiece current;
    private float length;
    private double degree;
    private int power;
    private float yVal;
    private float xVal;
    private Wind w;
    private boolean isInit = true;
    private boolean projSmall = true;
    private boolean ifMove = true;
    public boolean toRemove=false;
  
    

    // private ArrayList<Tankpiece> list_of_Tankpieces;
    private ArrayList<float[]> listOfPointsTerrian;

    public Projectile(Tankpiece t, Wind w,
            ArrayList<float[]> listOfPointsTerrian,boolean projSmall) {
        this.current = t;
        this.cordX = t.getCordX();
        this.cordY = t.getCordY();
        this.cordXInit = t.getCordX();
        this.cordYInit = t.getCordY() - 3;
        this.degree = t.getDegree();
        this.power = t.getPower();
        this.w = w;
        // this.list_of_Tankpieces = list_of_Tankpieces;
        this.listOfPointsTerrian = listOfPointsTerrian;
        this.projSmall=projSmall;
    }

    public void projectileInit() {

        if (isInit) {

            double turn = degree;
            // System.out.println(degree);
            turn = Math.toRadians(turn);
            double turnSin = Math.sin(turn);
            double turnCos = Math.cos(turn);
            yVal = (float) ((2 + (4 * power / 25)) * turnCos);
            xVal = (float) ((2 + (4 * power / 25)) * turnSin);

            length += (1 + (2 * power / 25))*2;

            cordX = (float) (turnSin * length + cordXInit);
            cordY = (float) (cordYInit - turnCos * length);
            // System.out.println(length);

            // System.out.println(cord_y);
            if (length >= 15) {
                isInit = false;
            }

        }

    }
    
    public void projectileMove() {
        projectileInit();
        
        if (!isInit) {
            
            xVal=xVal+w.getWindspeed()*0.03f/30;
               
            cordX += xVal;
           
            cordY -= yVal;
            yVal = (float) (yVal - (7.2 / 30));
            // System.out.println(cord_x);

        }

    }

    public void clash() {
        // System.out.println("cordx: " + cord_x);
        
        try{
        float[] tmp = listOfPointsTerrian.get((int) cordX);
        // System.out.println("cordx: " + cord_x + " terrain: " + tmp[1]);
     
        if (cordY > tmp[1]) {
            ifMove = false;
        }
    } catch(Exception e){System.out.println(cordY);}

    }

    public boolean projectileSmall() {
        return projSmall;
    }

    public boolean getIfMove() {
        return ifMove;
    }

    public boolean getIsInit() {
        return isInit;
    }

    public float getCordX() {
        return cordX;
    }

    public float getCordY() {
        return cordY;
    }
    public Tankpiece getBelonging(){
        return current;
    }

    public void draw(App app) {

        app.fill(Float.parseFloat(current.getColor()[0]), Float.parseFloat(current.getColor()[1]),
                Float.parseFloat(current.getColor()[2]));
        app.noStroke();
        if (projSmall) {
            app.ellipse(cordX, cordY, 10, 10);
        } else {
            app.ellipse(cordX, cordY, 20, 20);
        }
        app.stroke(0);
        app.fill(0, 0, 0);
        app.ellipse(cordX, cordY, 1, 1);

    }

}
