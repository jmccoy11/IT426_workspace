package animals.game;

import animals.animals.IAnimals;
import animals.entities.Animal;
import animals.factories.IAbstractFactory;
import animals.factories.PokemonFactory;

import java.util.List;

public class GameTest
{
    public static void main(String[] args)
    {
        IAbstractFactory factory = new PokemonFactory();
        
        //are there any animals with exactly one characteristic
        IAnimals providesAnimals = factory.getAnimalProvider();
        List<Animal> animals = providesAnimals.getAnimals();
        
        for(Animal animal : animals) {
            String[] characteristics = animal.getCharacteristics();
            
            if(characteristics.length == 1) {
                System.out.println(animal.getName() + " has 1 characteristic: " + characteristics[0]);
            }
        }
    }
}
