package Tanks;

import java.util.*;

import processing.core.PImage;

public class Tankpiece {
    private int fuel = 250;
    private int hp = 100;
    private int parachute = 3;
    private float cordX;
    private float cordY;
    private float[] pos;
    private float start;
    private float finish;
    private int power = 50;
    
    private String[] color;
    private char callSign;
    private float[] turretPos = new float[2];

    private double degree = 0;

    private double initCordXTurret;
    private double initCordYTurret;
    private Projectile projectile;

    private boolean parachuteExpand;
    
    public boolean ifExecute=true;
    public boolean ifIterate=true;

    private boolean projSize=true;
    public boolean tankFall=false;
   

    private int point;

    public Tankpiece(float cordX, float cordY, String color_String, char callSign, int point,int parachute) {
        setCordx(cordX);
        setCordy(cordY);

        String[] temp = colorParser(color_String);
        this.color = temp;
        // set_pos(cord_x, cord_y);
        this.callSign = callSign;
        setTurretInit(cordX, cordY);
        initCordXTurret = cordX;
        initCordYTurret = cordY - 18;
        this.point=point;
        this.parachute=parachute;

        // this.degree=degree;

    }
    public boolean getProjSize(){
        return projSize;
    }
    public void setProjSize(boolean projSize){
        this.projSize=projSize;
    }

    public void setCordx(float cordX) {
        this.cordX = cordX;
    }

    public void setCordy(float cordY) {
        this.cordY = cordY;
    }

    public void setTurretInit(float cordX, float cordY) {
        float[] pos = { cordX, cordY - 18 };

        this.turretPos = pos;

    }

    // public void set_pos(float cordx,float cordy){
    // float[] pos={cordx,cord_y};
    // this.pos =pos;
    // }

    public void setPoint(int point,Tankpiece t) {
        if(point>t.getHp()){
            point=t.getHp();
        }
        // System.out.println("hp: "+this.hp+" point: "+point);
        this.point += point;
    }

public void setHp(int hp){
    this.hp+=hp;
    if(this.hp>100){
        this.hp=100;
    }
    if(this.hp<0){
        this.hp=0;
    }
    checkPower();
}
public void setParachute(int parachute){
    this.parachute+=parachute;
}

public void setProjectile(Projectile projectile){
    this.projectile=projectile;
}




    public float getCordX() {
        return cordX;
    }

    public float getCordY() {
        return cordY;
    }

    public int getPoint() {
        return point;
    }

    // public float[] get_pos(){

    // return pos;
    // }
    public char getCallSign() {
        return callSign;
    }

    public String[] getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public int getFuel() {
        return fuel;
    }

    public int getParachute() {
        return parachute;
    }

    public int getHp() {

        return hp;
    }

    public double getDegree() {
        return degree;
    }

    public float[] getTurretPos() {
        return turretPos;
    }

    public String[] colorParser(String color) {
        String[] temp = color.split(",");
        return temp;
    }

    // public float[] turrent_pos(){

    // }
    public void processTurret() {
        if (Math.abs(degree) < 90) {
            // System.out.println(degree);

            double turn = degree;
            turn = Math.toRadians(turn);
            double turn_sin = Math.sin(turn);
            double turn_cos = Math.cos(turn);
            turretPos[0] = (float) (initCordXTurret + (15 * turn_sin));
            // System.out.print(turret_pos[1]-15*turn_cos);
            turretPos[1] = (float) (initCordYTurret + (15 - 15 * turn_cos));
        } else if (degree >= 90) {
            degree = 90;
            double turn = degree;
            turn = Math.toRadians(turn);
            double turn_sin = Math.sin(turn);
            double turn_cos = Math.cos(turn);
            turretPos[0] = (float) (initCordXTurret + Math.abs(15 * turn_sin));
            // System.out.print(turret_pos[1]-15*turn_cos);
            turretPos[1] = (float) (initCordYTurret + (15 - 15 * turn_cos));

        } else if (degree <= -90) {
            degree = -90;
            double turn = degree;
            turn = Math.toRadians(turn);
            double turn_sin = Math.sin(turn);
            double turn_cos = Math.cos(turn);
            turretPos[0] = (float) (initCordXTurret - Math.abs(15 * turn_sin));
            // System.out.print(turret_pos[1]-15*turn_cos);
            turretPos[1] = (float) (initCordYTurret + (15 - 15 * turn_cos));
        }
    }

