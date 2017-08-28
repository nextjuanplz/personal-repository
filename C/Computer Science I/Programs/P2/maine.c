//P2 - mastermind
//Juan M. Alzate Vanegas
//2-12-2017
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

//Constants
const int max_numCases = 100;
const int min_numColors = 2;
const int max_numMoves = 20;
const double max_nk = pow(10,6);

//Global variables
int c;  //Number of input cases
int n;  //Number of slots to fill in
int k;  //Number of possible colors for each slot
int m;  //Number of moves

//Struct for each guess
typedef struct guess {
    int b;          //Black pegs awarded
    int w;          //White pegs awarded
    int* comb;     //Associated combination
} Guess;

//User-defined functions
int blackPegs(int*, int*, int);                  //Awards the number of black pegs
int* colorFrequency(int*, int, int);             //Stores the frequency k colors in a guess of length n as a dynamically-allocated array
int pegs(int*, int*, int);                       //Awards the number of total pegs
int whitePegs(int*, int*, int, int);             //Awards white pegs
int checkPegs(int*, int*, int, int, int, int);   //Checks if a combination can be possible given its response
int checkSet(int*, Guess*);

int main(){
    //Inputs
    int b = 0;  //Number of black pegs
    int w = 0;  //Number of white pegs

    //Read number of cases
    scanf("%d",&c);

    //Check for illegal number of cases
    while(c < 1 || c > max_numCases) {
        if (c < 1)
            printf("The number of cases is too small. Try again.\n");
        if (c > max_numCases)
            printf("The number of cases is too large. Try again.\n");
        scanf("%d",&c);
    }

    while (c < max_numCases) {
        //Scan inputs n, k, and m
        scanf("%d %d %d", &n, &k, &m);

        //Checks for input value ranges
        //k is too small
        while (k < min_numColors) {
            printf("The value of k is too small. Try again.\n");
            scanf("%d",&k);
        }

        // m is too large
        while (m > max_numMoves) {
            printf("The input value is too small. Try again.\n");
            scanf("%d",&m);
        }

        //n and/or k are too large
        while (pow(k, n) > max_nk) {
            printf("The values of n and k are too large. Try again by typing n then k.\n");
            scanf("%d %d",&n, &k);
        }

        //Create array solution_set [case][move][slot]
        Guess* guess_set = malloc(c * sizeof(Guess));


        c++;

    }

    return 0;
}

int blackPegs(int* solution, int* guess){
    //Indexes
    int i;  //Slot
    int b;  //Number of black pegs to be awarded

    //Read every digit in the solution and guess
    for (i = 0; i < n; i++)

        //Award a peg if match in correct spot
        if (solution[i] == guess[i]) b++;

    return b;
}

int* colorFrequency(int* guess, int n, int k) {
    //Index
    int i;

    //Allocate memory to the array
    int* frequency = malloc(k * sizeof(int));

    //Zero out array
    for (i = 0; i < k; i++)
        frequency[i] = 0;
    i = 0;

    //Count each color
    while (i < n){
        frequency[guess[i]]++;
        i++;
    }

    return frequency;
}

int pegs(int* solution_frequency, int* guess_frequency, int k){
    //Counters
    int i = 0;     //Index for colors
    int bw = 0;    //Number of pegs to be awarded

    while (i < k) {
        if (guess_frequency[i] > solution_frequency[i])
            bw += solution_frequency[i];
        if (guess_frequency[i] <= solution_frequency[i])
            bw += guess_frequency[i];
        i++;
    }

    return bw;
}

int whitePegs(int * solution, int * guess, int n, int k) {
    //Counters
    int p;  //Total number of pegs
    int b;  //Number of black pegs

    //Calculate the number of black pegs
    b = blackPegs(solution, guess, n);

    //Calculate the total number of pegs
    p = pegs(colorFrequency(solution,n,k),colorFrequency(guess,n,k),k);

    //white pegs = pegs - (black pegs)
    return (p-b);
}

int checkPegs(int* solution, int* guess, int n, int k, int b, int w){
    //Calculated peg values, assuming the guess combination is possible
    int b_pegs = blackPegs(solution, guess, n);
    int w_pegs = whitePegs(solution, guess, n, k);

    //Return true if calculated pegs match the response
    if (b == b_pegs && w == w_pegs) return 1;

    //Return false if calculated pegs do not match the response
    else return 0;
}

int checkSet(int* solution, Guess* guessSet, int n, int k, int m, int c){
    //Indexes
    int i;

    for (i = 0; i < c; i++){

        checkPegs(solution, guessSet[i]->comb, n, k, guessSet[i]->b, guessSet[i]->w);

    }

}
