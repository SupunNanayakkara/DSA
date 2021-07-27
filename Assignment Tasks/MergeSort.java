import java.util.*;

class BHall
{
	String HID;
	String hotel;
	String HallName;
	String location;
	int SeatSize;
}
public class MergeSort 
{ 
	public static void merge(BHall arr[], int first, int mid, int last) 
	{ 
		int i, j, k; 								
		int n1 = mid - first + 1; 					
		int n2 = last - mid;
		BHall[] LeftArr = new BHall[n1];
		BHall[] RightArr = new BHall[n2]; 
		for (i = 0; i < n1; i++)
		{
			LeftArr[i] = new BHall();
			LeftArr[i] = arr[first + i];
		}
		for (j = 0; j < n2; j++)
		{
			RightArr[j] = new BHall();
			RightArr[j] = arr[mid + 1 + j]; 
		}

		i = 0; j = 0; k = first; 
		while (i < n1 && j < n2) 
		{ 
			if (LeftArr[i].SeatSize < RightArr[j].SeatSize) 
			{ 
				arr[k] = LeftArr[i]; 
				i++; 
			} 
			else
			{ 
				arr[k] = RightArr[j]; 
				j++; 
			} 
			k++; 
		} 
		while (i < n1) 
		{ 
			arr[k] = LeftArr[i]; 
			i++; 
			k++; 
		} 
		while (j < n2) 
		{ 
			arr[k] = RightArr[j]; 
			j++; 
			k++; 
		} 
	} 

	
	public static void sort(BHall arr[], int first, int last) 
	{ 
		if (first < last) 
		{ 
			int mid = (first+last)/2;		//line 1
			sort(arr, first, mid); 			//line 2
			sort(arr, mid+1, last); 		//line 3

			merge(arr, first, mid, last);	//line 4 
		} 
	}	

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
		sort(arr,0,n-1); 
		System.out.println("\t\t\t\tSorted Banquet Hall List\n"); 
		printArray(arr,n); 
	} 
} 