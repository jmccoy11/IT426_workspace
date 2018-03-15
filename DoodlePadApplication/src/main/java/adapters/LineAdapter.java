package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Line;

import java.util.Random;

public class LineAdapter implements IShape {

    private Line line;
    private Color colorOverride;
    private Boolean isFilled;
    private int xEndCoordinate;
    private int yEndCoordinate;

    public LineAdapter(Line adaptee, int xEnd, int yEnd) {
        this.line = adaptee;
        this.colorOverride = adaptee.getColor();
        this.isFilled = false;
        this.xEndCoordinate = xEnd;
        this.yEndCoordinate = yEnd;
    }

    @Override
    public IShape setThickness(double value) {
        line.setThickness(value);
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
        return line.getX();
    }

    @Override
    public double getYCoordinate() {
        return line.getY();
    }

    @Override
    public double getThickness() {
        return line.getThickness();
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
        graphics.strokeLine(getXCoordinate(), getYCoordinate(), xEndCoordinate, yEndCoordinate);
    }
}
