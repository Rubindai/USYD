package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class extentionTest {
    // testing repair powerup
    @Test
    public void repairTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer.point = 100;
        app.currentPlayer.hp = 81;
        app.keyCode = 'r';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.keyReleased();
        app.delay(100);

        assertEquals(app.currentPlayer.hp, 100);
        assertEquals(app.currentPlayer.point, 80);

    }

    // testing adding fuel
    @Test
    public void fuelTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer.point = 100;
        

        app.keyCode = 'f';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.keyReleased();
        app.delay(100);

        assertEquals(app.currentPlayer.fuel, 450);
        assertEquals(app.currentPlayer.point, 90);

    }

    // testing adding parachute
    @Test
    public void parachuteTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer.point = 100;

        app.keyCode = 'p';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.keyReleased();
        app.delay(100);

        assertEquals(app.currentPlayer.getParachute(), 4);
        assertEquals(app.currentPlayer.point, 85);

    }

    // testing large projectile
    @Test
    public void largeProjectileTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer.point = 100;

        app.keyCode = 'x';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.currentPlayer.getProjSize(), false);

        app.keyReleased();
        app.delay(100);
        assertEquals(app.currentPlayer.point, 80);

        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.wind.windSpeed = 0;
        app.delay(6000);

    }

}
