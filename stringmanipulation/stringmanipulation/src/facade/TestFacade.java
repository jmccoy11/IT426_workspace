package facade;

public class TestFacade {
    
    public static void main(String[] args) {
        String[] colors = {"green", "burgundy", "purple", "burgundy",
                "burgundy", "magenta", "cyan", "cyan"};
        
        colors = Facade.operate(colors)
                .filter("green")
                .removeDuplicates()
                .resize(3)
                .getData();
    }
}
