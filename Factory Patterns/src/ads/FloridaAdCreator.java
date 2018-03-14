package ads;

import java.util.Arrays;
import java.util.List;

public class FloridaAdCreator implements IAdCreator{
    
    @Override
    public List<String> provideAds() {
        return Arrays.asList("Eat gator stew!",
                "Come visit Cape Canaveral",
                "Come to Disney World");
    }
}
