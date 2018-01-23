/*
 * Jonnathon McCoy
 * 01/20/2018
 * CalculatorProperties.java
 *
 * This class contains the code to launch the Calculator App.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package launch;

import javafx.application.Application;
import ui.CalculatorUI;

/**
 * This class contains the code to launch the Calculator App.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Launcher {
    
    /**
     * Entry point for the CalculatorApp.
     *
     * @param args - String[] - Command Line Arguments
     */
    public static void main(String[] args) {
        Application.launch(CalculatorUI.class, args);
    }
}
