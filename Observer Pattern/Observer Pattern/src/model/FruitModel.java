package model;

import entities.Fruit;
import observer_pattern.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class FruitModel extends Observable
{
    private List<Fruit> fruits;

    public FruitModel()
    {
        fruits = new ArrayList<>();
    }

    //CRUD ACCESS
    public void addFruit(Fruit fruit)
    {
        fruits.add(fruit);
        
        setChanged();
        notifyObservers(new Object[] {ActionType.CREATE, fruit.getId()});
    }

    public void removeFruit(UUID id)
    {
        for (int i = fruits.size() - 1; i >= 0; i--)
        {
            if (id.equals(fruits.get(i).getId()))
            {
                fruits.remove(i);
            }
        }
    
    
        setChanged();
        notifyObservers(new Object[] {ActionType.DELETE, id});
    }

    public List<Fruit> getFruits()
    {
        setChanged();
        notifyObservers(new Object[] {ActionType.READ});
        
        return Collections.unmodifiableList(fruits);
    }

    public void updateFruit(UUID id, String type, boolean fresh)
    {
        for (int i = 0; i < fruits.size(); i++)
        {
            Fruit check = fruits.get(i);
            if (id.equals(check.getId()))
            {
                check.setType(type);
                check.setFresh(fresh);
                break;
            }
        }
        
        setChanged();
        notifyObservers(new Object[] {ActionType.UPDATE, id});
    }

    public enum ActionType
    {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }
}
