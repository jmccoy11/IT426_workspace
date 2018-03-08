package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import entities.Fruit;
import model.FruitModel.ActionType;
import observer_pattern.Observable;
import observer_pattern.Observer;

public class FileSaver implements Observer {
    
    @Override
    public void update(Observable observable, Object args) {
        Object[] arguments = (Object[]) args;
        ActionType action = (ActionType) arguments[0];
    
        //is this something I want to respond to here?
        if (action == ActionType.CREATE || action == ActionType.DELETE ||
                action == ActionType.UPDATE) {
            FruitModel model = (FruitModel) observable;
            List<Fruit> fruits = model.getFruits();
    
            try (PrintWriter writer = new PrintWriter(new FileOutputStream("fruits.dat"))) {
                for (Fruit fruit : fruits) {
                    writer.println(fruit);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
