package edu.greenriver.it.sportsmagazine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.greenriver.it.baseball.BaseballPlayer;
import edu.greenriver.it.basketball.BasketballPlayer;
import edu.greenriver.it.football.FootballPlayer;

public class SportsMagazine
{
    public static void main(String[] args)
    {
        //ichiro suzuki
        Map<String, String> ichiroStats = new HashMap<String, String>();
        ichiroStats.put("hits", "262");
        ichiroStats.put("batting average", ".368");
        BaseballPlayer ichiro = new BaseballPlayer("Ichiro Suzuki", ichiroStats);
        
        //lebron james
        BasketballPlayer lebron = new BasketballPlayer("Lebron", "James", Arrays.asList(
                new String[] {"field goal: 53%", "free throws: 90%"}));
        
        //russell wilson
        FootballPlayer russell = new FootballPlayer("Russell", "Wilson", 
                                                    "completion: 70%\ninterceptions: 2");
        
        //create an array of athletes
        for (;;)
        {
            //print out athlete information here...
        }
    }
}
