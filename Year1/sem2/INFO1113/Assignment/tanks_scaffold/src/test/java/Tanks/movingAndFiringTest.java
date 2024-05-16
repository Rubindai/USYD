package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class movingAndFiringTest {
    // Testing moving left and firing out of the window bound
    @Test
    public void fireToVoid() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);

        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(5000);
        app.keyReleased();

        app.keyCode = 38;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(1000);
        app.keyReleased();
        app.delay(500);

        app.keyCode = 32;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.wind.windSpeed = 0;
        assertEquals(app.listOfProjectiles.size(), 0);
        app.delay(1000);

    }

    // test moving left and firing towards terrian
    @Test
    public void fireToTerrian() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 40;// down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(1000);
        app.keyReleased();
        app.delay(500);

        app.delay(1000);
        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.keyReleased();
        app.delay(100);

        assertEquals(app.listOfExplosions.size(), 0);
        app.delay(1000);
    }

    // test firing and moving multiple time
    @Test
    public void firingAndMoving() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);

        app.keyCode = 40;// down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(1000);
        app.keyReleased();
        app.delay(500);

        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.wind.windSpeed = 0;
        app.delay(100);

        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(2000);
        app.keyReleased();

        app.keyCode = 38; // up
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(500);
        app.keyReleased();

        app.keyCode = 32;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.wind.windSpeed = 0;
        assertNotEquals(app.listOfProjectiles, null);
        assertNotEquals(app.currentPlayer, null);
        app.delay(5000);
    }

    // testing firing on others
    @Test
    public void firingOnOthers() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer = app.listOfTankpieces.get(2);

        app.keyCode = 40;// down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(2000);
        app.keyReleased();

        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(200);
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(200);
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(200);
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(200);
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(200);

        app.delay(5000);
        assertNotEquals(app.listOfTankpieces.get(3).hp, 100);

    }

}
