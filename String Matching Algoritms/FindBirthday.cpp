#include<iostream>
#include<string>
#include<fstream>
#include<vector>
#include<sstream>

using namespace std;

fstream data_file; //for Pi.txt file
fstream write_file; //for results.txt file
vector<char> text_vector; //create vector

//append results to the results.txt file
void FileWrite(int data,int n)
{
	write_file.open("results.txt",ios::app);   
	if(!write_file) 
		cout<<"Problem while updating the data file"<<endl; 
	else
	{
		if(n==1)
		{
			write_file<<"BirthDay Found At: "<<data<<endl;
		}
		else
		{
			write_file<<"No. of Results: "<<data<<endl<<endl;;
		}
		write_file.close();
	}
}

//naive string search algorithm
void Naive(char bday[], const vector<char> &text_vector)
{
	cout<<"Naive String Matching Algorithm"<<endl;
	
	int i,j,count=0;
    for(i=0;i<text_vector.size();i++) //read text string  
	{
        for(j=0;j<6;j++) //read birthday pattern 
		{
            if(text_vector.at(i+j)!=bday[j]) //if missmatch after j matches
			{
                break;
            }
        }

        if(j==6) //if all the characters matches
		{
            cout<<"BirthDay Found At : "<< i+1 <<endl;
            FileWrite(i+1,1);
            count++; //count no. of matches
        }
    }
    cout<<"No. of Results:"<<count<<endl<<endl;
    FileWrite(count,0);
}


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
		
		switch(op)
		{
			case 1:
				write_file.open("results.txt",ios::app);   
				if(!write_file) 
					cout<<"Problem while updating the data file"<<endl;
				else
				{
					write_file<<"Naive String Matching Algorithm"<<endl;
					write_file.close();
				}
				Naive(bday,text_vector);								
				break;
			case 5:
				exit(0);
			default:
				cout<<"Invalid Option";
				break;
		}
		
	}
	return 0;
}
