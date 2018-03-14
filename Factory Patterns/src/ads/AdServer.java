package ads;

import java.util.List;
import java.util.Random;

public abstract class AdServer {
    
    private Random random = new Random();
    private List<String> ads;
    
    public AdServer(){
    
    }
    
    public abstract IAdCreator getAdCreator();
    
    public String provideSingleAdd(){
        IAdCreator creator = getAdCreator();
        List<String> ads = creator.provideAds();
        
        return ads.get(random.nextInt(ads.size()));
    }
}
