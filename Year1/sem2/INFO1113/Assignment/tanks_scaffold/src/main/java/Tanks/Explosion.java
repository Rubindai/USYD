package Tanks;

public class Explosion implements ExplosionInterface {
    private float expectedRadius;
    private float currRadius = 0;
    private float cordX;
    private float cordY;
    public boolean status;

    /**
     * Constructor for Explosion
     * 
     * @param cordX x coordinate of the explosion
     * @param cordY y coordinate of the explosion
     * @param expectedRadius the expected explosion radius
     */
    public Explosion(float cordX, float cordY, float expectedRadius) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.expectedRadius = expectedRadius;
    }

    /**
     * Draw explode animations
     * 
     * @param app app from App class
     */
    public void explode(App app) {
        if (currRadius < expectedRadius) {
            currRadius += expectedRadius / 6;
            app.noStroke();

            if (currRadius <= expectedRadius) {

                app.noStroke();
                app.fill(255, 0, 0);
                app.ellipse(cordX, cordY, currRadius * 10 * 2, currRadius * 10 * 2);

            }

            if (currRadius < expectedRadius * 0.5) {
                app.fill(255, 165, 0);
                app.ellipse(cordX, cordY, currRadius * 10 * 2, currRadius * 10 * 2);

            } else {
                app.fill(255, 165, 0);
                app.ellipse(cordX, cordY, 30, 30);

            }

            if (currRadius < expectedRadius * 0.2) {

                app.fill(255, 255, 0);
                app.ellipse(cordX, cordY, currRadius * 10 * 2, currRadius * 10 * 2);

            } else {
                app.fill(255, 255, 0);
                app.ellipse(cordX, cordY, 12, 12);

            }
        } else {
            status = true;
        }

    }
}