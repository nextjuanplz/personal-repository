// Arup Guha
// 1/19/2017
// Solution to UCF 2016 HS Problem: Guessing Game
// Used to illustrate how to test programs via command line
// file redirection and a file comparison program.

#include <stdio.h>

int solve(int low, int high, int secret);

int main() {

    int numCases, loop;
    scanf("%d", &numCases);

    // Process each case.
    for (loop=1; loop<=numCases; loop++) {

        // Get input.
        int low, high, secret;
        scanf("%d%d%d", &low, &high, &secret);

        // Solve and output.
        int res = solve(low, high, secret);
        if (res != 1)
            printf("Game #%d: %d guesses\n", loop, res);
        else
            printf("Game #%d: 1 guess\n", loop);
    }

    return 0;
}

int solve(int low, int high, int secret) {

    int numGuesses = 1;
    
    // Run a binary search
    while (low <= high) {
        
        // This is what they said to guess in the rules.
        int guess = (low+high+1)/2;
        
        // Usual binary search adjustment
        if (guess == secret)
            return numGuesses;
        else if (secret > guess)
            low = guess+1;
        else
            high = guess-1;
            
        // We need to keep track of this.
        numGuesses++;
    }

    // Should never get here, but if it does...
    return numGuesses;
}
