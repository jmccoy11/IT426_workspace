/*
  Author: Jonnathon McCoy
  Date: 3/15/2018
  Filename: LineAdapter.java

  The class creates a wrapper for a Line shape to demonstrate using the adapter
  pattern to add features and functionality to a class closed for modification by
  programming against an interface.
 */

package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Line;

/**
 *   The class creates a wrapper for a Line shape to demonstrate using the adapter
 *   pattern to add features and functionality to a class closed for modification by
 *   programming against an interface.
 */
public class LineAdapter implements IShape {

    private Line line;
    private Color colorOverride;
    private Boolean isFilled;
    private int xEndCoordinate;
    private int yEndCoordinate;

    /**
     * Constructor
     * @param adaptee - Line - object to wrap
     * @param xEnd - end X point
     * @param yEnd - end Y point
     */
    public LineAdapter(Line adaptee, int xEnd, int yEnd) {
        this.line = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
        this.xEndCoordinate = xEnd;
        this.yEndCoordinate = yEnd;
    }

    /**
     * Set the border thickness. Method allows for chaining.
     * @param value - double - new thickness value
     * @return - returns this object so that it may be chained with other methods.
     */
    @Override
    public IShape setThickness(double value) {
        line.setThickness(value);
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
        return line.getX();
    }

    /**
     * Getter for Y Coordinate for upper left corner
     * @return - double - Y coordinate
     */
    @Override
    public double getYCoordinate() {
        return line.getY();
    }

    /**
     * Getter for border thickness
     * @return - double - border thickness
     */
    @Override
    public double getThickness() {
        return line.getThickness();
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
        graphics.strokeLine(getXCoordinate(), getYCoordinate(), xEndCoordinate, yEndCoordinate);
    }
}
