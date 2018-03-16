package buttons;

public class SizeChangingButton extends ButtonExtension {
    
    public SizeChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup() {
        System.out.println("You clicked a size changing button");
    }
    
    public void resizeButton() {
        if(getPrefWidth() == 500) {
            setPrefSize(100, 50);
        } else {
            setPrefSize(500, 100);
        }
    }
}
