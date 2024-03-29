package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ui.DoodlePadUI;

import java.util.ArrayList;
import java.util.List;

public class SavedShapes
{
    private List<IShape> shapes;

    public SavedShapes()
    {
        shapes = new ArrayList<>();
    }

    public boolean add(IShape shape)
    {
        if (!shapes.contains(shape))
        {
            shapes.add(shape);
            return true;
        }
        return false;
    }

    public boolean update(IShape shapeToUpdate, double thickness, Color color, boolean filled)
    {
        //get the shape
        IShape found = null;
        for (IShape shape : shapes)
        {
            if (shape.equals(shapeToUpdate))
            {
                found = shape;
                break;
            }
        }

        //update the shape
        if (found != null)
        {
            //method chaining
            shapeToUpdate.setThickness(thickness).setColor(color).setFilled(filled);
            return true;
        }

        return false;
    }

    public void drawShapes(GraphicsContext graphics)
    {
        //clear the graphics context, you may change the width and height here to match your Canvas size
        graphics.setFill(Color.LIGHTGRAY);
        graphics.fillRect(0, 0, DoodlePadUI.CANVAS_INIT_WIDTH, DoodlePadUI.CANVAS_INIT_HEIGHT);

        for (IShape shape : shapes)
        {
            shape.drawShape(graphics);
        }
    }
}
