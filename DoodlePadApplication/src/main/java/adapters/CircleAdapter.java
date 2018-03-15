package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Circle;

public class CircleAdapter implements IShape {

    private final double DEFAULT_WIDTH = 75;
    private final double DEFAULT_HEIGHT = 75;

    private Circle circle;
    private Color colorOverride;
    private Boolean isFilled;

    public CircleAdapter(Circle adaptee) {
        this.circle = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
    }

    @Override
    public IShape setThickness(double value) {
        circle.setThickness(value);
        return this;
    }

    @Override
    public IShape setColor(Color value) {
        this.colorOverride = value;
        return this;
    }

    @Override
    public IShape setFilled(boolean value) {
        this.isFilled = value;
        return this;
    }

    @Override
    public double getXCoordinate() {
        return circle.getX();
    }

    @Override
    public double getYCoordinate() {
        return circle.getY();
    }

    @Override
    public double getThickness() {
        return circle.getThickness();
    }

    @Override
    public Color getColor() {
        return colorOverride;
    }

    @Override
    public boolean getFilled() {
        return isFilled;
    }

    @Override
    public void drawShape(GraphicsContext graphics) {
        graphics.setFill(getColor());
        graphics.setStroke(getColor());
        graphics.setLineWidth(getThickness());

        if (isFilled) {
            graphics.fillOval(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT);
        } else {
            graphics.strokeOval(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT);
        }
    }
}
