package strategies;

import rankings.Review;

import java.util.List;

public interface IStrategy
{
    public int getScore(int year, List<Review> reviews);
}
