#include <iostream>
#include <cstdlib>
#include <ctime>
//Program that checks random numbers against user input; counts average # of numbers needed 50 times

int main()
{
    int input=0; //Number input by user
    int counter; //Counter for 50 experiments
    int sum = 0; //Sum of random_count for the 50 experiments
    float average = 0; //Average random_count for 50 experiments

    std::cout << "Please input a number between 0 and 99.\n";
    std::cin >> input;
	
    if (input < 0 || input > 99) //Checks that input is between 0 and 99
    {
        std::cout << "Input error.\n";
        return 0;
    }
    srand(time(NULL));
    for (counter = 0; counter < 50; counter++)
    {
		cExperiment c(input);
		c.runExperiment();
		sum += c.randomCount();
	}
	average = sum / 50;
	std::cout << "The average is " << average;
	return 0;
}

class cExperiment {

	// The variables below are private, so they cannot be accessed directly from outside the class. This protects them.

	int _input;
	int _count;
	
	public:
	
	// The object constructor is executed when an instance of the class is defined. It has no return type. It uses the same name as the class.
	// In this case the constructor is used to obtain the number that the user entered.
	cExperiment(int input) { 
		_input = input;
		_count=0;
	}
	
	// This function allows obtaining the value of the variable _count without exposing the variable, 
	// so it cannot be modified from outside the class.
	
	int randomCount(){ 
		return _count; 
	}
	
	// This function runs the experiment.
	
	void runExperiment(){
	    do
        {			
         int random_number = rand() % 100;
         _count++;
        } while (random_number != _input);
	}
}
 
