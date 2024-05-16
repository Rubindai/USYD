package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.event.KeyEvent;

public class explosionTankTest {
    // tank explode with low hp
    @Test
    public void explodeLowHp() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.keyCode = 37; // left
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveLeft, true);

        app.delay(3000);
        app.keyReleased();

        app.currentPlayer.hp = 0;
        assertNotEquals(app.currentPlayer, null);

        app.delay(2000);

    }

    // testing explosion on the edge
    @Test
    public void explodeOutOfTerrain() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        for (int i = 150; i < 200; i++) {
            float[] temp = { i, 650 };
            app.listOfPointsTerrain.set(i, temp);
        }
        assertNotEquals(app.currentPlayer, null);
        app.keyCode = 39; // right
        app.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 0));
        assertEquals(app.tankMoveRight, true);

        app.delay(100);

        app.delay(4000);
        app.keyReleased();
        app.delay(200);

    }

}
