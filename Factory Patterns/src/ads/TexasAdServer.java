package ads;

public class TexasAdServer extends AdServer {
    
    @Override
    public IAdCreator getAdCreator() {
        return new TexasAdCreator();
    }
}
