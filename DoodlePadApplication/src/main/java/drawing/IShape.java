package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IShape
{
    //method chaining
    public IShape setThickness(double value);
    public IShape setColor(Color value);
    public IShape setFilled(boolean value);

    //getters
    public double getXCoordinate();
    public double getYCoordinate();
    public double getThickness();
    public Color getColor();
    public boolean getFilled();

    //draw the shape
    public void drawShape(GraphicsContext graphics);
}
