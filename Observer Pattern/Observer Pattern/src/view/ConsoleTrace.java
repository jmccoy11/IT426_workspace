package view;


import model.FruitModel.ActionType;
import observer_pattern.Observable;
import observer_pattern.Observer;

public class ConsoleTrace implements Observer {
    
    @Override
    public void update(Observable observable, Object args) {
        Object[] arguments = (Object[]) args;
        ActionType action = (ActionType) arguments[0];
    
        //is this something I want to respond to here?
        if (action == ActionType.READ) {
            System.out.println("List of fruits were read...");
        }
    }
}
