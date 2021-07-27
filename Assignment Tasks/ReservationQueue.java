import java.util.*;

class BHall 
{
	String HID;
	String HallName;
	String location;
	int SeatSize;
	String ResDate;
	String hotel;
	BHall nextH;

	public BHall(String ID,String hall,String loc, int guest_size, String res_date, String name) 
	{ 
		this.HID = ID; 
		this.HallName = hall;
		this.location = loc;
		this.SeatSize = guest_size;
		this.ResDate = res_date;
		this.hotel = name;
		this.nextH = null;
	} 
}

class customer 
{
	String name;
	String contact;
	String mail;
	String location;
	String ResDate;
	int SeatSize;
	customer nextC;

	public customer(String cus_name,String phone,String email,String loc,int guest_size, String res_date) 
	{ 
		this.name = cus_name; 
		this.contact = phone;
		this.mail = email;
		this.location = loc;
		this.SeatSize = guest_size;
		this.ResDate = res_date;
		this.nextC = null;
	} 
}

class QueueNode
{
	int RID;
	BHall hall_info;
	customer cus_info;
	int reply;
	String date;
	QueueNode nextR;
	
	public QueueNode(int id,BHall hall,customer cus,String dt)
	{
		this.RID = id;
		this.hall_info = hall;
		this.cus_info = cus;
		this.reply = 0;
		this.date = dt;
		this.nextR = null;
	}
}

class BHQueue 
{
	BHall front, rear; 

	public BHQueue() 
	{ 
		this.front = null;
	} 
	public BHall enqueue(String ID,String hall,String loc, int guest_size, String res_date, String name)
	{
		BHall node = new BHall(ID,hall,loc,guest_size,res_date,name);
		if (front == null || front.SeatSize > node.SeatSize) 
		{
			node.nextH = front; 
			front = node; 
		} 
		else 
		{
			BHall ptr = front;
			while (ptr.nextH != null && ptr.nextH.SeatSize < node.SeatSize) 
			{ 
				ptr = ptr.nextH; 
			}
			node.nextH = ptr.nextH; 
			ptr.nextH = node;
		}
		System.out.println("Successfully Added Hall Details."); 
		return front;
	}
	public void dequeue(BHall ptr) 
	{  
		if (front == null)
		{
			System.out.println("Reservation Queue is Empty.");
			return; 
		}
		else
		{
			BHall tmp = front;
			if(tmp==ptr)
			{ 
				front = front.nextH; 
			}
			BHall pretmp = tmp;
			while(tmp!=ptr)
			{
				pretmp = tmp;
				tmp = tmp.nextH;
			}
			pretmp.nextH = ptr.nextH;
			System.gc();
		}
	}
	public void Display()
	{
		System.out.println("\n--------------Banquet Hall List--------------\n");
		BHall ptr = front;
		while (ptr!= null) 
		{
			System.out.println("Hall : " +ptr.HID+"	"+ptr.SeatSize);
			ptr = ptr.nextH; 
		}
	}
	public void search(String loc, String res_date, int guest_size)
	{
		int count=0;
		BHall ptr = front;
		if(front == null)
		{
			return;
		}
		while(ptr!=null)
		{
			if(loc.equalsIgnoreCase(ptr.location) && !res_date.equals(ptr.ResDate) && guest_size<=ptr.SeatSize)
			{
				System.out.println("Hall ID: "+ptr.HID+"	Hall Name: "+ptr.HallName+"is available.");
				count = 1;
			}
			ptr = ptr.nextH;
		}
		if(count==0)
		{
			System.out.println("Currently not available a suitable Banquet Hall. Please use option 2 to waiting in Reservation Queue");
		}
	}
}

class ResQueue 
{
	customer front, rear; 

