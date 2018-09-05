package cinemaTheater;


import java.io.*;
import java.net.*;
import java.util.*;



public class Client
{
  @SuppressWarnings("unchecked")
public static void main(String[] args) throws Exception
  {
	int choice = -1;
	ArrayList<Movie> movies;
	Movie m;
	
    Socket sock = new Socket("localhost", 6670);
 	ObjectInputStream oin = new ObjectInputStream(sock.getInputStream());
 	
 	Scanner sc = new Scanner(System.in);
    DataOutputStream dout = new DataOutputStream(sock.getOutputStream());  
    DataInputStream din = new DataInputStream(sock.getInputStream()); 
     while(choice!=4)
     {
    	 System.out.println("1.Movies \n2.Book \n3.Add new Show \n4.Exit");              
     	 choice = sc.nextInt();
     	 dout.writeInt(choice);
     	 
     	switch(choice)
		{
		case(1):
			movies = (ArrayList<Movie>)oin.readObject();
			show_all_movies(movies);
			break;
		case(2):
			movies = (ArrayList<Movie>)oin.readObject();
			show_all_movies(movies);
			int i = sc.nextInt();
			dout.writeInt(i);
			String str;
			str = din.readUTF();
			System.out.println(str);
			for(int j=0;j<3;j++)
			{
				i = sc.nextInt();
				dout.writeInt(i);
				str = din.readUTF();
				System.out.println(str);
			}
			break;
		case(3):
			movies = (ArrayList<Movie>)oin.readObject();
			Movie m2 = new Movie();
			m2.get_movie();
			movies.add(m2);
			break;
		case(4):
			break;
		default:
			System.out.println("Enter the valid choice");
			break;
		
		}
        
    }
     sc.close();
     sock.close();
     
     
  } 
	static void show_all_movies(ArrayList<Movie> movies)
	{
		int i = 1;
		for(Movie m: movies)
		{
			System.out.print("==============================="+i+"===============================\n");;
			i++;
			m.show();
			
		}
	
	}
  
} 