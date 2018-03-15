package adapters;

import drawing.IShape;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import shapes.Triangle;

public class TriangleAdapter implements IShape {

    private final double DEFAULT_WIDTH = 75;
    private final double DEFAULT_HEIGHT = 75;

    private Triangle triangle;
    private Color colorOverride;
    private Boolean isFilled;

    public TriangleAdapter(Triangle adaptee) {
        this.triangle = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
    }

    @Override
    public IShape setThickness(double value) {
        triangle.setThickness(value);
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
        return triangle.getX();
    }

    @Override
    public double getYCoordinate() {
        return triangle.getY();
    }

    @Override
    public double getThickness() {
        return triangle.getThickness();
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
            graphics.fillPolygon(
                    new double[]{
                            getXCoordinate(),
                            getXCoordinate() - DEFAULT_WIDTH/2,
                            getXCoordinate() + DEFAULT_WIDTH/2
                    },
                    new double[]{
                            getYCoordinate() - DEFAULT_HEIGHT/2,
                            getYCoordinate() + DEFAULT_HEIGHT/2,
                            getYCoordinate() + DEFAULT_HEIGHT/2
                    },
                    3
            );
        } else {
            graphics.strokePolygon(
                    new double[]{
                            getXCoordinate(),
                            getXCoordinate() - DEFAULT_WIDTH/2,
                            getXCoordinate() + DEFAULT_WIDTH/2
                    },
                    new double[]{
                            getYCoordinate() - DEFAULT_HEIGHT/2,
                            getYCoordinate() + DEFAULT_HEIGHT/2,
                            getYCoordinate() + DEFAULT_HEIGHT/2
                    },
                    3
            );
        }
    }
}