	public ResQueue() 
	{ 
		this.front = null;
		this.rear = null; 
	} 
	public customer enqueue(String cus_name,String phone,String email,String loc,int guest_size, String res_date)
	{
		customer cus_node = new customer(cus_name,phone,email,loc,guest_size,res_date);
		if (rear == null) 
		{ 
			front = cus_node;
			rear = cus_node;
		} 
		else
		{
			customer ptr = front;
			while(ptr.nextC != null)
			{
				ptr = ptr.nextC;
			}
			ptr.nextC = cus_node; 
			rear = cus_node;
		}
		System.out.println("Successfully added Customer Details.\n");
		return rear;
	}
	public void dequeue(customer ptr) 
	{  
		if (front == null)
		{
			System.out.println("Reservation Queue is Empty.");
			return; 
		}
		else
		{
			customer tmp = front;
			if(tmp==ptr)
			{ 
				front = front.nextC; 
			}
			customer pretmp = tmp;
			while(tmp!=ptr)
			{
				pretmp = tmp;
				tmp = tmp.nextC;
			}
			pretmp.nextC = ptr.nextC;
			System.gc();
		}
		if (front == null) 
		{
			this.rear = null;
		}
	}
	public void Display()
	{
		System.out.println("\n----------------Customer Queue----------------\n");
		customer ptr = front;
		while (ptr!= null) 
		{
			System.out.println("Customer Name : " + ptr.name);
			ptr = ptr.nextC; 
		}
	}
	public void searchBH(BHQueue bh,Queue wq,String dt)
	{
		int count=0,id;
		customer ptr = front;
		while(ptr!=null)
		{
			BHall hptr = bh.front;
			while(hptr!=null && count!=1)
			{
				if(hptr.location.equalsIgnoreCase(ptr.location) && ptr.SeatSize<=hptr.SeatSize)
				{
					System.out.println("Customer Name:"+ptr.name+"	Hall Name:"+hptr.HID);
					count = 1;
					dequeue(ptr);
					bh.dequeue(hptr);
					id = wq.getId()+1;
					wq.enqueue(id,hptr,ptr,dt);
				}
				hptr = hptr.nextH;
			}
			if(count==0)
			{
				System.out.println("NOT FOUND FOR "+ptr.name);
			}
			count = 0;
			ptr = ptr.nextC;
		}
	}
}

class Queue 
{
	QueueNode front, rear; 

