package cinemaTheater;

import java.net.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

class ClientHandler extends Thread
{
	final Socket socket;
	final ObjectOutputStream out;
	final DataInputStream din;
	final DataOutputStream dout;
	final Database db;
	ArrayList<Movie> movies;
	
	
	
	ClientHandler(Socket socket,ObjectOutputStream out,DataInputStream din,DataOutputStream dout,ArrayList<Movie> movies,Database db)
	{
		this.socket = socket;
		this.out = out;
		this.din = din;
		this.dout = dout;
		this.movies = movies;
		this.db = db;
	
	}
	
	public void run()
	{	Movie movie;
		Statement stm = db.connect();
		int choice = -1;
	try{
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
				movie = movies.get(i-1);
				movie.book_seats(din,dout);
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
}

public class Server {

	static Connection connection;
	static ServerSocket ss;
	
	static public void main(String[] args) throws IOException
	{
		ArrayList<Movie> movies = new ArrayList<Movie>(); 
		read_movies(movies);
		
		Database db = new Database();
		
		System.out.println("Im out");
		// Server socket
		ss = new ServerSocket(6670);
		while(true)
		{
			try{
				
				Socket socket = null;
				socket = ss.accept();

				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
				DataInputStream din = new DataInputStream(socket.getInputStream());
				DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
				
				Thread t = new ClientHandler(socket,out,din,dout,movies,db);
				t.start();
					
			}catch(Exception e)
			{
				e.printStackTrace();
			}

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
