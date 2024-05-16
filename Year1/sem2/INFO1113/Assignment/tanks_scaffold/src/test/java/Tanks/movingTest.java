package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class movingTest {
    // testing tank move left
    @Test
    public void movingLeft() {
        App app = new App();
        app.level = 1;
        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveLeft, true);

        app.delay(4000);
        app.keyReleased();

    }

    // testing tank move right with different tanks
    @Test
    public void movingRight() {
        App app = new App();
        app.level = 1;
        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);
        app.currentPlayer = app.listOfTankpieces.get(2);

        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveRight, true);
        app.delay(5000);
        app.keyReleased();

    }

    // moving when fuel runs out
    @Test
    public void mixMoving() {
        App app = new App();
        app.level = 1;
        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);
        app.currentPlayer.fuel = 0;

        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveRight, true);
        app.delay(1000);
        app.keyReleased();

        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveLeft, true);

        app.delay(1000);
        app.keyReleased();

    }

}
