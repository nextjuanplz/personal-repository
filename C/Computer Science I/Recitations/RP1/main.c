//Prime Sum - Juan M. Alzate Vanegas
//1-20-2017
#include <stdio.h>
#include <stdlib.h>
#define SIZE 104730    //Array size, checking up to 104,730 for n <= 10,000 primes

//User-defined functions
int* create_array(int);         //Creates a dynamically-allocated array to SIZE
int sum_prime(int*, int);       //P(n): sums the primes of an array up to a given test value n
void free_memory(int*, int);    //Frees up memory from the dynamically-allocated array, as well as the array itself

void main() {
    //Counters
    int t;              //number of test cases
    int i = 1;          //counter for scanning cases
    int n = 0;          //number of primes to be summed

    //Create the array dynamically
    int* myArray = create_array(SIZE);

    //Scan for number of test cases
    scanf("%d",&t);

    //Check for t <= 20000
    while (t > 20000) {
        puts("Too many. Try again.");
        scanf("%d",&t);
    }

    //Sums primes up to case t
    for (i = 1; i <= t; i++) {
        scanf("%d",&n);
        if (n <= 10000) //Check for n < 10000
            sum_prime(myArray,n);
    }

    //Free dynamically-allocated memory
    free_memory(myArray,SIZE);
}

int* create_array(int array_Size) {
    //Counters
    int i;             //counter for each integer after 1
    int k;             //counter for multiples of i

    int* Array = calloc(array_Size, sizeof(int)); //Dynamic allocation to array

    if (Array != NULL) {                         //Check that the array was allocated to memory dynamically
        //Set 0 and 1 as "false" (non-prime)
        Array[0] = 0;
        Array[1] = 0;

        //Set every integer after 1 as "true" (prime)
        for(i = 2; i < array_Size; i++)
            Array[i] = 1;

        //Set multiples of i after 1 as "false" (non-prime)
        for (i = 2; i < array_Size; i++) {
            k = 2;
            if(Array[i] != 0) {
                while((i * k) < array_Size) {
                    Array[i * k] = 0;
                    k++;                        //Multiples go from 2i, 3i... smaller than array_Size
                }
            }
        }
    }
    return(Array);                              //Returns ptr to the first element of the array
}

int sum_prime(int* Array, int n) {  //Sums the primes of an array up to a given test value n
    //Counters
    int i = 0;          //counter for integers in the array
    int sum = 0;        //sum of primes up to the ith integer
    int j = 0;          //number of primes summed

    while (j < n && i < SIZE) {     //Only counts up to the integer SIZE, and only counts up to n primes
        if (Array[i] == 1) {            //Checks that an integer in the array is prime
            sum = sum + i;              //Adds prime number i to the sum
            j++;                        //Counts that a prime number has been added
        }
        i++;                        //Goes to the next integer i
    }

    //Error if program does not check n cases but i exceeds SIZE, or vise versa
    if (j < n) {
        printf("Error. Out of bounds of the array.");
        return 1;
    }

    //Prints sum for each test case
    printf("%d\n",sum);
    return(sum);
}

void free_memory(int* Array, int array_Size) {
    int i;              //counter for integers in the array

    //Free every element
    for (i = 0; i < array_Size; i++)
        free(&(Array[i]));

    //Free array
    free(Array);
}
