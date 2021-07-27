import java.util.*;

class Stack 
{
	private static final int MAX = 2;
	private int top;
	private String arr[];

	public Stack() 
	{ 
		top = -1;
		arr = new String[MAX];
	} 

	public int push(String var) 
	{ 
		if (isFull())								//line 1 
		{
			System.out.println("Stack Overflow"); 	//line 2
			return top; 							//line 3
		} 
		else 
		{
			arr[++top] = var;						//line 4
			return top; 							//line 5
		} 
	} 

	public int pop() 
	{
		if (isEmpty())						//line 1 
		{
			return 0;						//line 2 
		} 
		else 
		{ 
			String data = arr[top];			//line 3
			System.out.println(data);		//line 4
			top--;							//line 5
			return 1;						//line 6 
		} 
	}
	
	public boolean isFull() 
	{ 
		if (top == MAX-1)							//line 1 
		{
			System.out.println("Stack Overflow"); 	//line 2
			return true; 							//line 3
		}
		else
		{
			return false;							//line 4
		}
	}
	public boolean isEmpty() 
	{ 
		if (top == -1)								//line 1 
		{ 
			System.out.println("Stack Underflow"); 	//line 2
			return true; 							//line 3
		}
		else
		{
			return false;							//line 4
		}
	}
} 

public class HallStack  
{ 
	public static void main(String args[]) 
	{
		int op=0;
		String ID,loc,hall,hotel,guest_size;
		Scanner sc = new Scanner(System.in);
		Stack st1 = new Stack();//Stack for store Hotel ID
        Stack st2 = new Stack();//Stack for store Hall Name
        Stack st3 = new Stack();//Stack for store Hall Location
        Stack st4 = new Stack();//Stack for store No. of guest size
        Stack st5 = new Stack();//Stack for store Hotel Name

		
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
					st1.push(ID);
					System.out.print("Enter the Hall Name:");
					hall = sc.nextLine();
					st2.push(hall);
					System.out.print("Enter the Banquet Hall Location:");
					loc = sc.nextLine();
					st3.push(loc);
					System.out.print("Enter the Number of Guest Count:");
					guest_size = sc.nextLine();
					st4.push(guest_size);
					System.out.print("Enter the Hotel Name:");
					hotel = sc.nextLine();
					st5.push(hotel);
					System.out.println("Successfully Added to the Stack");
					break;
				
				case 2:
					System.out.print("Hall ID:");
					st1.pop();
					System.out.print("Hall Name:");
					st2.pop();
					System.out.print("Location:");
					st3.pop();
					System.out.print("Maximum No.of Guests:");
					st4.pop();
					System.out.print("Hotel Name:");
					st5.pop();
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
