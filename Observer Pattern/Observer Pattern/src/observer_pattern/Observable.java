package observer_pattern;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable {
    private Set<Observer> observers;
    private boolean isChanged = false;
    
    public Observable() {
        this.observers = new HashSet<>();
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void setChanged(){
        isChanged = true;
    }
    
    public void notifyObservers(Object args) {
        if(isChanged) {
            isChanged = false;
    
            for (Observer observer : observers) {
                observer.update(this, args);
            }
        }
    }
}
