package shapes;

import javafx.scene.paint.Color;

public class Shape
{
    //location
    private double x;
    private double y;

    //shape drawing settings
    private double thickness;
    private Color color; //readonly

    public Shape(double x, double y, double thickness, Color color)
    {
        this.x = x;
        this.y = y;

        this.thickness = thickness;
        this.color = color;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getThickness()
    {
        return thickness;
    }

    public Color getColor()
    {
        return color;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setThickness(double thickness)
    {
        this.thickness = thickness;
    }

    public String toString()
    {
        return "Shape [x=" + x + ", y=" + y + ", thickness=" + thickness + ", color=" + color + "]";
    }
}
