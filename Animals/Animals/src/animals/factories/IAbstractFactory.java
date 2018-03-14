package animals.factories;

import animals.animals.IAnimals;
import animals.characters.ICaretaker;

public interface IAbstractFactory {
    public IAnimals getAnimalProvider();
    public ICaretaker getCaretakerProvider();
}
