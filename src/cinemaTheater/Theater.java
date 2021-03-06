package cinemaTheater;
import java.io.Serializable;
import java.util.*;


class Seat implements Serializable
{
	boolean booked;
	
	Seat(){
		this.booked = false;
	}
	
	void book()
	{
		if(!this.booked)
		{
			this.booked = true;
		}
		else
		{
			System.out.println("Already booked");
		}
	}
	
	void cancel()
	{
		if(this.booked)
		{
			this.booked = false;
		}
		
		else
		{
			System.out.println("Already vacant");
		}
	}
	
	
}


public class Theater implements Serializable {

	int capacity;
	ArrayList<Seat> seats;
	
	Theater()
	{
		capacity = 64;
		
		seats = new ArrayList<Seat>(this.capacity);
		
		for(int i=0; i<64; i++)
		{
			Seat seat = new Seat();
			seats.add(seat);
		}
	}
	
	String book_bulk(int n,int row, int col)
	{
		Queue<Integer> cols = new LinkedList<Integer>();
		
		while(col<8 && cols.size()<n )
		{
			
			Seat seat = seats.get(row*8+col);
			
			if(!seat.booked)
			{
				cols.add(col);
			}
			col++;	
		}
		String str= "";
		if(cols.size()==n)
		{
			str+="====================================================\n";
			str+="====================Confirmation====================\n";
			str+="Booked seat numbers are\n";
			for(int i=0;i<n;i++)
			{
				col = cols.remove();
				seats.get(row*8+col).book();
				str+=(row+"-"+col+"\n").toString();
				
			}
			str+="====================================================\n";
		}		

		return str;
	}	

	void display_status()
	{
		int i = 0;
		for(Seat seat : seats)
		{
			char c = seat.booked ? '*' : '-';
			System.out.print(c+"\t");
			if((i+1)%8==0)
			{
				System.out.print("\n");
			}
			i++;      	                  		
		}
		System.out.print("\n");
	
	
	
	}
	
}