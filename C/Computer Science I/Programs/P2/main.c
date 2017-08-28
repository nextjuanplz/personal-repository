//P2 - mastermind (COP 3502C)
//Juan M. Alzate Vanegas
//2-12-2017
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

//Constants
const int max_numCases = 100;
const int min_numColors = 2;
const int max_numMoves = 20;
const double max_nk = 10000000;

//Global variables
int c;                  //Number of input cases
int n;                  //Number of slots to fill in
int k;                  //Number of possible colors for each slot
int m;                  //Number of moves that have been played
int** possibilities;    //Array containing all k^n combinations
int global_index = 0;   //Index for possibilities array

//Guess struct for each guess with an associated number of pegs
typedef struct guess {
    int b;          //Black pegs awarded
    int w;          //White pegs awarded
    int* combo;     //Associated combination
} Guess;

//User-defined functions
int blackPegs(int*, int*);                  //Awards the number of black pegs
int* colorFrequency(int*);                  //Stores the frequency k colors in a guess of length n as a dynamically-allocated array
int pegs(int*, int*);                       //Awards the number of total pegs
int whitePegs(int*, int*);                  //Awards white pegs
int checkPegs(int*, int*, int, int);        //Checks if a combination can be possible given its response
int checkSet(int*, Guess*);                 //Checks if a set of combinations can be possible
void generatePossibilities(int*, int);      //Generates all k^n possibilities and stores them to the possibilities array

void main() {
    //Indexes
    int i = 0;
    int j = 0;
    int c_index = 0;
    int m_index = 0;
    int n_index = 0;
    int matchesFound = 0;

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

    //Dynamically-allocated array for output
    int* output = malloc(c * sizeof(int));

    //Read every case
    while (c_index < c) {

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

        //Allocate memory to the guess_set array of structs
        Guess* guess_set = malloc(m * sizeof(Guess));
        for (j = 0; j < m; j++){
            guess_set[j].combo = malloc(n * sizeof(int));
        }

        //Allocate memory to the possibilities array
        possibilities = malloc(pow(k,n) * sizeof(int*));

        for (i = 0; i < pow(k,n); i++)
            possibilities[i] = malloc(n * sizeof(int));

        //Start calculating each k^n possibility recursively
        global_index = 0;
        generatePossibilities(NULL, 0);

        //Read m moves
        m_index = 0;
        while (m_index < m) {
            //Read n slots
            n_index = 0;
            while (n_index < n) {
                //Scan combinations
                scanf("%d", &(guess_set[m_index].combo[n_index]));
                n_index++;
            }

            //Scan corresponding b and w values
            scanf("%d %d", &(guess_set[m_index].b), &(guess_set[m_index].w));
            m_index++;
        }

        //Test input values
        matchesFound = 0;
        for (i = 0; i < global_index; i++)
            //Increment matches_found each time a possibility matches with all previous guesses
            if (checkSet(possibilities[i], guess_set) == m) matchesFound++;

        //Add number of matches found in a case to an external array
        output[c_index] = matchesFound;

        //Free possibilities array
        for (i = 0; i < pow(k,n); i++)
            free(possibilities[i]);
        free(possibilities);

        //Free guess_set
        for (i = 0 ; i < m; i++)
            free(guess_set[i].combo);
        free(guess_set);

        //Move onto the next case
        c_index++;
    }

    //Print solutions from output array
    for (i = 0; i < c; i++)
        printf("%d\n",output[i]);

    //Free output array
    free(output);

    return;
}

int blackPegs(int* solution, int* combo) {
    //Indexes
    int i;      //Slot
    int b = 0;  //Number of black pegs to be awarded

    //Read every digit in the solution and guess
    for (i = 0; i < n; i++)

        //Award a black peg if match in correct spot
        if (solution[i] == combo[i]) b++;

    return b;
}

int* colorFrequency(int* combo) {
    //Index
    int i;

    //Allocate memory to the array
    int* frequency = malloc(k * sizeof(int));

    //Zero out array
    for (i = 0; i < k; i++)
        frequency[i] = 0;

    //Count each color
    i = 0;
    while (i < n){
        frequency[combo[i]]++;
        i++;
    }
    return frequency;
}

int pegs(int* solution_frequency, int* guess_frequency) {
    //Counters
    int i = 0;     //Index for colors
    int bw = 0;    //Number of pegs to be awarded

    while (i < k) {
        //Overestimate
        if (guess_frequency[i] > solution_frequency[i])
            bw += solution_frequency[i];
        //Underestimate or correctly estimated
        if (guess_frequency[i] <= solution_frequency[i])
            bw += guess_frequency[i];
        i++;
    }
    return bw;
}

int whitePegs(int* solution, int* combo) {
    //Counters
    int p;  //Total number of pegs
    int b;  //Number of black pegs

    //Pointers for frequency tables
    int* solution_ptr = colorFrequency(solution);
    int* combo_ptr = colorFrequency(combo);

    //Calculate the number of black pegs
    b = blackPegs(solution, combo);

    //Calculate the total number of pegs
    p = pegs(solution_ptr, combo_ptr);

    //Free frequency tables
    free(solution_ptr);
    free(combo_ptr);

    //white pegs = pegs - (black pegs)
    return (p-b);
}

int checkPegs(int* solution, int* combo, int b, int w) {
    //Calculated peg values, assuming the guess combination is possible
    int b_pegs = blackPegs(solution, combo);
    int w_pegs = whitePegs(solution, combo);

    //Return true if calculated pegs match the response
    if (b == b_pegs && w == w_pegs) return 1;

    //Return false if calculated pegs do not match the response
    else return 0;
}

int checkSet(int* solution, Guess* guessSet){
    //Indexes
    int i;          //Case index
    int match = 0;  //Number of possible guesses

    for (i = 0; i < m; i++)
        if (checkPegs(solution, (guessSet[i]).combo, (guessSet[i]).b, (guessSet[i]).w) == 1)
            match++;

    return match;
}

void generatePossibilities(int* array, int elements) {
    //Indexes
    int i;                 //auxiliaryArray index
    int j;                 //Index for each element of a combination

    //Dynamically allocate an array to copy the contents of the input array
    int* auxiliaryArray = malloc((elements + 1) * sizeof(int));
    if (elements > 0)
        for (i = 0; i < elements; i++)
            auxiliaryArray[i] = array[i];

    //Base case
    if(elements == (n-1)){

        //Try each color in slot n-1
        for (i = 0; i < k; i++){
            auxiliaryArray[elements] = i;

            //Copy each combination into the global array
            for (j = 0; j < n; j++)
                possibilities[global_index][j] = auxiliaryArray[j];

            //Move onto the next combination
            global_index++;
        }
    }
    //Recursion
    else {
        //Fills in a slot and uses recursion to fill up the rest
        for (i = 0; i < k; i++) {
            auxiliaryArray[elements] = i;
            generatePossibilities(auxiliaryArray, elements + 1);
        }
    }
    //Free auxiliary array
    free(auxiliaryArray);
}
