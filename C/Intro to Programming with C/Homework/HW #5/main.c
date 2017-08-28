//Juan M. Alzate Vanegas - HW #5 Part 1
//Sequence of events recorder (array populator)
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define END 1177

//Structure data type Data_Slice
typedef struct data_slice {
    int brake_fluid_pressure; //20 to 30 PSI
    int electrical_power; //10 to 100 W
    int brake_pressure; // 30 to 40 PSI
    int disk_pressure; // 10 to 15 PSI
    float t; // 0.1 to 10.0 s
}Data_slice;

Data_slice data[100]; //Array of 100 structs

//User-defined function prototypes
void populate_array(Data_slice[], int, int);
void write_to_file(Data_slice[], int);

void main()
{
    int n = 0; //counter for 1177 runs
    srand(time(NULL)); //rand() seed

    populate_array(data,100,n);
    write_to_file(data,100);
}

void populate_array(Data_slice a[],int b, int counter) //Populates array with 100 structs for 1177 runs
{
    int k; //Counter for circular array
    b = 100; //Array size

    for (counter = 0; counter <= END; counter++)
    {
        for (k = 0; k <= b; k++)
        {
            //Array population using specified ranges
            a[k].brake_fluid_pressure = (rand() % 10) + 20;
            a[k].electrical_power = (rand() % 90) + 10;
            a[k].brake_pressure = (rand() % 10) + 30;
            a[k].disk_pressure =(rand() % 5) + 10;

            //Time - set up to increase with k
            a[k].t = a[k-1].t + 0.1;

            //Counter which terminates loop
            counter ++;
            if (k + 1 > b) k = 0; //Circular array - ensures infinite loop
            if (counter >= END) break; //Ends array population after 1177 measurements
        }
    }
}

void write_to_file(Data_slice a[],int b) //Writes last iteration of array at counter = 1177 to file
{
    int x;

    FILE * fout;
    fout = fopen("data.txt","w");
    if (fout == NULL) printf("The file could not be opened.\n"); //Check if file could be opened

    for (x = 0; x < 100; x++) //Loop that prints array contents to data.txt
    {
        fprintf(fout, "%.1f\t", a[x].t); //Time only written to 1 significant figure
        fprintf(fout, "%d\t", a[x].brake_fluid_pressure);
        fprintf(fout, "%d\t", a[x].disk_pressure);
        fprintf(fout, "%d\t", a[x].brake_pressure);
        fprintf(fout, "%d\n", a[x].electrical_power);
    }

    fclose(fout); //File close
}
