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
    public String background;
    private String foregroundColour;
    private String trees;
    private long start;
    private long finish;
    private long start2;
    private long finish2;

    public ArrayList<float[]> listOfPointsTerrain = new ArrayList<float[]>();
    public ArrayList<float[]> listOfPointsTree = new ArrayList<float[]>();
    public ArrayList<Tankpiece> listOfTankpieces = new ArrayList<Tankpiece>();
    private ArrayList<Tankpiece> listOfTankpiecesFinal = new ArrayList<Tankpiece>();
    public ArrayList<Projectile> listOfProjectiles = new ArrayList<Projectile>();
    public ArrayList<Explosion> listOfExplosions = new ArrayList<Explosion>();
    public Tankpiece currentPlayer;
    public boolean turretMovLeft;
    public boolean turretMovRight;
    public boolean tankMoveLeft;
    public boolean tankMoveRight;
    public boolean wPress;
    public boolean sPress;
    public boolean init;
    private boolean ifExecute = true;
    public boolean gameEnd;
    private boolean ifExecute2 = true;

    public Wind wind;
    public int level = 0;
    private int total_level;
    private int currentPlayerIndex;

    private UI ui;

    // Feel free to add any additional methods or attributes you want. Please put
    // classes in different files.

    public App() {
        this.configPath = "config.json";

    }

    /**
     * Initialize the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);

    }

    /**
     * Load all resources such as images. Initialize the elements such as the player
     * and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);

        reset();

        // See PApplet javadoc:
        // loadJSONObject(configPath)
        // loadImage(this.getClass().getResource(filename).getPath().toLowerCase(Locale.ROOT).replace("%20",
        // " "));
    }

    /**
     * Resetting between levels and reset game when game ends
     */
    public void reset() {
        img = null;
        imgTree = null;
        jsonArray = null;
        jsonObj = null;
        layout = null;
        background = null;
        foregroundColour = null;
        trees = null;
        listOfPointsTerrain = new ArrayList<float[]>();
        listOfPointsTree = new ArrayList<float[]>();
        listOfProjectiles = new ArrayList<Projectile>();
        listOfExplosions = new ArrayList<Explosion>();

        currentPlayer = null;
        jsonObj = loadJSONObject(configPath);
        currentPlayerIndex = 0;

        jsonArray = jsonObj.getJSONArray("levels");
        total_level = jsonArray.size();

        layout = jsonArray.getJSONObject(level).getString("layout");

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
            System.err.println("ERROR ON FILE PARSING");

        }
        terrainSetup(scan);

        treeSetup(scan2);

        tankSetup(scan3);

        currentPlayer = listOfTankpieces.get(0);
        wind = new Wind(imgWindLeft, imgWindRight);
        ui = new UI(listOfTankpiecesFinal);
        ifExecute2 = true;

    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {

        if (keyCode == UP && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("UP"); // turret move +-3 rand
            turretMovLeft = true;

        }
        if (keyCode == DOWN && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("DOWN");
            turretMovRight = true;

        }
        if (keyCode == LEFT && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("LEFT");// tank move +-60 pixel
            tankMoveLeft = true;

        }
        if (keyCode == RIGHT && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("RIGHT");
            tankMoveRight = true;

        }

        if (keyCode == 32 && !gameEnd && !currentPlayer.tankFall) {

            if (ifExecute) {
                System.out.println("Space");// fire

                if (currentPlayer.getTurretPos()[0] >= 0) {
                    if (listOfPointsTerrain.get((int) (currentPlayer.getTurretPos()[0]))[1] > currentPlayer
                            .getTurretPos()[1]) {
                        // EDGE CASE ATTENTION
                        Projectile p = new Projectile(currentPlayer, wind, listOfPointsTerrain,
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

        }
        if (Character.toUpperCase(keyCode) == 'S' && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("S");
            sPress = true;

        }
        if (Character.toUpperCase(keyCode) == 'W' && !gameEnd && !currentPlayer.tankFall) {
            System.out.println("W");
            wPress = true;

        }
        if (Character.toUpperCase(keyCode) == 'F' && !gameEnd && !currentPlayer.tankFall) {
            if (currentPlayer.getPoint() >= 10) {
                currentPlayer.setPoint(-10, currentPlayer);
                currentPlayer.addFuel(200);
            }

        }
        if (Character.toUpperCase(keyCode) == 'R' && !currentPlayer.tankFall) {
            if (!gameEnd) {
                if (currentPlayer.getPoint() >= 20&&currentPlayer.hp<100) {
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
        if (Character.toUpperCase(keyCode) == 'X' && !gameEnd && !currentPlayer.tankFall) {
            if (currentPlayer.getPoint() >= 20) {
                currentPlayer.setPoint(-20, currentPlayer);
                currentPlayer.setProjSize(false);

            }

        }
        if (Character.toUpperCase(keyCode) == 'P' && !gameEnd && !currentPlayer.tankFall) {
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

        if (keyCode == UP) {
            System.out.println("UP"); // turret move +-3 rand
            turretMovLeft = false;

        }
        if (keyCode == DOWN) {
            System.out.println("DOWN");
            turretMovRight = false;

        }
        if (keyCode == LEFT) {
            System.out.println("LEFT");// tank move +-60 pixel
            tankMoveLeft = false;

        }
        if (keyCode == RIGHT) {
            System.out.println("RIGHT");
            tankMoveRight = false;

        }

        if (Character.toUpperCase(keyCode) == 'S') {

            sPress = false;

        }
        if (Character.toUpperCase(keyCode) == 'W') {

            wPress = false;

        }
        if (keyCode == ' ') {
            ifExecute = true;
        }

    }

    /**
     * Setting up terrain read from level files
     * 
     * @param scan scanner object
     */
    public void terrainSetup(Scanner scan) {
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
                        listOfPointsTerrain.add(cord);
                        xCord += 1;
                    }
                }

                i += 1;
            }
            yCord += 1;
        }
        Terrain t = new Terrain();

        listOfPointsTerrain = t.sortFromLeftToRight(listOfPointsTerrain);
        listOfPointsTerrain = t.moveAverage(listOfPointsTerrain);
        listOfPointsTerrain = t.moveAverage(listOfPointsTerrain);
    }

    /**
     * Setting up trees from level files
     * 
     * @param scan scanner object
     */
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
                    float[] temp = listOfPointsTerrain.get((int) cord[0]);
                    yCord = temp[1] - 32;
                    cord[1] = yCord;
                    listOfPointsTree.add(cord);
                }

                i += 1;
            }
            yCord += 1;
        }

    }

    /**
     * Setting up tank from level files
     * 
     * @param scan scanner object
     */
    public void tankSetup(Scanner scan) {
        String fInput;

        JSONObject jsonObj1;
        char charOfF;
        float yCord = 0;
        jsonObj1 = jsonObj.getJSONObject("player_colours");
        ArrayList<Tankpiece> tempArrayList = new ArrayList<Tankpiece>();

        while (scan.hasNextLine()) {
            fInput = scan.nextLine();
            int i = 0;

            while (i < fInput.length()) { // The file contain spaces which is 28*20
                charOfF = fInput.charAt(i);
                // String c = String.valueOf(charOfF).trim();
                if (charOfF != 'T' && charOfF != 'X' 
                        && !String.valueOf(charOfF).trim().equals("")) { // WEIRD char_of_f==' '
                    // System.out.println(char_of_f); debug
                    float[] cord = new float[2];

                    cord[0] = i * 32;

                    float[] temp = listOfPointsTerrain.get((int) cord[0]);
                    yCord = temp[1];
                    cord[1] = yCord;
                    String color = jsonObj1.getString(String.valueOf(charOfF));

                    if (color.equals("random")) {
                        color = "";
                        for (int cont = 0; cont < 3; cont++) {
                            int min = 0;
                            int max = 255;

                            int colorNum = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            String colorString = String.valueOf(colorNum);
                            if (cont < 2) {
                                color += colorString + ",";

                            } else {
                                color += colorString;
                            }
                        }

                    }

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
                                color = t.coloString;
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
        init = true;

    }

    /**
     * Sorting tank using the call sign(in ascending order)
     * 
     * @param list an arraylist consists of Tankpieces
     * @return an arraylist consists of tankpieces in sorted order
     */
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

    /**
     * Processing move according to the boolean value, which is fetched from
     * keyboard input
     */

    public void processMove() {
        if (turretMovLeft && !turretMovRight) {
            currentPlayer.turretMovLeft();
        }
        if (turretMovRight && !turretMovLeft) {
            currentPlayer.turretMovRight();
        }
        if (tankMoveLeft && !tankMoveRight) {
            currentPlayer.tankMoveLeft(listOfPointsTerrain);
        }
        if (tankMoveRight && !tankMoveLeft) {
            currentPlayer.tankMoveRight(listOfPointsTerrain);

        }
        if (wPress && !sPress) {
            currentPlayer.powerIncrease();

        }
        if (sPress && !wPress) {
            currentPlayer.powerDecrease();

        }

    }

    /**
     * Processing projectiles and get projectile status
     * 
     * @param listOfProjectiles an arraylist consists of projectiles
     */
    public void processProjectile(ArrayList<Projectile> listOfProjectiles) {
        ArrayList<Projectile> listOfProjectilesCopy = new ArrayList<Projectile>(listOfProjectiles);
        for (Projectile projectile : listOfProjectilesCopy) {
            if (projectile.getIfMove()) {
                projectile.projectileMove();

            }
            if (projectile.getCordX() < -15 || projectile.getCordX() > 880 || projectile.getCordY() > 700) {
                // System.out.println("triggered");
                listOfProjectiles.remove(projectile);
                continue;
            }

            if (!projectile.getIsInit()) {

                projectile.clash();
            }

        }

    }

    /**
     * Responsible for switching players in different situations
     */
    public void playerSwitch() {
        if (currentPlayerIndex >= listOfTankpieces.size() - 1) {
            currentPlayerIndex = -1;
        }
        if (currentPlayerIndex <= -1) {
            currentPlayerIndex = -1;
        }
        currentPlayerIndex += 1;

        if (listOfTankpieces.size() > 0) {
            currentPlayer = listOfTankpieces.get(currentPlayerIndex);
            currentPlayer.ifIterate = true;
            currentPlayer.ifExecute = true;
        }

        if (currentPlayerIndex >= listOfTankpieces.size() - 1) {
            currentPlayerIndex = -1;
        }
    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {

        image(img, 0, 0);

        Terrain t = new Terrain();

        t.draw(this, listOfPointsTerrain, listOfPointsTree, imgTree, foregroundColour);

        processMove();
        for (Tankpiece tank : listOfTankpieces) {

            tank.draw(this, listOfPointsTerrain, imgParachute, currentPlayer);
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

                    Terrain.terrainDamageProj(listOfPointsTerrain, listOfTankpieces, projectile, 30);

                    listOfProjectiles.remove(projectile);

                    DamageCalculation.projectileDamage(listOfTankpieces, projectile, 30);
                }

            } else {
                if (projectile.toRemove) {
                    // System.out.println("Enter");

                    Terrain.terrainDamageProj(listOfPointsTerrain, listOfTankpieces, projectile, 60);

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

                Terrain.terrainDamageTank(listOfPointsTerrain, tmp, 15);

                DamageCalculation.tankDamage(listOfTankpiecesCopy, tmp, 15);

                if (tmp == currentPlayer) {
                    currentPlayerIndex -= 1;
                    playerSwitch();
                    continue;
                }

            } else if (tmp.getCordY() >= 640) {

                Explosion ep = new Explosion(tmp.getCordX(), tmp.getCordY(), 3);
                listOfExplosions.add(ep);
                listOfTankpieces.remove(tmp);

                Terrain.terrainDamageTank(listOfPointsTerrain, tmp, 30);

                DamageCalculation.tankDamage(listOfTankpiecesCopy, tmp, 30);

                if (tmp == currentPlayer) {
                    currentPlayerIndex -= 1;
                    playerSwitch();
                }

            }

        }
        if (listOfTankpieces.size() <= 1 && !gameEnd) {
            if (ifExecute2) {
                level += 1;
            }
            if (level == total_level) {

                gameEnd = true;
            }
            if (ifExecute2) {
                start2 = System.nanoTime();
                ifExecute2 = false;
            }
            finish2 = System.nanoTime();

            if (finish2 - start2 >= 1 * 1000000000) {
                if (!gameEnd) {
                    ifExecute2 = true;
                    reset();
                }
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
