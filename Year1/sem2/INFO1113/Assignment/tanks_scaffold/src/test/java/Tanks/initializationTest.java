package Tanks;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class initializationTest {

    // testing main function
    @Test
    public void MainTest() {
        App app = new App();
        String[] a = {};
        App.main(a);
        app.delay(500);
        assertEquals(app.gameEnd, false);

    }

    // testing setup initialize
    @Test
    public void SetupTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);
        assertNotEquals(app.currentPlayer, null);
        assertEquals(app.gameEnd, false);

    }

    // testing setup with no tree in config
    @Test
    public void noTree() {
        App app = new App();
        app.level = 1;
        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);
        assertNotEquals(app.listOfPointsTree, null);

    }

    // testing init with random player color
    @Test
    public void randomColor() {
        App app = new App();
        app.level = 1;
        app.configPath = "configTest.json";
        PApplet.runSketch(new String[] { "App" }, app);

        app.delay(1000);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.delay(3000);
        assertNotEquals(app.currentPlayer, null);

    }

    // testing level switch, whether the parachute num inherit from previous
    // setting
    @Test
    public void levelSwitch() {
        App app = new App();

        PApplet.runSketch(new String[] { "App" }, app);
        app.delay(1000);

        app.currentPlayer.parachute = 2;
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);
        app.listOfTankpieces.remove(1);

        app.delay(2000);
        assertEquals(app.currentPlayer.parachute, 2);

    }

}
