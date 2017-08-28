#include <stdio.h>
#include <stdlib.h>

int mod_return (int, int);

int main()
{
    int n = 3;
    int a, upper_limit, res;
    puts("What would you like to be the upper limit?");
    scanf("%d", &upper_limit);

    do
    {


    } while (n <= upper_limit);
}

int mod_return(int num, int a)
{
    int res = num % a;
    return res;
}
