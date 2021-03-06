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

//calculate longest proper suffix
void LPSfunc(char* bday, int* lps) 
{
	int len=0, i=1; 
	lps[0] = 0; 	//initiate 
 
	while (i<6) 
	{ 
		if (bday[i]==bday[len]) 
		{ 
			len++; 
			lps[i] = len; 
			i++; 
		} 
		else 
		{ 
			if (len!=0) 
			{ 
				len=lps[len-1];
			} 
			else
			{ 
				lps[i] = 0; 
				i++; 
			} 
		} 
	}
}
//KMP string matching algorithm
void KMP(char bday[], const vector<char> &text_vector)
{
	cout<<"KMP String Matching Algorithm"<<endl;
	 
	int lps[6],count=0; 
	LPSfunc(bday,lps); //calculate lps 

	int i = 0; // index for textt[] 
	int j = 0; // index for bday[] 
	while (i<text_vector.size()) 
	{ 
		if(bday[j]==text_vector.at(i)) 
		{ 
			j++; 
			i++; 
		} 

		if(j==6) //if all the characters matches
		{ 
			cout<<"BirthDay Found At : "<< i-j+1 <<endl;
			FileWrite(i-j+1,1);
            count++;
			j=lps[j-1]; 
		}
		else if(i<text_vector.size() && bday[j]!=text_vector.at(i)) // if mismatch after j matches 
		{ 
			if(j!= 0)
			{ 
				j=lps[j-1];
			}
			else
			{
				i=i+1;
			}
		} 
	}
	cout<<"No. of Results:"<<count<<endl<<endl;
	FileWrite(count,0); 
}

//Rabin-Karp string matching algorithm
void RK(char bday[], const vector<char> &text_vector, int p) 
{ 
	int i, j, h=1, d=10,count=0; 
	int hash_bday = 0; // hash value for birthday pattern 
	int hash_text = 0; // hash value for text 

	for (i=0;i<5;i++)	// h=pow(d, m-1)%p or h=(d^m-1)%p
	{ 
		h=(h*d)%p;
	}
	
	for (i=0;i<6;i++) 
	{ 
		hash_bday = (d * hash_bday + bday[i]) % p;				//hash value for birthday pattern 
		hash_text = (d * hash_text + text_vector.at(i)) % p;	//hash value for starting sub-string of text
	} 
	for (i=0;i<=text_vector.size()-6;i++) 
	{ 
		if (hash_bday == hash_text) 
		{ 
			for (j=0;j<6;j++) 
			{ 
				if (text_vector.at(i+j)!=bday[j]) 
				{
	                break;
	            }
			}
			if (j == 6)		//if all the characters matches
			{ 
				cout<<"BirthDay Found At : "<< i+1 <<endl;
				FileWrite(i+1,1);
				count++;
			}
		} 
		if (i<text_vector.size()-6) 
		{ 
			hash_text = (d*(hash_text - text_vector.at(i)*h) + text_vector.at(i+6))%p; 
			if (hash_text<0)
			{
				hash_text= (hash_text + p);
			}
		} 
	}
	cout<<"No. of Results:"<<count<<endl<<endl; 
	FileWrite(count,0);
}

//calculate bad character table
void BadCharacterFunc(char bday[], int bchar[]) 
{ 
	int i; 

	// Initialize all occurrences as -1 
	for (i=0;i<256;i++)
	{ 
		bchar[i] = -1;
	}

	// Fill the actual value of last occurrence (index) of a character 
	for (i=0;i<6;i++)
	{ 
		bchar[(int) bday[i]] = i;	//according to the ascii value
	}
}

//check maximum value and return it
int max(int a, int b)
{
    return (a > b) ? a : b;
}

//Boyer-Moore string matching algorithm
void BM(char bday[], const vector<char> &text_vector) 
{
	int bchar[256],j,shift=0,count=0; 

	BadCharacterFunc(bday,bchar); //calculate bad character table 

	while(shift <= text_vector.size()-6)	//read whole text 
	{ 
		j = 5; 
		while(j >= 0 && bday[j] == text_vector[shift+j]) //matching char by char from right to left
		{
			j--;
		}
		
		if (j < 0)		//if all the characters matches 
		{ 
			cout<<"BirthDay Found At : "<< shift+1 << endl;
			FileWrite(shift+1,1);
			count++; 
			if(shift + 6 < text_vector.size())	//continue string matching
			{
				shift = shift + 6 - bchar[text_vector.at(shift + 6)];
			}
			else
			{
				shift = shift + 1;
			} 
		}
		else
		{
			shift = shift + max(1, j - bchar[text_vector.at(shift + j)]);	
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
			case 2:
				write_file.open("results.txt",ios::app);   
				if(!write_file) 
					cout<<"Problem while updating the data file"<<endl;
				else
				{
					write_file<<"KMP String Matching Algorithm"<<endl;
					write_file.close();
				}
				KMP(bday,text_vector);
				break;
			case 3:
				write_file.open("results.txt",ios::app);   
				if(!write_file) 
					cout<<"Problem while updating the data file"<<endl;
				else
				{
					write_file<<"Rabin-Karp String Matching Algorithm"<<endl;
					write_file.close();
				}
				RK(bday,text_vector,11);
				break;
			case 4:
				write_file.open("results.txt",ios::app);   
				if(!write_file) 
					cout<<"Problem while updating the data file"<<endl;
				else
				{
					write_file<<"Boyer-Moore String Matching Algorithm"<<endl;
					write_file.close();
				}
				BM(bday,text_vector);
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
