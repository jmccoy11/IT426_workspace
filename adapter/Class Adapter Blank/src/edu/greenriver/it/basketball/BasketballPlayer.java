package edu.greenriver.it.basketball;

import java.util.List;

public class BasketballPlayer
{
    private String firstName;
    private String lastName;
    
    //eg. "free throw percentage: 0.87", "rebounds per game: 10", ...
    private List<String> stats;

    public BasketballPlayer(String firstName, String lastName,
            List<String> stats)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stats = stats;
    }

    //getters/setters
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public List<String> getStats()
    {
        return stats;
    }
}
