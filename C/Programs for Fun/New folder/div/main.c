//n, d(n), P(n), and delta P(n)
//Prints integers, number of divisors for int > 0, all primes up to LIM, and gaps between primes
//Juan M. Alzate Vanegas

#include <stdio.h>
#include <stdlib.h>
#define ERROR -1

//Upper-bound
#define LIM 1000000

//Print-to-console flag
#define PRINT 0

//Check that file was opened correctly
FILE* checkFile(FILE*);

int main() {
    //n File
    FILE* nFile = fopen("n.txt","w");
    checkFile(nFile);

    //Divisor file
    FILE* divisors = fopen("divisors.txt","w");
    checkFile(divisors);

    //Prime file
    FILE* primes = fopen("primes.txt","w");
    checkFile(primes);

    //Deltaprime file
    FILE* deltap = fopen("deltap.txt","w");
    checkFile(deltap);

    int n;  //Integers

    //Check every number up to LIM
    for (n = 1; n <= LIM; n++) {
        int i = 1;  //Divisor index
        int f = 0;  //Number of divisors
        int a;      //Previous prime

        //Start with distance to previous prime as 0
        if (n == 2) a = 2;

        //Check number of divisors
        while (i <= n) {
            if (n % i == 0) f++;
            i++;
        }

        //Print n and d(n)
        if (PRINT) printf("%d %d", n, f);
        fprintf(nFile, "%d\n",n);
        fprintf(divisors, "%d\n", f);

        //Check primes
        if (f == 2 && n > 1) {

            //Calculate distance to previous prime
            int d = n - a;

            //Reset previous prime to n
            a = n;

            //Print primes and gap between primes
            fprintf(primes, "%d\n", a);
            fprintf(deltap, "%d\n", d);
            if (PRINT) printf(" %d <------------------prime\n", d);
        }

        else if (PRINT) printf("\n");
    }

    //Close files
    fclose(nFile);
    fclose(divisors);
    fclose(primes);
    fclose(deltap);

    //Finished!
    printf("Done!");

    return 0;
}

//Check that file was opened correctly
FILE* checkFile(FILE* file) {
    if (file == NULL) printf("File could not be opened.\n");
    return NULL;
}
