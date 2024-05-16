package Tanks;

import processing.core.PImage;

public class Wind {
    public int windSpeed;
    private PImage windLeft;
    private PImage windRight;

    /**
     * Constructor for Wind
     * @param windLeft image of windleft
     * @param windRight image of windright
     */
    public Wind(PImage windLeft, PImage windRight) {
        randomInit();
        this.windLeft = windLeft;
        this.windRight = windRight;

    }

    public int getWindspeed() {
        return windSpeed;
    }

    /**
     * Set initial random wind value when the level starts
     */
    public void randomInit() {
        int min = -35;
        int max = 35;

        int randomWindInit = (int) Math.floor(Math.random() * (max - min + 1) + min);
        windSpeed = randomWindInit;

    }

    /**
     * Switch wind per turn
     */
    public void randomTurn() {
        int min = -5;
        int max = 5;

        int randomWindInit = (int) Math.floor(Math.random() * (max - min + 1) + min);
        windSpeed += randomWindInit;
      
       

    }

    /**
     * Draw Wind icon
     * @param app app from App
     */
    public void draw(App app) {
        // windspeed=10;
        if (windSpeed < 0) {
            windLeft.resize(55, 55);
            app.image(windLeft, 760, 0);
        }
        if (windSpeed > 0) {
            windRight.resize(55, 55);
            app.image(windRight, 760, 0);

        }
        app.fill(0, 0, 0);

        app.textSize(20);
        app.text(Math.abs(windSpeed), 820, 35);
    }

}
