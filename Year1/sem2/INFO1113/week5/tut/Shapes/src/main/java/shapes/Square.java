package shapes;

import processing.core.PImage;

/**
 * Represents a square.
 */
public class Square extends Shape {

    /**
     * Whether to move left.
     */
    private boolean moveLeft;

    /**
     * Creates a new square with coordinates (20, 20)
     */
    public Square() {
        super(20, 20);
        this.moveLeft = false;
    }

    /**
     * Updates the square every frame.
     */
    public void tick() {
        // If moveLeft is true, move left by decrementing x
        if (moveLeft) {
            this.x--;
        } else {
            // Move right by incrementing x
            this.x++;
        }
    }

    /**
     * Called in App when the left key is pressed.
     */
    public void pressLeft() {
        this.moveLeft = true;
    }

    /**
     * Called in App when the right key is pressed.
     */
    public void pressRight() {
        this.moveLeft = false;
    }
}
