//HW #2: Simple Conditionals, Loops and User-defined functions
#include <stdio.h>
#include <stdlib.h>

//Prototype for function evaluating if user input is even or odd
char even_or_odd(int num)
{
    /*Function takes int and returns char 'E' (even), 'O' (odd), or 'F' (invalid input)*/
    int parity_check = 0, parity = 0;
    parity_check = num % 2;
    if (parity_check == 0)
    {
        parity = 'E';
    }
    else if (parity_check == 1)
    {
        parity = 'O';
    }
    return (parity);
}

int main()
{
    char username[256];
    int input = 1;
    int evens = 0;
    int odds = 0;
    int evens_sum = 0;
    int odds_sum = 0;
    char par_test;

    puts("Hello! What is your name?");
    scanf("%s",username);
    puts("\nPlease enter an integer value.");

    while ((input != 0))
    {
        scanf("%d",&input);
        par_test = even_or_odd(input);
        /*Evens*/ if (par_test == 'E')
            {
                printf("\nThe number is even.\n");
                evens = evens + 1;
                evens_sum = evens_sum + input;
                printf("Please enter another integer value.\nOtherwise, type in 0 to exit.\n");
            }
        /*Odds*/ else if (par_test == 'O')
            {
                printf("\nThe number is odd.\n");
                odds = odds + 1;
                odds_sum = odds_sum + input;
                printf("Please enter another integer value.\nOtherwise, type in 0 to exit.\n");
            }
    }
    printf("\n%s, the numbers you entered break down as follows:\n\n",username);
    printf("You entered %d even numbers, with a total value of %d.\n",evens,evens_sum);
    printf("You entered %d odd numbers, with a total value of %d.\n",odds,odds_sum);
    return (0);
}
