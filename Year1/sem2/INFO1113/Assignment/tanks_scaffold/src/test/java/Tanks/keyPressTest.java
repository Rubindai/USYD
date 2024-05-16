package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class keyPressTest {
    // testing pressing left arrow
    @Test
    public void keyPressLeft() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveLeft, true);

        app.delay(100);
        app.keyReleased();

    }

    // testing pressing right arrow
    @Test
    public void keyPressRight() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveRight, true);

        app.delay(100);
        app.keyReleased();

    }

    // testing pressing up arrow
    @Test
    public void keyPressUp() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 38; // up
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovLeft, true);

        app.delay(3000);
        app.keyReleased();

    }

    // testing pressing down arrow
    @Test
    public void keyPressDown() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 40; // down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovRight, true);

        app.delay(3000);
        app.keyReleased();
        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();

        app.wind.windSpeed = 0;
        app.delay(3000);

    }

    // testing pressing s and w
    @Test
    public void extentionTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 'w';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.wPress, true);

        app.delay(5000);
        app.keyReleased();

        app.keyCode = 's';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.sPress, true);

        app.delay(6000);
        app.keyReleased();

    }

    // testing when pressing buttons at the same time
    @Test
    public void clashTest() {

        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);
        // app.turretMovLeft=true;
        // app.turretMovRight=true;
        app.keyCode = 38; // up
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyCode = 40; // down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.delay(500);

        assertEquals(app.turretMovLeft, true);
        assertEquals(app.turretMovRight, true);

        // app.delay(100);

        app.keyCode = 39;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyCode = 37;
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.delay(500);

        assertEquals(app.tankMoveLeft, true);
        assertEquals(app.tankMoveRight, true);

        app.delay(100);
        app.keyCode = 'w';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.keyCode = 's';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.sPress, true);
        assertEquals(app.wPress, true);
        app.delay(1000);
    }

}
