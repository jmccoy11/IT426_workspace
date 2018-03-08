package ui.layouts;

import javafx.scene.Parent;
import javafx.scene.Scene;
import ui.UI;

public class WindowScene extends Parent {
    
    private UI ui;
    private Scene layout;
    
    public WindowScene(UI ui) {
        this.ui = ui;
    }
    
    public UI getUi() {
        return ui;
    }
    
    public Scene getLayout() {
        return layout;
    }
    
    public void setLayout(Scene layout) {
        this.layout = layout;
    }
}
