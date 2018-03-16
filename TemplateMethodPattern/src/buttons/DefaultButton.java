package buttons;

public class DefaultButton extends ButtonExtension {
    
    public DefaultButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup() {
        System.out.println("You clicked a default button.");
    }
}
