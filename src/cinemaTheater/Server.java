package cinemaTheater;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Server {

	static ServerSocket ss;
	static public void main(String[] args)
	{
		ArrayList<Movie> movies = new ArrayList<Movie>(); 
		read_movies(movies);
		try{
			ss = new ServerSocket(6669);
			Socket socket = ss.accept();
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			int choice = -1;
			while(choice!=4)
		    {
				choice = din.readInt();
				switch(choice)
				{
				case(1):
					out.writeObject(movies);
					break;
				case(2):
					out.writeObject(movies);
					int i = din.readInt();
					out.writeObject(movies.get(i-1));
					break;
				case(3):
					out.writeObject(movies);
					break;
				case(4):
					break;
				default:
					System.out.println("Enter the valid choice");
					break;
				
				}      
			
		}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
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
	
	static String show_all_movies(ArrayList<Movie> movies)
	{
		String str = "";
		int i = 1;
		for(Movie m: movies)
		{
			System.out.print("==============================="+i+"===============================\n");;
			i++;
			m.show();
			
		}
		return str;
	}
	
}