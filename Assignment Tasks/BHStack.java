import java.util.*;

class Node
{
	String HID;
	String HallName;
	String location;
	int SeatSize;
	String hotel;
	
	public Node(String ID,String hall,String loc, int guest_size, String name) 
	{ 
		this.HID = ID; 					//line 1
		this.HallName = hall;			//line 2
		this.location = loc;			//line 3
		this.SeatSize = guest_size;		//line 4
		this.hotel = name;				//line 5
	}
}
class Stack 
{
	private static final int MAX = 100;
	private int top;
	private Node arr[];

	public Stack() 
	{ 
		top = -1;
		arr = new Node[MAX];
	} 

	int push(String ID,String hall,String loc, int guest_size, String name) 
	{ 
		if (isFull())											//line 1 
		{
			System.out.println("Stack Overflow");				//line 2 
			return top;											//line 3 
		} 
		else 													
		{
			Node info = new Node(ID,hall,loc,guest_size,name);	//line 4
			top++;												//line 5
			arr[top] = info;									//line 6
			return top;											//line 7	 
		} 
	} 

	int pop() 
	{
		if (isEmpty())											//line 1 
		{
			return 0;											//line 2 
		} 
		else 
		{ 
			String ID = arr[top].HID;							//line 3
			String hall = arr[top].HallName;					//line 4
			String loc = arr[top].location;						//line 5
			int guest_size = arr[top].SeatSize;					//line 6
			String name = arr[top].hotel;						//line 7
			System.out.println("Hall ID:"+ID+"	Hall Name:"+hall+"	Location:"+loc+"	Maximum No.of Guests:"+guest_size+"	Hotel Name:"+name);
			top--;												//line 8
			return 1;											//line 9 
		} 
	}
	
	boolean isFull() 
	{ 
		if (top == MAX-1) 							//line 1
		{
			System.out.println("Stack Overflow");	//line 2 
			return true;							//line 3 
		}
		else										
		{
			return false;							//line 4
		}
	}
	boolean isEmpty() 
	{ 
		if (top == -1) 								//line 1
		{ 
			System.out.println("Stack Underflow");	//line 2 
			return true; 							//line 3
		}
		else
		{
			return false;							//line 4
		}
	}
} 

public class BHStack  
{ 
	public static void main(String args[]) 
	{
		int op=0,guest_size;
		String ID,loc,hall,hotel;
		Scanner sc = new Scanner(System.in);
		Stack st = new Stack(); 
		st.push("H1","ABC","Galle",150,"KING HOTEL"); 
		st.push("H2","BCD","Galle",120,"KING HOTEL");
		st.push("H3","BCA","Matara",110,"SEA HOTEL");
		st.push("H4","ABCA","Kandy",200,"KANDY HOTEL");
		st.push("H5","ABCD","Kandy",100,"KANDY HOTEL");
		
		while(op!=3)
		{
			System.out.println("\nDisplay Banquet Hall from Newest to Oldest\n");
				
			System.out.println("1.Insert Banquet Hall Details");
			System.out.println("2.Display Banquet Hall");
			System.out.println("3.Exit\n");
				
			System.out.print("Enter the Option:");
			op = sc.nextInt();
			sc.nextLine();
			switch(op)
			{
				case 1:
					System.out.print("Enter Hall ID:");
					ID = sc.nextLine();
					System.out.print("Enter the Hall Name:");
					hall = sc.nextLine();
					System.out.print("Enter the Banquet Hall Location:");
					loc = sc.nextLine();
					System.out.print("Enter the Number of Guest Count:");
					guest_size = sc.nextInt();
					sc.nextLine();	
					System.out.print("Enter the Hotel Name:");
					hotel = sc.nextLine();
					st.push(ID,hall,loc,guest_size,hotel);
					break;
				
				case 2:
					st.pop();
					break;
					
				case 3:
					System.exit(0);
					break;

				default:
					System.out.println("Undifined Option.");
					break;
			}
		}
	}
} 
