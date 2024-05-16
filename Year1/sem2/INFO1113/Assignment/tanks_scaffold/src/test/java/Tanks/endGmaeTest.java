package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class endGmaeTest {

    // check game ending and restart of the game
    @Test
    public void endingTest() throws InterruptedException {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        while (app.listOfTankpieces.size() <= 3) {
            Thread.sleep(1000);
        }
        app.listOfTankpieces.get(1).point = 10;
        app.listOfTankpieces.get(2).point = 20;
        app.listOfTankpieces.get(3).point = 30;
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);

        while (app.listOfTankpieces.size() <= 3) {
            Thread.sleep(1000);
        }
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);

        while (app.listOfTankpieces.size() <= 3) {
            Thread.sleep(1000);
        }
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);

        Thread.sleep(5000);
        app.noLoop();
        Thread.sleep(1000);
        app.keyCode = 'r';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));

        app.loop();

        Thread.sleep(2000);
        app.keyReleased();
        assertEquals(app.gameEnd, false);
        Thread.sleep(1000);
    }

    // testing keyboard while gameend
    @Test
    public void gameEndFunctionTest() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);

        app.gameEnd = true;
        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.delay(100);
        assertEquals(app.tankMoveLeft, false);
        app.keyReleased();

        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveRight, false);
        app.delay(100);
        app.keyReleased();

        app.keyCode = 38; // up
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovLeft, false);
        app.delay(100);
        app.keyReleased();

        app.keyCode = 40; // down
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.turretMovRight, false);
        app.delay(100);
        app.keyReleased();

        app.keyCode = 32;// space
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.delay(100);
        app.keyReleased();

        app.keyCode = 'w';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.wPress, false);
        app.delay(100);
        app.keyReleased();

        app.keyCode = 's';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.sPress, false);
        app.delay(100);
        app.keyReleased();

        app.keyCode = 'f';
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        app.delay(100);
        app.keyReleased();

        app.delay(1000);

    }

}
