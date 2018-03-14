package ads;

import java.util.Arrays;
import java.util.List;

public class TexasAdCreator implements IAdCreator {
    
    @Override
    public List<String> provideAds() {
        return Arrays.asList("Only steers and queers come from Texas",
                "Come BBQ with us!",
                "We know queso",
                "Nothing as flat as Texas");
    }
}
