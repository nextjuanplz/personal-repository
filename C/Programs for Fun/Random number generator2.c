//Random number generator (v. 2)
//by Juan M. Alzate Vanegas
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int random_number(int min, int max);
int main(void)
{
//Variable declaration
    int i, n, x;

    time_t t;

//Inputs
    puts("How many pseudorandom numbers do you need?"); /*Specifies n of numbers to print*/
    scanf("%d",&n);
    puts("Maximum value?"); /*Specifies maximum number that can appear*/
    scanf("%d",&x);
    x = x + 1; /*Allows for the maximum value to be a possibility*/
    srand((unsigned)time(&t)); /*Time seed*/

//Output
    printf("----------------Pseudorandom Numbers----------------\n"); /*Used for legibility*/

    for (i = 0; i < n; i++)
    {
        int pseudorandom = rand() %x;
        printf("%2d\n",pseudorandom); /*Right-justified for single-digit numbers; WIP*/
    }
}