	public Queue() 
	{ 
		this.front = null;
		this.rear = null; 
	} 
	public void enqueue(int id,BHall hall,customer cus,String dt)
	{
		QueueNode Rnode = new QueueNode(id,hall,cus,dt);
		if (rear == null) 
		{ 
			front = Rnode;
			rear = Rnode;
		} 
		else
		{
			QueueNode ptr = front;
			while(ptr.nextR != null)
			{
				ptr = ptr.nextR;
			}
			ptr.nextR = Rnode; 
			rear = Rnode;
		}
		System.out.println("Successfully added Reservation ID:" +id+ " to Resevation Queue.\n");
	}
	public void dequeue(QueueNode ptr) 
	{
		if (front == null)
		{
			System.out.println("Reservation Queue is Empty.");
			return; 
		}
		else
		{
			QueueNode tmp = front;
			if(tmp==ptr)
			{ 
				front = front.nextR; 
			}
			QueueNode pretmp = tmp;
			while(tmp!=ptr)
			{
				pretmp = tmp;
				tmp = tmp.nextR;
			}
			pretmp.nextR = ptr.nextR;
			System.gc();
		}
		if (front == null) 
		{
			this.rear = null;
		}
	}
	public void Display()
	{
		System.out.println("\n--------------Reservation Queue--------------\n");
		QueueNode ptr = front;
		while (ptr!= null) 
		{
			System.out.println("Reservation: "+ptr.RID+ "	" + ptr.cus_info.name+"	"+ptr.hall_info.HallName+"	"+ptr.reply+"	"+ptr.date);
			ptr = ptr.nextR; 
		}
	}
	public int getId()
	{
		QueueNode ptr = front;
		int id;
		if(front==null)
		{
			id = 0;
		}
		else
		{
			while (ptr.nextR!= null) 
			{
				ptr = ptr.nextR; 
			}
			id = ptr.RID;
		}
		return id;
	}
	public void confirm(int id)
	{
		QueueNode ptr = front;
		while (ptr != null) 
		{
			if(ptr.RID == id)
			{
				ptr.reply = 1;
				System.out.println("Reservation is Confirmed.\n");
				return;
			}
			ptr = ptr.nextR; 	
		}
		System.out.println("\nReservation Id is not in Queue.");
	}
	public void setChange(int id, String loc,String res_date,int guest_size, BHQueue bh, ResQueue rq)
	{
		QueueNode ptr = front;
		while (ptr != null) 
		{
			if(ptr.RID == id)
			{
				ptr.cus_info.location = loc;
				ptr.cus_info.ResDate = res_date;
				ptr.cus_info.SeatSize = guest_size;
				dequeue(ptr);
				rq.enqueue(ptr.cus_info.name, ptr.cus_info.contact, ptr.cus_info.mail, ptr.cus_info.location, ptr.cus_info.SeatSize, ptr.cus_info.ResDate);
				bh.enqueue(ptr.hall_info.HID, ptr.hall_info.HallName,  ptr.hall_info.location, ptr.hall_info.SeatSize, ptr.hall_info.ResDate, ptr.hall_info.hotel);
				System.out.println("Successfully Change Reservation Details.\n");
				return;
			}
			ptr = ptr.nextR; 	
		}
		System.out.println("\nReservation Id is not in Queue.");
	}
	public void delRes(int id, BHQueue bh)
	{
		QueueNode ptr = front;
		while (ptr != null) 
		{
			if(ptr.RID == id)
			{
				dequeue(ptr);
				bh.enqueue(ptr.hall_info.HID, ptr.hall_info.HallName,  ptr.hall_info.location, ptr.hall_info.SeatSize, ptr.hall_info.ResDate, ptr.hall_info.hotel);
				System.out.println("Successfully Delete Reservation.\n");
				return;
			}
			ptr = ptr.nextR; 	
		}
		System.out.println("\nReservation Id is not in Queue.");
	}
	public void remove(BHQueue bh,ResQueue rq,String dt)
	{
		QueueNode ptr = front;
		while (ptr != null) 
		{
			if(ptr.reply == 0 && !dt.equals(ptr.date))
			{
				dequeue(ptr);
				rq.enqueue(ptr.cus_info.name, ptr.cus_info.contact, ptr.cus_info.mail, ptr.cus_info.location, ptr.cus_info.SeatSize, ptr.cus_info.ResDate);
				bh.enqueue(ptr.hall_info.HID, ptr.hall_info.HallName,  ptr.hall_info.location, ptr.hall_info.SeatSize, ptr.hall_info.ResDate, ptr.hall_info.hotel);
				System.out.println("\nReservation Id:"+ptr.RID+" is Removed from Reservation Queue.");
			}
			ptr = ptr.nextR; 	
		}
	}
}   
 
