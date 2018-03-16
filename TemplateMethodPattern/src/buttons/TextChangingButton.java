package buttons;

public class TextChangingButton extends ButtonExtension {
    
    private String oldText;
    private String defaultText = "Default";
    
    public TextChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup() {
        System.out.println("You clicked a Text Changing Button");
    }
    
    public void showText() {
        if(oldText == null) {
            oldText = getText();
            setText(defaultText);
        } else {
            setText(oldText);
            oldText = null;
        }
    }
}
