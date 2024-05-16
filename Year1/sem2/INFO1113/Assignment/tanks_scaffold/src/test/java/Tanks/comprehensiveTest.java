package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class comprehensiveTest {
    @Test
    // testing gaming in general
    public void generalTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);
        app.currentPlayer.setPoint(150, app.currentPlayer);
        assertEquals(app.currentPlayer.point, 100);

        app.listOfTankpieces.get(3).parachute = 0;

        app.currentPlayer = app.listOfTankpieces.get(2);

        app.keyCode = 40;// down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovRight, true);

        app.delay(2000);
        app.keyReleased();
        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        assertNotEquals(app.listOfProjectiles, null);

        app.delay(200);
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();

        app.keyCode = 38;// up
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovLeft, true);

        app.delay(500);
        app.keyReleased();

        app.keyCode = 'w';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.wPress, true);

        app.delay(2000);
        app.keyReleased();

        app.keyCode = 's';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.sPress, true);

        app.delay(2000);
        app.keyReleased();

        app.keyCode = 'w';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.delay(1500);
        app.keyReleased();

        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.keyReleased();
        app.currentPlayer = app.listOfTankpieces.get(2);
        app.wind.windSpeed = 0;
        app.delay(3000);


    }

}
