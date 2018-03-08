package edu.greenriver.it.baseball;

import java.util.HashMap;
import java.util.Map;

public class BaseballPlayer
{
    private String name;
    
    //eg. hitting percentage --> 0.300, position --> "first base"
    private Map<String, String> statLines = new HashMap<String, String>();
    
    public BaseballPlayer(String name, Map<String, String> statLines)
    {
        this.name = name;
        this.statLines = statLines;
    }

    //getters/setters
    public String getName()
    {
        return name;
    }

    public Map<String, String> getStatLines()
    {
        return statLines;
    }
}
