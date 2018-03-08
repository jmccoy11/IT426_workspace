package rankings;

import strategies.IStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

//provides access to a list of employee names and yearly reviews
public class RankingsCalculator
{
	//all employee names (last names omitted)
	private Set<String> names;

	//a mapping of names to yearly reviews
	private Map<String, List<Review>> reviewData;

	public RankingsCalculator()
	{
		names = new HashSet <>();
		reviewData = new HashMap<>();

		loadData();
	}

	public void printRankings(IStrategy strategy)
    {
		//create a ranked list of employee reviews
		List<RankedReview> rankedList = new ArrayList<>();

		/*
		    1: Loop over each name available using the names Set<T>
		    2: Retrieve reviews for that employee using the reviewData Map<K, V>
		    3: Calculate a score for employee using the strategy parameter
		    4: Add a new RankedReview object for that employee and score to the rankedList
		       ArrayList<T>
		 */

		//sort our results
		RankedReview[] rankedReviews = rankedList.toArray(
				new RankedReview[rankedList.size()]);
		Arrays.sort(rankedReviews);

		//print out the ranked list
		for (int i = 0; i < rankedReviews.length; i++)
		{
			System.out.println(rankedReviews[i].getName() + ": " + rankedReviews[i].getScore());
		}
	}

	//loads fictitious data from a text file
	private void loadData()
	{
		try(Scanner reader = new Scanner(new FileInputStream("files/reviews.txt")))
		{
			//for each review in the file
			while (reader.hasNextLine())
			{
				String line = reader.nextLine();

				//retrieve each part of the review
				String[] parts = line.split(", ");
				String name = parts[0];
				int year = Integer.parseInt(parts[1]);
				int[] kpis = new int[parts.length - 2];

				//parse out the key performance indicators
				for (int i = 0; i < kpis.length; i++)
				{
					kpis[i] = Integer.parseInt(parts[i + 2]);
				}

				//record the name encountered
				names.add(name);

				//add the review
				if (!reviewData.containsKey(name))
				{
					reviewData.put(name, new ArrayList<>());
				}
				reviewData.get(name).add(new Review(name, year, kpis));
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Write error: " + ex.getMessage());
		}
	}

	//returns an iterator for the reviews of an employee
	public List<Review> getReviews(String name)
	{
		if (!reviewData.containsKey(name))
		{
			return null;
		}
		
		return Collections.unmodifiableList(reviewData.get(name));
	}

	//returns a list of employee names (with last names omitted)
	public Set<String> getNames()
	{
		return Collections.unmodifiableSet(names);
	}
}
