package animals.factories;

import animals.animals.IAnimals;
import animals.animals.PokemonAnimals;
import animals.characters.ICaretaker;
import animals.characters.PokemonCaretakers;

public class PokemonFactory implements IAbstractFactory {
    
    @Override
    public IAnimals getAnimalProvider() {
        return new PokemonAnimals();
    }
    
    @Override
    public ICaretaker getCaretakerProvider() {
        return new PokemonCaretakers();
    }
}
