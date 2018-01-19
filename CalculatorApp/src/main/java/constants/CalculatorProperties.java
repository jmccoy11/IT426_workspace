package constants;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CalculatorProperties {
    public static final int SIDE_PADDING_INSETS = 20;
    public static final int APP_MIN_WIDTH = 300;
    public static final int APP_MIN_HEIGHT = 400;
    public static final int APP_MAX_WIDTH = 600;
    public static final int APP_MAX_HEIGHT = 800;
    public static final int COLUMN_CONSTRAINT_WIDTH_PERCENT = 25;
    public static final int BUTTON_GAP = 10;
    public static final int BUTTON_GRID_COLUMNS = 4;
    public static final int ENTER_BTN_COLSPAN = 2;
    public static final String[][] BUTTON_MAP = new String[][]{
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", "Enter", "/"}
    };
    
    public static class ButtonProperties {
        private static final Color BACKGROUND_COLOR = Color.gray(0.9);
        private static final Color BORDER_COLOR = Color.gray(0.3);
        private static final CornerRadii CORNER_RADII = new CornerRadii(5);
        private static final BorderWidths BORDER_WIDTHS = new BorderWidths(1);
        
        public static final int BUTTON_WIDTH = 50;
        public static final int BUTTON_HEIGHT = 50;
        public static final Background BUTTON_BACKGROUND = new Background(new BackgroundFill(
                BACKGROUND_COLOR, CORNER_RADII, Insets.EMPTY));
        public static final Border BUTTON_BORDER = new Border(new BorderStroke(BORDER_COLOR,
                   BorderStrokeStyle.SOLID, CORNER_RADII, BORDER_WIDTHS));
    }
    
    public static class OutputDisplayProperties {
        private static final Color BACKGROUND_COLOR = Color.WHITE;
        private static final Color BORDER_COLOR = Color.gray(0.3);
        private static final CornerRadii CORNER_RADII = new CornerRadii(5);
        private static final BorderWidths BORDER_WIDTHS = new BorderWidths(1);
    
        public static final int OUTPUT_DISPLAY_WIDTH = 50;
        public static final int OUTPUT_DISPLAY_HEIGHT = 50;
        public static final int OUTPUT_DISPLAY_ROW_INDEX = 4;
        public static final int OUTPUT_DISPLAY_COL_SPAN = 4;
        public static final Background OUTPUT_DISPLAY_BACKGROUND = new Background(new BackgroundFill(
                BACKGROUND_COLOR, CORNER_RADII, Insets.EMPTY));
        public static final Border OUTPUT_DISPLAY_BORDER = new Border(new BorderStroke(BORDER_COLOR,
                BorderStrokeStyle.SOLID, CORNER_RADII, BORDER_WIDTHS));
        public static final Insets OUTPUT_DISPLAY_PADDING = new Insets(0, 20, 0, 20);
    }
}
