package shapes;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * Represents an abstract Shape object that is inherited by Square and Circle.
 */
public abstract class Shape {
    
    /**
     * The Shape's x-coordinate.
     */
    protected int x;
    
    /**
     * The Shape's y-coordinate.
     */
    protected int y;

    /**
     * The Shape's sprite.
     */
    private PImage sprite;

    /**
     * Creates a new Shape object.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the shape's sprite.
     * 
     * @param sprite The new sprite to use.
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Updates the shape every frame.
     */
    public abstract void tick();

    /**
     * Draws the shape to the screen.
     * 
     * @param app The window to draw onto.
     */
    public void draw(PApplet app) {
        // The image() method is used to draw PImages onto the screen.
        // The first argument is the image, the second and third arguments are coordinates
        app.image(this.sprite, this.x, this.y);
    }

    /**
     * Gets the x-coordinate.
     * @return The x-coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate.
     * @return The y-coordinate.
     */
    public int getY() {
        return this.y;
    }
}
