package cinemaTheater;
import java.util.*;

public class Movie implements Comparable<Movie>{
	
	String name;
	String genre;
	double ratings;
	double timing;
	Theater T;

	Movie()
	{
		name = "Unspecified";
		genre = "Unspecified";
		ratings = -1;
		timing = 0.0; 
		T = new Theater();	
	}
	
	Movie(String[] details)
	{
		name = details[0];
		genre = details[1];
		ratings = Double.parseDouble(details[2]);
		timing = Double.parseDouble(details[3]);
		T = new Theater();	
	}

	void get_movie()
	{
		Scanner sc = new Scanner(System.in);
		
 
		System.out.println("Enter the name");
		this.name = sc.nextLine();

		System.out.println("Enter the genre");
		this.genre =  sc.nextLine();

		System.out.println("Enter the ratings");
		this.ratings =  sc.nextFloat();
		
		System.out.println("Enter the timing");
		this.timing =  sc.nextFloat();
	}
	
	void book_seats()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of seats to book");
		int n = sc.nextInt();
	
		System.out.println("Enter the row of prefrence");
		int row = sc.nextInt();

		
		System.out.println("Enter the col of prefrence");
		int col = sc.nextInt();

		T.book_bulk(n, row, col);
	}
	
	public int compareTo(Movie m)
	{
		if(this.ratings<m.ratings)
		{
			return 1;
		}
		else if(this.ratings>m.ratings)
		{
			return -1;
		}
		return 0;
	}
	
	void  show()
	{
		System.out.println("==============================================================\n");
		System.out.println("Name: "+this.name+"");
		System.out.println("("+this.genre+")\n");
		System.out.println("ratings="+this.ratings+"\n");
		System.out.println("Time="+this.timing+"\n");
		System.out.println("==============================================================");
		
		System.out.println("===============================Status===============================");
		T.display_status();
		
	}
	
}