public class ReservationQueue 
{ 
	public static void main(String[] args) 
	{ 
		int op=0,guest_size,n,rec,res_id;
		String ID,loc,res_date,cus_name,phone,email,hall,hotel,today;
		Scanner sc = new Scanner(System.in);
		BHQueue bh = new BHQueue();
		ResQueue rq = new ResQueue();
		Queue wq = new Queue();
		
		System.out.print("Enter Today Date (dd-mm-yyyy):");
		today = sc.nextLine();
		
		/*bh.enqueue("H1","ABC","Galle",150,"12-03-2020","KING HOTEL"); 
		bh.enqueue("H2","BCD","Galle",120,"11-03-2020","KING HOTEL");
		bh.enqueue("H3","BCA","Matara",110,"12-03-2020","SEA HOTEL");
		bh.enqueue("H4","ABCA","Kandy",200,"10-03-2020","KANDY HOTEL");
		bh.enqueue("H5","ABCD","Kandy",100,"10-03-2020","KANDY HOTEL");*/
		
		
		System.out.print("If you want to enter Banquet Halls information to Queue(YES-1 NO-0):");
		n = sc.nextInt();
		sc.nextLine();
		if(n==1)
		{
			System.out.print("Total Number of Records You Want to Enter:");
			rec = sc.nextInt();
			sc.nextLine();
			while(rec!=0)
			{
				System.out.print("Enter Hall ID:");
				ID = sc.nextLine();
				System.out.print("Enter the Hall Name:");
				hall = sc.nextLine();
				System.out.print("Enter the Banquet Hall Location:");
				loc = sc.nextLine();
				System.out.print("Enter the Reservation Date (dd-mm-yyyy):");
				res_date = sc.nextLine();
				System.out.print("Enter the Number of Guest Count:");
				guest_size = sc.nextInt();
				sc.nextLine();	
				System.out.print("Enter the Hotel Name:");
				hotel = sc.nextLine();
				bh.enqueue(ID,hall,loc,guest_size,res_date,hotel);
				rec--;
			}
		}
		while(op!=8)
		{
			System.out.println("\n");
			System.out.println("Banquet Hall Reservation System");
			
			System.out.println("1.Search a Banquet Hall");
			System.out.println("2.Insert Customer Details");
			System.out.println("3.Reservation Confirmation");
			System.out.println("4.Change Reservation Details");
			System.out.println("5.Delete Reservation");
			System.out.println("6.Remove not Confirmed Reservation");
			System.out.println("7.Display Queue Details");
			System.out.println("8.Exit\n");
			
			System.out.print("Enter the Option:");
			op = sc.nextInt();
			sc.nextLine();
			switch(op)
			{
				case 1:
					System.out.print("Enter the Banquet Hall Location:");
					loc = sc.nextLine();
					System.out.print("Enter the Reservation Date (dd-mm-yyyy):");
					res_date = sc.nextLine();
					System.out.print("Enter the Number of Guest Count:");
					guest_size = sc.nextInt();					
					sc.nextLine();
					bh.search(loc,res_date,guest_size);
					break;
				
				case 2:
					System.out.print("Enter Your Name:");
					cus_name = sc.nextLine();
					System.out.print("Enter the Contact Number:");
					phone = sc.nextLine();
					System.out.print("Enter the Email Address (if available) name@mail.com:");
					email = sc.nextLine();
					System.out.print("Enter the Banquet Hall Location:");
					loc = sc.nextLine();
					System.out.print("Enter the Reservation Date (dd-mm-yyyy):");
					res_date = sc.nextLine();
					System.out.print("Enter the Number of Guest Count:");
					guest_size = sc.nextInt();
					sc.nextLine();
					rq.enqueue(cus_name,phone,email,loc,guest_size,res_date);
					rq.searchBH(bh,wq,today);
					break;
				case 3:
					System.out.print("Enter Reservation ID:");
					res_id = sc.nextInt();
					sc.nextLine();
					wq.confirm(res_id);
					break;
				case 4:
					System.out.print("Enter Reservation ID:");
					res_id = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter new Banquet Hall Location:");
					loc = sc.nextLine();
					System.out.print("Enter new Reservation Date (dd-mm-yyyy):");
					res_date = sc.nextLine();
					System.out.print("Enter the Number of Guest Count:");
					guest_size = sc.nextInt();					
					sc.nextLine();
					wq.setChange(res_id,loc,res_date,guest_size,bh,rq);
					break;
				case 5:
					op = 0;
					System.out.print("Enter Reservation ID:");
					res_id = sc.nextInt();
					sc.nextLine();
					System.out.print("Are You sure, Do you want to Delete Reservation (YES-1 NO-0):");
					op = sc.nextInt();
					sc.nextLine();
					if(op==1)
					{
						wq.delRes(res_id,bh);
					}
					break;
				case 6:
					wq.remove(bh,rq,today);
					break;
				case 7:
					rq.Display();
					bh.Display();
					wq.Display();
					break;
				case 8:
					System.exit(0);
					break;
				default:
					System.out.println("Undifined Input");
			}
		}
	} 
} 
