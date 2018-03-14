package animals.characters;

import animals.entities.Caretaker;

import java.util.Map;

public interface ICaretaker {
    public Map<String, Caretaker> getAllCaretakers();
    public Caretaker getCaretakerByName(String fullName);
}
