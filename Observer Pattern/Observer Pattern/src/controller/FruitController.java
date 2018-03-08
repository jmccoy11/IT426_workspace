package controller;

import entities.Fruit;
import model.FileSaver;
import model.FruitModel;
import view.ConsoleTrace;
import view.FruitStandUI;

import java.util.List;
import java.util.UUID;

public class FruitController
{
    private FruitModel model;
    private ConsoleTrace trace;
    private FileSaver fileSaver;

    public FruitController(FruitStandUI view)
    {
        model = new FruitModel();
        trace = new ConsoleTrace();
        fileSaver = new FileSaver();
        
        //register observers
        model.addObserver(view);
        model.addObserver(trace);
        model.addObserver(fileSaver);
    }

    public void addFruit(String type, String fresh)
    {
        model.addFruit(new Fruit(type, Boolean.valueOf(fresh)));
    }

    public void removeFruit(UUID id)
    {
        model.removeFruit(id);
    }

    public List<Fruit> getFruits()
    {
        return model.getFruits();
    }

    public void updateFruit(UUID id, String type, boolean fresh)
    {
        model.updateFruit(id, type, fresh);
    }
}
