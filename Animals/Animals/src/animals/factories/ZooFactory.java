package animals.factories;

import animals.animals.IAnimals;
import animals.animals.ZooAnimals;
import animals.characters.ICaretaker;
import animals.characters.ZooCaretakers;

public class ZooFactory implements IAbstractFactory {
    
    @Override
    public IAnimals getAnimalProvider() {
        return new ZooAnimals();
    }
    
    @Override
    public ICaretaker getCaretakerProvider() {
        return new ZooCaretakers();
    }
}