    public void turretMovLeft() { // move 30 deg each time
        // float[] temp=turrent_pos;
        degree = degree - 171.887339 / 60;
        processTurret();
    }

    public void turretMovRight() { // move 30 deg each time
        // float[] temp=turrent_pos;
        degree = degree + 171.887339 / 60;
        processTurret();
    }

    public void tankMoveLeft(ArrayList<float[]> list_of_points_terrian) {
        if (cordX > 0 && fuel > 0) {
            cordX -= 1;
            fuel -= 1;
            float[] temp = list_of_points_terrian.get((int) cordX);
            cordY = temp[1];
            initCordXTurret = cordX;
            initCordYTurret = cordY - 18;

            // set_turret_init(cord_x, cord_y);
            processTurret();
            // System.out.println(degree);
            // System.out.println(turret_pos[0]);
        }
    }

    public void tankMoveRight(ArrayList<float[]> list_of_points_terrian) {
        if (cordX < 864 && fuel > 0) {
            cordX += 1;
            fuel -= 1;
            float[] temp = list_of_points_terrian.get((int) cordX);
            cordY = temp[1];
            initCordXTurret = cordX;
            initCordYTurret = cordY - 18;

            // set_turret_init(cord_x, cord_y);
            processTurret();
            // System.out.println(degree);

        }
    }

    public void checkPower() {
        if (power > hp) {
            power = hp;
        }
        if (power < 0) {
            power = 0;
        }
    }

    public void powerIncrease() {

        power += 1;
        checkPower();
    }

    public void powerDecrease() {
        power -= 1;
        checkPower();
    }

    public void addFuel(int a) {
        fuel += a;

    }
    private int dmgSum=0;
    public void tankCheck(ArrayList<float[]> list_of_points_terrian) {
        if (cordY < list_of_points_terrian.get((int) cordX)[1]) {
            tankFall=true;
            if (parachute > 0 ||parachuteExpand) {
                if (!parachuteExpand) {
                    parachuteExpand = true;
                    parachute--;
                    
                }
                cordY += 1;
                initCordXTurret = cordX;
                initCordYTurret = cordY - 18;
                processTurret();
            } else {
                cordY += 2;
                dmgSum+=2;
                initCordXTurret = cordX;
                initCordYTurret = cordY - 18;
                processTurret();
            }
        } else {
            tankFall=false;
            if(projectile!=null){
                System.out.println("this is called in tankpiece");
                projectile.getBelonging().setPoint(dmgSum,this);
                setHp(-dmgSum);
            }
            dmgSum=0;
            projectile=null;
            parachuteExpand = false;
            
        }
    }
    

    public void draw(App app, ArrayList<float[]> list_of_points_terrian, PImage img_parachute,Tankpiece currentPlayer) {
        
            tankCheck(list_of_points_terrian);
            

            app.stroke(0, 0, 0);
            app.strokeWeight(4);
            app.line(turretPos[0], turretPos[1], cordX, cordY - 3);

            app.noStroke();
            app.fill(Float.parseFloat(color[0]), Float.parseFloat(color[1]), Float.parseFloat(color[2]));
            app.rect(cordX - 10, cordY, 20, 5);
            app.rect(cordX - 7.5f, cordY - 5, 15, 5);

            if (parachuteExpand) {
                app.image(img_parachute, cordX - 10 - 5, cordY - 35, 32, 32);
            }
            if(this==currentPlayer){
                finish=System.nanoTime();
                if(ifExecute){
                    app.stroke(0, 0, 0);
                    app.strokeWeight(2f);
                    app.line(cordX, cordY-100, cordX, cordY -50);
                    app.line(cordX-10,cordY-70,cordX, cordY -50);
                    app.line(cordX+10,cordY-70,cordX, cordY -50);
                    if(ifIterate){
                        start=System.nanoTime();
                    }
                    ifIterate=false;
                    
                }
                if(finish - start > 2 * 1000000000){
                    ifExecute=false;
                }

            }

    
}
}
