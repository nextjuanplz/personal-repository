#include <stdio.h>
#include <stdlib.h>

#define 1000

int main{
    int n, j, q = 1;

    for (n = 1; n <= LIM; n++) {
        int i = 1;  //Divisor index
        int f = 0;  //Number of divisors

        //Check number of divisors
        while (i <= n) {
            if (n % i == 0) f++;
            i++;
        }
        //Check primes
        if (f == 2 && n > 1) {
            q++
        }

    for (j = 0; j =< q; j++)
        printf("%d\n", n);
}
