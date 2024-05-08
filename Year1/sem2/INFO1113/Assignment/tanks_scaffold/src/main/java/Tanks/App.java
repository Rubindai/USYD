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

    public static final int CELLSIZE = 32; // 8;
    public static final int CELLHEIGHT = 32;

    public static final int CELLAVG = 32;
    public static final int TOPBAR = 0;
    public static int WIDTH = 864; // CELLSIZE*BOARD_WIDTH;
    public static int HEIGHT = 640; // BOARD_HEIGHT*CELLSIZE+TOPBAR;
    public static final int BOARD_WIDTH = WIDTH / CELLSIZE;
    public static final int BOARD_HEIGHT = 20;

    public static final int INITIAL_PARACHUTES = 1;

    public static final int FPS = 30;

    public String configPath;

    public static Random random = new Random();
    private PImage img;
    private PImage imgTree;
    private PImage imgWindLeft;
    private PImage imgWindRight;
    private PImage imgFuel;
    private PImage imgParachute;
    private JSONArray jsonArray;
    private JSONObject jsonObj;
    private String layout;
    private String background;
    private String foregroundColour;
    private String trees;
    private long start;
    private long finish;
    private long duration;

    private ArrayList<float[]> listOfPointsTerrian = new ArrayList<float[]>();
    private ArrayList<float[]> listOfPointsTree = new ArrayList<float[]>();
    private ArrayList<Tankpiece> listOfTankpieces = new ArrayList<Tankpiece>();
    private ArrayList<Tankpiece> listOfTankpiecesFinal = new ArrayList<Tankpiece>();
    private ArrayList<Projectile> listOfProjectiles = new ArrayList<Projectile>();
    public ArrayList<Explosion> listOfExplosions = new ArrayList<Explosion>();
    private Tankpiece currentPlayer;
    private boolean turretMovLeft;
    private boolean turretMovRight;
    private boolean tankMoveLeft;
    private boolean tankMoveRight;
    private boolean wPress;
    private boolean sPress;
    private boolean init;
    private boolean ifExecute = true;
    private boolean gameEnd;

    Wind wind;
    private int level = 0;
    private int total_level;
    private int currentPlayerIndex;

    private UI ui;

    // Feel free to add any additional methods or attributes you want. Please put
    // classes in different files.

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
     * Load all resources such as images. Initialise the elements such as the player
     * and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        reset();
        init = true;
        

        // See PApplet javadoc:
        // loadJSONObject(configPath)
        // loadImage(this.getClass().getResource(filename).getPath().toLowerCase(Locale.ROOT).replace("%20",
        // " "));
    }

    public void reset() {
        img = null;
        imgTree = null;
        jsonArray = null;
        jsonObj = null;
        layout = null;
        background = null;
        foregroundColour = null;
        trees = null;
        listOfPointsTerrian = new ArrayList<float[]>();
        listOfPointsTree = new ArrayList<float[]>();
        listOfProjectiles = new ArrayList<Projectile>();
        listOfExplosions = new ArrayList<Explosion>();
        // list_of_Tankpieces=new ArrayList<Tankpiece>();
        currentPlayer = null;
        jsonObj = loadJSONObject(configPath);
        currentPlayerIndex = 0;

        jsonArray = jsonObj.getJSONArray("levels");
        total_level = jsonArray.size();

        layout = jsonArray.getJSONObject(level).getString("layout");
        // System.out.println(layout);
        background = jsonArray.getJSONObject(level).getString("background");
        foregroundColour = jsonArray.getJSONObject(level).getString("foreground-colour");
        trees = jsonArray.getJSONObject(level).getString("trees");
        if (trees == null) {
            trees = "tree2.png";
        }
        img = loadImage("src/main/resources/Tanks/" + background);
        imgTree = loadImage("src/main/resources/Tanks/" + trees);
        imgWindLeft = loadImage("src/main/resources/Tanks/wind-1.png");
        imgWindRight = loadImage("src/main/resources/Tanks/wind.png");
        imgFuel = loadImage("src/main/resources/Tanks/fuel.png");
        imgParachute = loadImage("src/main/resources/Tanks/parachute.png");

        File fIn;

        Scanner scan = null;
        Scanner scan2 = null;
        Scanner scan3 = null;

        try {

            fIn = new File(layout);
            scan = new Scanner(fIn);

            scan2 = new Scanner(fIn);
            scan3 = new Scanner(fIn);

        } catch (FileNotFoundException e) {
            System.out.println("ERROR ON FILE PARSING");
        }
        terrianSetup(scan);
        treeSetup(scan2);
        tankSetup(scan3);
        currentPlayer = listOfTankpieces.get(0);
        wind = new Wind(imgWindLeft, imgWindRight);
        ui = new UI(listOfTankpiecesFinal);

    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (key == CODED && !gameEnd) {
            if (keyCode == UP && !gameEnd &&!currentPlayer.tankFall) {
                System.out.println("UP"); // turrent move +-3 rand
                turretMovLeft = true;

            }
            if (keyCode == DOWN && !gameEnd&&!currentPlayer.tankFall) {
                System.out.println("DOWN");
                turretMovRight = true;

            }
            if (keyCode == LEFT && !gameEnd&&!currentPlayer.tankFall) {
                System.out.println("LEFT");// tank move +-60 pixel
                tankMoveLeft = true;

            }
            if (keyCode == RIGHT && !gameEnd&&!currentPlayer.tankFall) {
                System.out.println("RIGHT");
                tankMoveRight = true;

            }

        }

        if (key == ' ' && !gameEnd&&!currentPlayer.tankFall) {

            if (ifExecute) {
                System.out.println("Space");// fire

                // if(list_of_Tankpieces.size()>0){
                // list_of_Tankpieces_final.add(list_of_Tankpieces.get(0));
                // list_of_Tankpieces.remove(0);
                // System.out.println(list_of_Tankpieces_final.get(0).get_call_sign());

                if (currentPlayer.getTurretPos()[0] >= 0) {
                    if (listOfPointsTerrian.get((int) (currentPlayer.getTurretPos()[0]))[1] > currentPlayer
                            .getTurretPos()[1]) {
                        // EDGE CASE ATTENTION
                        Projectile p = new Projectile(currentPlayer, wind, listOfPointsTerrian,
                                currentPlayer.getProjSize());
                        listOfProjectiles.add(p);
                        wind.randomTurn();
                        currentPlayer.setProjSize(true);
                        playerSwitch();

                    } else {
                        System.out.println("INVALID TURRET POSITION");
                    }

                } else {
                    System.out.println("INVALID TURRET POSITION");
                }
                start = System.nanoTime();
                ifExecute = false;
            }
            finish = System.nanoTime();
            if (finish - start > 0.05 * 1000000000) {
                ifExecute = true;
            }

            // }
        }
        if (Character.toUpperCase(key) == 'S' && !gameEnd&&!currentPlayer.tankFall) {
            System.out.println("S");
            sPress = true;

        }
        if (Character.toUpperCase(key) == 'W' && !gameEnd&&!currentPlayer.tankFall) {
            System.out.println("W");
            wPress = true;

        }
        if (Character.toUpperCase(key) == 'F' && !gameEnd&&!currentPlayer.tankFall) {
            if (currentPlayer.getPoint() >= 10) {
                currentPlayer.setPoint(-10, currentPlayer);
                currentPlayer.addFuel(100);
            }

        }
        if (Character.toUpperCase(key) == 'R'&&!currentPlayer.tankFall) {
            if (!gameEnd) {
                if (currentPlayer.getPoint() >= 20) {
                    currentPlayer.setPoint(-20, currentPlayer);
                    currentPlayer.setHp(20);
                }
            } else {
                init = false;
                gameEnd = false;
                level = 0;
                reset();
            }

        }
        if (Character.toUpperCase(key) == 'X' && !gameEnd&&!currentPlayer.tankFall) {
            // if (currentPlayer.getPoint() >= 20) {
            // currentPlayer.setPoint(-20, currentPlayer);
            // currentPlayer.setProjSize(false);

            // }
            currentPlayer.setProjSize(false);

        }
        if (Character.toUpperCase(key) == 'P' && !gameEnd&&!currentPlayer.tankFall) {
            if (currentPlayer.getPoint() >= 15) {
                currentPlayer.setPoint(-15, currentPlayer);
                currentPlayer.setParachute(1);
            }

        }

    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased() {
        if (key == CODED ) {
            if (keyCode == UP) {
                System.out.println("UP"); // turrent move +-3 rand
                turretMovLeft = false;

            }
            if (keyCode == DOWN ) {
                System.out.println("DOWN");
                turretMovRight = false;

            }
            if (keyCode == LEFT ) {
                System.out.println("LEFT");// tank move +-60 pixel
                tankMoveLeft = false;

            }
            if (keyCode == RIGHT ) {
                System.out.println("RIGHT");
                tankMoveRight = false;

            }
        }
        if (Character.toUpperCase(key) == 'S' ) {

            sPress = false;

        }
        if (Character.toUpperCase(key) == 'W' ) {

            wPress = false;

        }
        if (key == ' ' ) {
            ifExecute = true;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO - powerups, like repair and extra fuel and teleport
        System.out.println(e.getX());
        System.out.println(e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Draw all elements in the game by current frame.
     */

    public void terrianSetup(Scanner scan) {
        String fInput;

        char charOfF;
        float yCord = 0;
        float xCord = 0;

        while (scan.hasNextLine()) {
            fInput = scan.nextLine();
            int i = 0;

            while (i < fInput.length()) { // The file contain spaces which is 28*20
                charOfF = fInput.charAt(i);
                if (charOfF == 'X') {
                    xCord = i * 32;

                    for (int k = 0; k < 32; k++) {
                        float[] cord = new float[2];
                        cord[0] = xCord;
                        cord[1] = yCord * 32;
                        listOfPointsTerrian.add(cord);
                        xCord += 1;
                    }
                }

                i += 1;
            }
            yCord += 1;
        }
        Terrian t = new Terrian();

        listOfPointsTerrian = t.sortFromLeftToRight(listOfPointsTerrian);
        listOfPointsTerrian = t.moveAverage(listOfPointsTerrian);
        listOfPointsTerrian = t.moveAverage(listOfPointsTerrian);
    }

    public void treeSetup(Scanner scan) {
        String fInput;

        char charOfF;
        float yCord = 0;

        while (scan.hasNextLine()) {
            fInput = scan.nextLine();
            int i = 0;

            while (i < fInput.length()) { // The file contain spaces which is 28*20
                charOfF = fInput.charAt(i);

                if (charOfF == 'T') {

                    float[] cord = new float[2];
                    int min = -15;
                    int max = 15;

                    int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    cord[0] = i * 32 + random_int;
                    if (cord[0] < 0) {
                        cord[0] = 0;
                    }
                    if (cord[0] > 864) {
                        cord[0] = 864;
                    }
                    float[] temp = listOfPointsTerrian.get((int) cord[0]);
                    yCord = temp[1] - 32;
                    cord[1] = yCord;
                    listOfPointsTree.add(cord);
                }

                i += 1;
            }
            yCord += 1;
        }

    }

    public void tankSetup(Scanner scan) {
        String fInput;

        JSONObject jsonObj1;
        char charOfF;
        float yCord = 0;
        jsonObj1 = jsonObj.getJSONObject("player_colours");
        ArrayList<Tankpiece> tempArrayList = new ArrayList<Tankpiece>();
        int y = 0; // debug purpose

        while (scan.hasNextLine()) {
            fInput = scan.nextLine();
            int i = 0;
            y++;

            while (i < fInput.length()) { // The file contain spaces which is 28*20
                charOfF = fInput.charAt(i);
                // String c = String.valueOf(charOfF).trim();
                if (charOfF != 'T' && charOfF != 'X' && charOfF != 'T' && charOfF != 'X'
                        && !String.valueOf(charOfF).trim().equals("")) { // WEIRD char_of_f==' '
                    // System.out.println(char_of_f); debug
                    float[] cord = new float[2];

                    cord[0] = i * 32;

                    float[] temp = listOfPointsTerrian.get((int) cord[0]);
                    yCord = temp[1];
                    cord[1] = yCord;
                    String color = jsonObj1.getString(String.valueOf(charOfF));

                    if (init == false) {
                        Tankpiece a = new Tankpiece(cord[0], cord[1], color, charOfF, 0, 3);
                        tempArrayList.add(a);
                    } else {
                        int p = 0;
                        int parachute = 0;
                        for (Tankpiece t : listOfTankpiecesFinal) {
                            if (t.getCallSign() == charOfF) {
                                p = t.getPoint();
                                parachute = t.getParachute();
                            }
                        }
                        Tankpiece a = new Tankpiece(cord[0], cord[1], color, charOfF, p, parachute);
                        tempArrayList.add(a);
                    }

                }

                i += 1;
            }
            yCord += 1;
        }
        listOfTankpieces = sortTank(tempArrayList);
        listOfTankpiecesFinal = new ArrayList<Tankpiece>(listOfTankpieces);

    }

    public ArrayList<Tankpiece> sortTank(ArrayList<Tankpiece> list) {
        ArrayList<Tankpiece> new_ArrayList = new ArrayList<Tankpiece>();
        while (list.size() > 0) {
            Tankpiece minArray = list.get(0);
            char minNum = minArray.getCallSign();

            for (int i = 1; i < list.size(); i++) {
                Tankpiece tempArray = list.get(i);
                char tempNum = tempArray.getCallSign();
                if (tempNum < minNum) {
                    minArray = tempArray;
                    minNum = tempNum;
                }

            }
            new_ArrayList.add(minArray);
            list.remove(list.indexOf(minArray));

        }

        return new_ArrayList;
    }

    public void processMove() {
        if (turretMovLeft && !turretMovRight) {
            currentPlayer.turretMovLeft();
        }
        if (turretMovRight && !turretMovLeft) {
            currentPlayer.turretMovRight();
        }
        if (tankMoveLeft && !tankMoveRight) {
            currentPlayer.tankMoveLeft(listOfPointsTerrian);
        }
        if (tankMoveRight && !tankMoveLeft) {
            currentPlayer.tankMoveRight(listOfPointsTerrian);

        }
        if (wPress && !sPress) {
            currentPlayer.powerIncrease();

        }
        if (sPress && !wPress) {
            currentPlayer.powerDecrease();

        }

    }

    public void processProjectile(ArrayList<Projectile> listOfProjectiles) {
        ArrayList<Projectile> listOfProjectilesCopy = new ArrayList<Projectile>(listOfProjectiles);
        for (Projectile projectile : listOfProjectilesCopy) {
            if (projectile.getIfMove()) {
                projectile.projectileMove();

            }
            if (projectile.getCordX() < -15 || projectile.getCordX() > 880 || projectile.getCordY() > 700) {
                System.out.println("triggered");
                listOfProjectiles.remove(projectile);
                continue;
            }

            if (!projectile.getIsInit()) {

                projectile.clash();
            }

        }

    }

    public void playerSwitch() {
        if (currentPlayerIndex >= listOfTankpieces.size() - 1) {
            currentPlayerIndex = -1;
        }
        currentPlayerIndex += 1;

        // System.out.println("index:"+current_level+"
        // size:"+list_of_Tankpieces.size());
        if (listOfTankpieces.size() > 0) {
            currentPlayer = listOfTankpieces.get(currentPlayerIndex);
            currentPlayer.ifIterate = true;
            currentPlayer.ifExecute = true;
        }

        if (currentPlayerIndex >= listOfTankpieces.size() - 1) {
            currentPlayerIndex = -1;
        }
    }

    @Override
    public void draw() {
        image(img, 0, 0);

        Terrian t = new Terrian();

        t.draw(this, listOfPointsTerrian, listOfPointsTree, imgTree, foregroundColour);

        processMove();
        for (Tankpiece tank : listOfTankpieces) {
            tank.draw(this, listOfPointsTerrian, imgParachute, currentPlayer);
        }

        processProjectile(listOfProjectiles);

        for (Projectile projectile : listOfProjectiles) {
            if (projectile.getIfMove()) {
                projectile.draw(this);
            }

        }

        for (Projectile projectile : listOfProjectiles) {
            if (projectile.projectileSmall()) {
                if (!projectile.getIfMove() && !projectile.toRemove) {
                    Explosion e = new Explosion(projectile.getCordX(), projectile.getCordY(), 3);
                    listOfExplosions.add(e);
                    projectile.toRemove = true;
                }
            } else {
                if (!projectile.getIfMove() && !projectile.toRemove) {
                    Explosion e = new Explosion(projectile.getCordX(), projectile.getCordY(), 6);
                    listOfExplosions.add(e);
                    projectile.toRemove = true;
                }

            }
        }
        ArrayList<Explosion> list_of_Explosions_copy = new ArrayList<Explosion>(listOfExplosions);
        for (Explosion e : list_of_Explosions_copy) {
            if (e.status) {
                listOfExplosions.remove(e);
            }
            e.explode(this);
        }
        ArrayList<Projectile> listOfProjectilesCopy = new ArrayList<Projectile>(listOfProjectiles);

        for (Projectile projectile : listOfProjectilesCopy) {
            if (projectile.projectileSmall()) {
                if (projectile.toRemove) {

                    Terrian.terrainDamageProj(listOfPointsTerrian, listOfTankpieces, projectile, 30);

                    listOfProjectiles.remove(projectile);

                    DamageCalculation.projectileDamage(listOfTankpieces, projectile, 30);
                }

            } else {
                if (projectile.toRemove) {
                    // System.out.println("Enter");

                    Terrian.terrainDamageProj(listOfPointsTerrian, listOfTankpieces, projectile, 60);

                    listOfProjectiles.remove(projectile);

                    DamageCalculation.projectileDamage(listOfTankpieces, projectile, 60);
                }
            }

        }

        wind.draw(this);

        ui.draw(this, imgFuel, imgParachute, currentPlayer, currentPlayer.getProjSize(), gameEnd);

        ArrayList<Tankpiece> listOfTankpiecesCopy = new ArrayList<Tankpiece>(listOfTankpieces);

        for (Tankpiece tmp : listOfTankpiecesCopy) {
            // System.out.println(tmp.get_cord_y());
            if (tmp.getHp() == 0) {

                Explosion ep = new Explosion(tmp.getCordX(), tmp.getCordY(), 1.5f);
                listOfExplosions.add(ep);
                listOfTankpieces.remove(tmp);

                Terrian.terrainDamageTank(listOfPointsTerrian, tmp, 15);

                DamageCalculation.tankDamage(listOfTankpiecesCopy, tmp, 15);

                if (tmp == currentPlayer) {
                    playerSwitch();
                    continue;
                }

            } else if (tmp.getCordY() >= 640) {

                Explosion ep = new Explosion(tmp.getCordX(), tmp.getCordY(), 3);
                listOfExplosions.add(ep);
                listOfTankpieces.remove(tmp);

                Terrian.terrainDamageTank(listOfPointsTerrian, tmp, 30);

                DamageCalculation.tankDamage(listOfTankpiecesCopy, tmp, 30);

                if (tmp == currentPlayer) {
                    playerSwitch();
                }

            }

        }
        if (listOfTankpieces.size() <= 1 && !gameEnd) {

            level += 1;
            if (level == total_level) {
                gameEnd = true;
            }
            if (!gameEnd) {
                reset();
            }
        }

        if (gameEnd) {
            ui.endGameUI(this);

        }

    }

    public static void main(String[] args) {
        PApplet.main("Tanks.App");
    }

}
