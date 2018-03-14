package animals.characters;

import animals.entities.Caretaker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ZooCaretakers implements ICaretaker
{
    private Map<String, Caretaker> characters;
    
    public ZooCaretakers()
    {
        characters = new HashMap<String, Caretaker>();
        
        //load our map
        characters.put("bob", new Caretaker(
                "bob", "exhibit caretaker"));
        characters.put("sarah", new Caretaker(
                "sarah", "animal trainer"));
    }

    public Map<String, Caretaker> getAllCaretakers()
    {
        return Collections.unmodifiableMap(characters);
    }

    public Caretaker getCaretakerByName(String fullName)
    {
        if (!characters.containsKey(fullName))
        {
            throw new IllegalArgumentException(
                    "No name of that type exists!");
        }
        
        return characters.get(fullName);
    }
}
