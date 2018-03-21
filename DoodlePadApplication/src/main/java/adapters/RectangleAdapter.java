/*
  Author: Jonnathon McCoy
  Date: 3/15/2018
  Filename: RectangleAdapter.java

  The class creates a wrapper for a Rectangle shape to demonstrate using the adapter
  pattern to add features and functionality to a class closed for modification by
  programming against an interface.
 */

package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Rectangle;

/**
 * The class creates a wrapper for a Rectangle shape to demonstrate using the adapter
 * pattern to add features and functionality to a class closed for modificationby
 * programming against an interface.
 */
public class RectangleAdapter implements IShape {

    private final double DEFAULT_WIDTH = 100;
    private final double DEFAULT_HEIGHT = 75;

    private Rectangle rectangle;
    private Color colorOverride;
    private Boolean isFilled;

    /**
     * Constructor
     * @param adaptee - Rectangle - object to wrap
     */
    public RectangleAdapter(Rectangle adaptee) {
        this.rectangle = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
    }

    /**
     * Set the border thickness. Method allows for chaining.
     * @param value - double - new thickness value
     * @return - returns this object so that it may be chained with other methods.
     */
    @Override
    public IShape setThickness(double value) {
        rectangle.setThickness(value);
        return this;
    }

    /**
     * Sets the background color of the shape.
     * @param value - Color - new color value
     * @return - returns this object so that it may be chained with other methods.
     */
    @Override
    public IShape setColor(Color value) {
        this.colorOverride = value;
        return this;
    }

    /**
     * Sets the boolean expression on whether the shape does or does not use a background.
     * @param value - boolean - true or false
     * @return - returns this object so that it may be chained with other methods
     */
    @Override
    public IShape setFilled(boolean value) {
        this.isFilled = value;
        return this;
    }

    /**
     * Getter for X Coordinate for upper left corner
     * @return - double - X coordinate
     */
    @Override
    public double getXCoordinate() {
        return rectangle.getX();
    }

    /**
     * Getter for Y Coordinate for upper left corner
     * @return - double - Y coordinate
     */
    @Override
    public double getYCoordinate() {
        return rectangle.getY();
    }

    /**
     * Getter for border thickness
     * @return - double - border thickness
     */
    @Override
    public double getThickness() {
        return rectangle.getThickness();
    }

    /**
     * Getter for background color
     * @return - Color - background color
     */
    @Override
    public Color getColor() {
        return colorOverride;
    }

    /**
     * Getter for isFilled.
     * @return - boolean - true or false
     */
    @Override
    public boolean getFilled() {
        return isFilled;
    }

    /**
     * Draws the shape on the canvas.
     * @param graphics - the context from the canvas
     */
    @Override
    public void drawShape(GraphicsContext graphics) {
        graphics.setFill(getColor());
        graphics.setStroke(getColor());
        graphics.setLineWidth(getThickness());

        // if is filled, use .fillRect()
        if (isFilled) {
            graphics.fillRect(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT
            );
        } else { // else, use .strokeRect()
            graphics.strokeRect(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT
            );
        }
    }
}
