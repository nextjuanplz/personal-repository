#include <stdio.h>
#include <stdlib.h>

//Structure data type Data_Slice
typedef struct data_slice {
    int brake_fluid_pressure; //20 to 30 PSI
    int electrical_power; //10 to 100 W
    int brake_pressure; // 30 to 40 PSI
    int disk_pressure; // 10 to 15 PSI
    float t; // 0.1 to 10.0 s
}Data_slice;

Data_slice data_save[100]; //Temporary array

void main()
{
    int k;
    FILE * fin;
    fin = fopen("data.txt","r");
    if (fin == NULL) printf("The file could not be read.\n"); //Check if data.txt was opened

    //Labels
    printf(" Time\tBrake fluid\tPressure\tDriver brake\tElectrical power to\n");
    printf("(secs)\t  pressure\ton disks\t  pressure\t\tunit\n");
    printf("\t   (PSI)\t  (PSI)\t\t   (PSI)\t\t(W)\n\n");

    for (k = 0; k < 100; k++)
    {
        //Reads data.txt
        fscanf(fin,"%f%d%d%d%d",&data_save[k].t,&data_save[k].brake_fluid_pressure,&data_save[k].disk_pressure,&data_save[k].brake_pressure,&data_save[k].electrical_power);

        //Prints contents
        printf(" %.1f\t    ", data_save[k].t);
        printf("%d\t\t   ", data_save[k].brake_fluid_pressure);
        printf("%d\t\t    ", data_save[k].disk_pressure);
        printf("%d\t\t\t", data_save[k].brake_pressure);
        printf("%d\n", data_save[k].electrical_power);
    }
    fclose(fin); //File close
}
