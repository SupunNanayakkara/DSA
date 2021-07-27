import java.util.*;

class BHall
{
	String HID;
	String hotel;
	String HallName;
	String location;
	int SeatSize;
}
public class SelectionSort 
{ 
	public static void sort(BHall arr[],int arrSize) 
	{ 
		int i,j,n,pos;										//line 1
		n = arrSize;										//line 2

		for (i = 0; i < n-1; i++)							//line 3
		{  
			pos = i; 										//line 4
			for (j = i+1; j < n; j++)						//line 5
			{
				if (arr[j].SeatSize < arr[pos].SeatSize)	//line 6
				{					
					pos = j;								//line 7
				}
			}
			BHall temp = new BHall();						//line 8
			
			temp = arr[pos]; 								//line 9
			arr[pos] = arr[i]; 								//line 10
			arr[i] = temp; 									//line 11
		}
	}	

	// Prints the array 
	public static void printArray(BHall arr[],int arrSize) 
	{ 
		int i,n;
		n = arrSize;
		System.out.println("Banquet Hall ID		"+" Banquet Hall Name 	"+" Location	"+" Maximum No. of Guests		"+" Hotel Name");
		System.out.println("---------------		"+" -----------------	"+" --------	"+" ---------------------		"+" ----------");
		for(i=0;i<n;i++)
		{
			System.out.println(arr[i].HID+"				"+arr[i].HallName+"		  "+arr[i].location+"			"+arr[i].SeatSize+"			  "+arr[i].hotel);
		}
		System.out.println(); 
	} 

	public static void main(String args[]) 
	{ 
		int i,size,n;
		String id,hotel,name,loc;
		System.out.print("Enter No of Inputs:");
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		BHall[] arr = new BHall[n];
		for(i=0;i<n;i++)
		{
			sc.nextLine();
			arr[i] = new BHall();
			System.out.print("Enter Hall ID:");
			arr[i].HID = sc.nextLine();
			System.out.print("Enter Hotel Name:");
			arr[i].hotel = sc.nextLine();
			System.out.print("Enter Hall Name:");
			arr[i].HallName = sc.nextLine();
			System.out.print("Enter Hall Location:");
			arr[i].location = sc.nextLine();
			System.out.print("Enter Maximum Number of Guests:");
			arr[i].SeatSize = sc.nextInt();
			 
		}
		System.out.println("\t\t\t\tUnsorted Banquet Hall List\n"); 
		printArray(arr,n);
		sort(arr,n); 
		System.out.println("\t\t\t\tSorted Banquet Hall List\n"); 
		printArray(arr,n); 
	} 
} 