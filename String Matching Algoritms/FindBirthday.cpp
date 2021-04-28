#include<iostream>
#include<string>
#include<fstream>
#include<vector>
#include<sstream>

using namespace std;

fstream data_file; //for Pi.txt file
fstream write_file; //for results.txt file
vector<char> text_vector; //create vector

int main()
{
	int op,a,i,flag=0,x=0;
	char bday[6];
	string data,row,bd;
	
	data_file.open("pi.txt",ios::in); //open file to read   
	if(!data_file) 
		cout<<"File Not Existing."; 
	else
	{
		cout<<"Data Reading..."<<endl;
		while(data_file.eof()==0)
		{
			if(x<21) //Skip the lines until Pi value started
			{
				getline(data_file,row);
				x++;
			}
			else
			{
				getline(data_file,data);
				if(!data.empty()) //ignore empty lines
				{
					for(i=0;i<54;i++)
					{
						if(data.at(i)!=' ') //ignore spaces of a line
						{
							text_vector.push_back(data.at(i)); //insert char by char to the vector
						}
					}
				}
			}
		}
		
	}
	cout<<"No. of elements:"<<text_vector.size()<<endl;
	
	write_file.open("results.txt",ios::out); //write results to the results.txt file  when starting the program, data of the results.txt will be erased.   
	if(!write_file) 
		cout<<"Problem while updating the data file"<<endl;
	else
	{
		write_file<<"Results of String Matching Algorithms"<<endl;
		write_file<<"-------------------------------------"<<endl<<endl;
		write_file.close();
	}
			
	while(op!=5)
	{
		cout<<" * String Matching Algorithms *"<<endl<<endl;
		cout<<"--------------------------------"<<endl;
		cout<<"1. Naive String Matching Algorithm"<<endl;
		cout<<"2. KMP Algorithm"<<endl;
		cout<<"3. Rabin-Karp Algorithm"<<endl;
		cout<<"4. Boyer-Moore Algorithm"<<endl;
		cout<<"5. Exit"<<endl;
		
		cout<<"Enter Your Birthday String (YYMMDD): "; //get birthdy string
		cin>>bd;
		for(a=0;a<6;a++)
		{
			bday[a]=bd[a]; //set input to bday[] array
		}
		write_file.open("results.txt",ios::app);   //append birthdy string to results.txt file
		if(!write_file) 
			cout<<"Problem while updating the data file"<<endl;
		else
		{
			write_file<<"Birthday String:";
			for(a=0;a<6;a++)
			{
				write_file<<bday[a];
			}
			write_file<<endl;
			write_file.close();
		}
		cout<<"Select Your Searching Algorithm: ";
		cin>>op;
		
	}
	return 0;
}
