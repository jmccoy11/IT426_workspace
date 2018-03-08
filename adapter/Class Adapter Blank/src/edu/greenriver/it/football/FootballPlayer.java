package edu.greenriver.it.football;

public class FootballPlayer
{
    private String firstName;
    private String lastName;

    //eg. pass percentage - 67%\ninterceptions - {12}\nnickname - "bossy"
    private String statLine;

    public FootballPlayer(String firstName, String lastName,
            String statLine)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.statLine = statLine;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getStatLine()
    {
        return statLine;
    }
}
