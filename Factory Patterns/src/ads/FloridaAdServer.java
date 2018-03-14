package ads;

public class FloridaAdServer extends AdServer {
    
    @Override
    public IAdCreator getAdCreator() {
        return new FloridaAdCreator();
    }
}
