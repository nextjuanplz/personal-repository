//HW #7 Part 2 - Juan M. Alzate Vanegas
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void swap(int *, int*);
void printout(int[], int);

void main()
{
    srand(time(NULL));

    int i, j, n, list[100];

    puts("----Before Insertion Sort--------------------------------------------------\n");

    for (n = 0; n < 100; n++) //List creation and printing
    {
        list[n] = rand() % 10001;
    }

    printout(list, 100);

    puts("\n----After Insertion Sort--------------------------------------------------");

    for (i = 0; i < 100; i++) //Insertion sort algorithm
    {
        for (j = i; j!= 0 && list[j]<list[j-1]; j--)
        {
            swap(&list[j],&list[j-1]);
        }
    }

    printout(list, 100);
}

void swap (int * a, int * b) //Swaps values
{
    int hold = *a;
    *a = *b;
    *b = hold;
}

void printout(int array[], int size) //Prints out list
{
    int n;
    for (n = 0; n < size; n++)
    {
        printf("Element #%d = %d\n",n,array[n]);
    }
}
