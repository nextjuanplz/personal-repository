//HW #7 Part 12 - Juan M. Alzate Vanegas
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void main2()
{
    srand(time(NULL));

    int n, list[100];

    for (n = 0; n < 101; n++) //List creation and printing
    {
        list[n] = rand() % 10001;
        printf("Element #%d = %d\n",n,list[n]);
    }
}
