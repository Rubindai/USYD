package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class projectileTest {
    // Testing firing single shot, and check whether keep pressing spacebar for a
    // short period could fire multiple projectiles.
    @Test
    public void fireTestSimple() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);

        app.keyCode = 32;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        // app.keyReleased();
        assertNotEquals(app.listOfProjectiles, null);

        app.delay(100);

        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.delay(5000);

    }

    // Testing firing multiple shot
    @Test
    public void fireTestMultiple() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);

        app.keyCode = 32;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.keyReleased();
        app.delay(500);
        // assertNotEquals(app.listOfProjectiles, null);

        // app.delay(2000);

        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.keyReleased();
        app.delay(500);

        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.keyReleased();
        app.delay(500);

        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.wind.windSpeed = 0;
        app.keyReleased();
        assertEquals(app.listOfProjectiles.size(), 4);

        app.delay(5000);

    }
}
