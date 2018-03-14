package ads;

public class TestAds {
    
    public static void main(String[] args) {
        AdServer server = new FloridaAdServer();
        String ad = server.provideSingleAdd();
        System.out.println(ad);
        
        server = new TexasAdServer();
        ad = server.provideSingleAdd();
        System.out.println(ad);
    }
}
