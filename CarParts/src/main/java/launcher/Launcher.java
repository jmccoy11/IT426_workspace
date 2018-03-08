/*
 * File: Launcher.java
 * Author: Josh Archer
 *
 * Launcher for the Car Parts Import/Export Program.
 */

package launcher;

import javafx.application.Application;
import view.PartsUI;

public class Launcher
{
    
    /**
     * Entry point for the program.
     * @param args - String[] - Command line arguments
     */
    public static void main(String[] args)
    {
        Application.launch(PartsUI.class, args);
    }
}
