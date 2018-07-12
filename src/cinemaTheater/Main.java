/*
 * TODO:
 * 1. Theater class
 * 2. Movie class - Each movie object has theater obj associated with it
 * 3. Main class
 * 4. Movies sorted according to ratings
 * 5. Data structure covered - Q, ArrayList    
 */

package cinemaTheater;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
	

	public static void main(String[] args) {

		ArrayList<Movie> movies = new ArrayList<Movie>(); 
		read_movies(movies);
		Scanner sc = new Scanner(System.in);
		int ch;
		
		do {
		System.out.println("1.Movies \n2.Book \n3.Add new Show \n4.Exit");
		ch = sc.nextInt();
		switch(ch)
		{
		case(1):
			show_all_movies(movies);
			break;
		case(2):
			show_all_movies(movies);
			int mc = sc.nextInt();
			movies.get(mc-1).book_seats();
			break;
		case(3):
			Movie new_movie  =  new Movie();
			new_movie.get_movie();
			movies.add(new_movie);
			Collections.sort(movies);
			show_all_movies(movies);
			break;
		case(4):
			break;
		default:
			System.out.println("Enter the valid choice");
			break;
		}
		}while(ch!=4);
	
		
	}
	
	static void read_movies(ArrayList<Movie> movies)
	{
		BufferedReader br;
		try {
			File file = new File("/home/sukhad/Workspace/Java/CinemaTheater/Movies.txt");
			br = new BufferedReader(new FileReader(file));
			String st;
			while((st = br.readLine())!=null)
			{
				String [] arrOstr = st.split(",",4);	
				Movie movie = new Movie(arrOstr);
				movies.add(movie);
				
			}
			
			Collections.sort(movies);
		br.close();
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
			return;
		}
		
		
	}
	
	static void show_all_movies(ArrayList<Movie> movies)
	{
		int i = 1;
		for(Movie m: movies)
		{
			System.out.println("==============================="+i+"===============================");
			i++;
			m.show();
		}
	}

}
