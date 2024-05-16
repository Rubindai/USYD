package Tanks;

import java.util.*;

import processing.core.PImage;

public class Tankpiece {
    public int fuel = 250;
    public int hp = 100;
    public int parachute = 3;
    private float cordX;
    private float cordY;

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

    public boolean ifExecute = true;
    public boolean ifIterate = true;

    private boolean projSize = true;
    public boolean tankFall = false;
    public String coloString;

    public int point;

    /**
     * Constructor for Tankpiece
     * 
     * @param cordX
     * @param cordY
     * @param colorString
     * @param callSign
     * @param point
     * @param parachute
     */
    public Tankpiece(float cordX, float cordY, String colorString, char callSign, int point, int parachute) {
        setCordx(cordX);
        setCordy(cordY);
        this.coloString = colorString;
        String[] temp = colorParser(colorString);
        this.color = temp;

        this.callSign = callSign;
        setTurretInit(cordX, cordY);
        initCordXTurret = cordX;
        initCordYTurret = cordY - 18;
        this.point = point;
        this.parachute = parachute;

    }

    public boolean getProjSize() {
        return projSize;
    }

    public void setProjSize(boolean projSize) {
        this.projSize = projSize;
    }

    public void setCordx(float cordX) {
        this.cordX = cordX;
    }

    public void setCordy(float cordY) {
        this.cordY = cordY;
    }

    /**
     * Set the initial position of turret
     * 
     * @param cordX x coordinate of the turret
     * @param cordY y coordinate of the turret
     */
    public void setTurretInit(float cordX, float cordY) {
        float[] pos = { cordX, cordY - 18 };

        this.turretPos = pos;

    }

    /**
     * Set the point the tank received
     * 
     * @param point point gained by the player
     * @param t     t of type Tankpiece
     */
    public void setPoint(int point, Tankpiece t) {
        if (point > t.getHp()) {
            point = t.getHp();
        }
        // System.out.println("hp: "+this.hp+" point: "+point);
        this.point += point;
    }

    /**
     * Set the hp of the tank
     * 
     * @param hp an integer hp
     */
    public void setHp(int hp) {
        this.hp += hp;
        if (this.hp > 100) {
            this.hp = 100;
        }
        if (this.hp < 0) {
            this.hp = 0;
        }
        checkPower();
    }

    /**
     * Set the number of parachute
     * 
     * @param parachute the number of parachute
     */
    public void setParachute(int parachute) {
        this.parachute += parachute;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
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

    /**
     * A color parser that convert String color to a list of string
     * 
     * @param color a string of color
     * @return a list of string
     */
    public String[] colorParser(String color) {
        String[] temp = color.split(",");
        return temp;
    }

    /**
     * Process turret movement to make sure its relevant position
     */
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

    /**
     * Process turret movement when turret moves to left
     */
    public void turretMovLeft() { // move 30 deg each time

        degree = degree - 171.887339 / 60;
        processTurret();
    }

    /**
     * Process turret movement when turret moves to right
     */
    public void turretMovRight() { // move 30 deg each time

        degree = degree + 171.887339 / 60;
        processTurret();
    }

    /**
     * Process tank movement when tank moves to left
     * 
     * @param listOfPointsTerrain an arraylist of type list of float
     */
    public void tankMoveLeft(ArrayList<float[]> listOfPointsTerrain) {
        if (cordX > 0 && fuel > 0) {
            cordX -= 1;
            fuel -= 1;
            float[] temp = listOfPointsTerrain.get((int) cordX);
            cordY = temp[1];
            initCordXTurret = cordX;
            initCordYTurret = cordY - 18;

            processTurret();

        }
    }

    /**
     * Process tank movement when tank moves to left
     * 
     * @param listOfPointsTerrain an arraylist of type list of float
     *
     */
    public void tankMoveRight(ArrayList<float[]> listOfPointsTerrain) {
        if (cordX < 864 && fuel > 0) {
            cordX += 1;
            fuel -= 1;
            float[] temp = listOfPointsTerrain.get((int) cordX);
            cordY = temp[1];
            initCordXTurret = cordX;
            initCordYTurret = cordY - 18;

            // set_turret_init(cord_x, cord_y);
            processTurret();
            // System.out.println(degree);

        }
    }

    /**
     * Check power level to make sure it's reasonable
     */
    public void checkPower() {
        if (power > hp) {
            power = hp;
        }
        if (power < 0) {
            power = 0;
        }
    }

    /**
     * Process power level when power increase
     */
    public void powerIncrease() {

        power += 1;
        checkPower();
    }

    /**
     * Process power level when power decrease
     */
    public void powerDecrease() {
        power -= 1;
        checkPower();
    }

    /**
     * Add fuel by amount a
     * 
     * @param a an integer a
     */
    public void addFuel(int a) {
        fuel += a;

    }

    private int dmgSum = 0;

    /**
     * Check tank position
     * 
     * @param listOfPointsTerrain an arraylist consists of terrain coordinates
     */
    public void tankCheck(ArrayList<float[]> listOfPointsTerrain) {
        // System.out.println(listOfPointsTerrain.size()+"cordx: "+cordX);
        if (cordY < listOfPointsTerrain.get((int) cordX)[1]) {
            tankFall = true;
            if (parachute > 0 || parachuteExpand) {
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
                dmgSum += 2;
                initCordXTurret = cordX;
                initCordYTurret = cordY - 18;
                processTurret();
            }
        } else {
            tankFall = false;
            if (projectile != null) {
                // System.out.println("this is called in tankpiece");
                projectile.getBelonging().setPoint(dmgSum, this);
                setHp(-dmgSum);
            }
            dmgSum = 0;
            projectile = null;
            parachuteExpand = false;

        }
    }

    /**
     * Draw tank
     * 
     * @param app                 app from App
     * @param listOfPointsTerrain an arraylist consists of terrain coordinates
     * @param img_parachute       parachute image
     * @param currentPlayer       currentPlayer of the game, type Tankpiece
     */
    public void draw(App app, ArrayList<float[]> listOfPointsTerrain, PImage img_parachute, Tankpiece currentPlayer) {

        tankCheck(listOfPointsTerrain);

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
        if (this == currentPlayer) {
            finish = System.nanoTime();
            if (ifExecute) {
                app.stroke(0, 0, 0);
                app.strokeWeight(2f);
                app.line(cordX, cordY - 100, cordX, cordY - 50);
                app.line(cordX - 10, cordY - 70, cordX, cordY - 50);
                app.line(cordX + 10, cordY - 70, cordX, cordY - 50);
                if (ifIterate) {
                    start = System.nanoTime();
                }
                ifIterate = false;

            }
            if (finish - start > 2 * 1000000000) {
                ifExecute = false;
            }

        }

    }
}
