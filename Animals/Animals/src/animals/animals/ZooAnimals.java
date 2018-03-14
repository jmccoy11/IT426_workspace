package animals.animals;

import animals.entities.Animal;

import java.util.Arrays;
import java.util.List;

public class ZooAnimals implements IAnimals
{
    //other fields, constructors and methods here

    public List<Animal> getAnimals()
    {
        Animal[] animals = {
                new Animal("lion",
                    new String[] {"king of the jungle", "is furry"}),
                new Animal("bear",
                    new String[] {"likes shade", "loves water"}),
                new Animal("snake",
                    new String[] {"avoids humans"})};
        
        return Arrays.asList(animals);
    }
}

