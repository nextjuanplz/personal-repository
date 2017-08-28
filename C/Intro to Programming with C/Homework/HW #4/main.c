//Juan Manuel Alzate Vanegas
//HW 4 - Mini-weather station - prints temperatures, max/min, and averages for every day in 2016

//Pre-processor directives
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <time.h>
#define SIZE 24

enum months {JAN = 1, FEB, MAR, APR, MAY, JUN, JUL, AUG, OCT, NOV, DEC}; //Enumeration for month labels

//User-defined functions
int get_value(); //Returns random int from 60 to 100
void calc_results(int[], int); //Accepts arrays; prints min/max and daily averages

void main()
{
    int size = SIZE;
    int time_of_day, day, daily_temp [size];

    //Month labels
    enum months month;
    char *monthName [] = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};
    int days_in_month [12] = {31,29,31,30,31,30,31,31,30,31,30,31}; //Number of days in each month for 2016

    //rand() seed
    srand(time(NULL));

    //Loop for month
    for (month = JAN; month <= DEC; month++)

        //Loop for day of month
        for (day = 1; day <= days_in_month[month]; day++)
            {
            printf("Temperature conditions on %s %d 2016:\n",monthName[month],day);
            printf("\tTime of day\tTemperature in degrees F\n");

            //Loop for time of day
            for (time_of_day = 0; time_of_day < size; time_of_day++) //Populates the array daily_temp
                {
                daily_temp [time_of_day] = get_value();
                printf("\t     %d\t\t\t   %d\n",time_of_day,daily_temp[time_of_day]); //Prints all temperatures for day
                }
            calc_results(daily_temp, size); //Prints max, min, & avg. for day
            }
}

int get_value()
{
   return (rand() % 40) + 60;
}

void calc_results(int daily_temp[], int size)
{
    float daily_average, daily_sum = 0; //floats for average
    int min = 101, max = 0; //ints for max and min checks

    //Loop checking for each daily_temp array element
    int time_of_day = 0; //counter for hour
    for (time_of_day = 0; time_of_day < size; time_of_day++)
    {
        //Sum of temperatures for all hours of the day
        daily_sum = daily_sum + daily_temp[time_of_day];

        //Max & Min check
        if (daily_temp[time_of_day] > max)
            max = daily_temp[time_of_day];
        if (daily_temp[time_of_day] < min)
            min = daily_temp[time_of_day];
    }

    //Average check
    daily_average = daily_sum / size;

    //Prints min, max, and daily avg
    printf("Minimum temperature for the day: %d degrees F.\n",min);
    printf("Maximum temperature for the day: %d degrees F.\n",max);
    printf("Average temperature for the day: %.2f degrees F.\n",daily_average);
    printf("--------------------------------------------------------\n");
}
