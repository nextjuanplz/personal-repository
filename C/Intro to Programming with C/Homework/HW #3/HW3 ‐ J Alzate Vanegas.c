//Juan Manuel Alzate Vanegas
//HW 3 - Program that checks random numbers against user input; computes average # of numbers needed 50 times

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void main()
{
    int input; //Number input by user
    int output; //# of numbers run by a given experiment
    int counter; //Counter for 50 experiments
    int random_number = 0; //Generated pseudorandom number
    int random_count = 0; //Counter for # of numbers it takes to match random_number with input
    int sum = 0; //Sum of random_count for the 50 experiments
    float average = 0; //Average random_count for 50 experiments

    printf("Please input a number between 0 and 99.\n");
    scanf("%d",&input);

    while (input < 0 || input > 99) //Input check, which allows user to retry if input != [0,99]
    {
     printf("Error. Please try again.\n");
     scanf("%d",&input);
    }

    srand(time(NULL)); //rand() seed

    for (counter = 0; counter < 50; counter++) //Outer loop for 50 experiments
    {
        do
        {
         random_number = rand() % 100;
         random_count++;
         /*printf("Random number = %d\n",random_number);*/
        } while (random_number != input); //Inner loop for checking if random number matches input

        sum = sum + random_count; //Adds up the # of random numbers needed

        /*printf("Experiment %d ran %d times.\n",counter,random_count);*/
        random_count = 0;
    }
    average = sum/50;
    printf("The average is %f.\n",average);
}
