package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Rectangle;

public class RectangleAdapter implements IShape {

    private final double DEFAULT_WIDTH = 100;
    private final double DEFAULT_HEIGHT = 75;

    private Rectangle rectangle;
    private Color colorOverride;
    private Boolean isFilled;

    public RectangleAdapter(Rectangle adaptee) {
        this.rectangle = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
    }

    @Override
    public IShape setThickness(double value) {
        rectangle.setThickness(value);
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
        return rectangle.getX();
    }

    @Override
    public double getYCoordinate() {
        return rectangle.getY();
    }

    @Override
    public double getThickness() {
        return rectangle.getThickness();
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
            graphics.fillRect(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT
            );
        } else {
            graphics.strokeRect(
                    getXCoordinate() - DEFAULT_WIDTH/2,
                    getYCoordinate() - DEFAULT_HEIGHT/2,
                    DEFAULT_WIDTH,
                    DEFAULT_HEIGHT
            );
        }
    }
}
