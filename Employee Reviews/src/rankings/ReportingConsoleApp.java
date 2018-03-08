package rankings;

import java.util.Scanner;

public class ReportingConsoleApp
{
    private static Console console;
    public static void main(String[] args)
    {
        console = new Console();
        /*
            1: display a welcome message for the user
            2: Prompt the user for a strategy: TotalStrategy, WeightedStrategy or SelectiveStrategy.
            3: Create the requested strategy object
            4: Create a RankingsCalculator object and print out the reviews using printRankings()
         */
    }
    
    private static void menuChoice() {
        String userInput = console.getString();
        
        switch(userInput) {
            case ("1"):
                System.out.println("1 chosen");
                break;
            case ("2"):
                System.out.println("2 chosen");
                break;
            case ("3"):
                System.out.println("3 chosen");
            default:
                System.out.println("Please choose a vaid option.");
        }
    }
}
