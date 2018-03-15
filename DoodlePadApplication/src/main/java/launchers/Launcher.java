package launchers;

import javafx.application.Application;
import ui.DoodlePadUI;

public class Launcher
{
    /**
     * Entry point for the program.
     * @param args - String[] - Command line arguments
     */
    public static void main(String[] args)
    {
        Application.launch(DoodlePadUI.class, args);
    }
}