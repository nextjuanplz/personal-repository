// Arup Guha
// 5/28/2014
// Alternate Sudoku Solver
/*** Ported to C from Java on 4/6/2017 ***/

#include <stdio.h>
#include <stdlib.h>

#define N 9

int solve(int* board);
int solveK(int* board, int k);
void print(int* board);
int isConsistent(int* board, int pos);
int isConsistentRow(int* board, int pos);
int isConsistentCol(int* board, int pos);
int isConsistentBox(int* board, int pos);

int main() {

    int numCases, loop, i;
    scanf("%d", &numCases);

    // Process each case.
    for (loop=1; loop<=numCases; loop++) {

        // Read in the initial board.
        int board[N*N];
        for (i=0; i<N*N; i++)
            scanf("%d", &board[i]);

        // Solve it!
        int hasSol = solve(board);

        // Print appropriate output.
        printf("Test case %d:\n\n", loop);
        if (!hasSol) printf("No solution possible.\n");
        else print(board);

        // Two blank lines.
        printf("\n\n");
    }

    return 0;
}

// Wrapper function returns 1 iff there is a solution, 0 otherwise.
int solve(int* board) {

    int i;
    // Gave us a bad board...
    for (i=0; i<N*N; i++) if (!isConsistent(board, i)) return 0;

    // Get the recursion started.
    return solveK(board, 0);
}

// Recursively solved the board which has its first k positions fixed.
int solveK(int* board, int k) {

    // Done!
    if (k == N*N) return 1;

    // Fixed square, go to next.
    if (board[k] != 0) return solveK(board, k+1);

    // Try each number in this spot.
    int i;
    for (i=1; i<=N; i++) {
        board[k] = i;

        // If this doesn't break rules, recurse.
        if (isConsistent(board, k)) {
            int branch = solveK(board, k+1);
            if (branch) return 1;
        }

        // Reset to be empty.
        board[k] = 0;
    }

    // Nothing worked!
    return 0;
}


    // Prints the board.
void print(int* board) {
    int i;
    for (i=0; i<N*N; i++) {
        printf("%d ", board[i]);
        if (i%N == 8) printf("\n");
    }
}

// Returns 1 iff there is no problem with this square, 0 otherwise.
int isConsistent(int* board, int pos) {
    return isConsistentRow(board, pos) && isConsistentCol(board, pos) && isConsistentBox(board, pos);
}

// Returns 1 iff the row of pos is consistent with pos, 0 otherwise.
int isConsistentRow(int* board, int pos) {

    // Automatically okay.
    if (board[pos] == 0) return 1;

    // Make sure no other square has this value on this row.
    int i;
    for (i=0; i<N; i++) {
        if (i == pos%N) continue;
        if (board[(pos/N)*N+i] == board[pos]) return 0;
    }

    // Okay if we get here.
    return 1;
}

// Returns 1 iff the column of pos is consistent with pos, 0 otherwise.
int isConsistentCol(int* board, int pos) {

    // Automatically okay.
    if (board[pos] == 0) return 1;

    // Make sure no other square has this value on this column.
    int i;
    for (i=0; i<N; i++) {
        if (i == pos/N) continue;
        if (board[i*N+pos%N] == board[pos]) return 0;
    }

    // Okay if we get here.
    return 1;
}

// Returns 1 iff the box of pos is consistent with pos, 0 otherwise.
int isConsistentBox(int* board, int pos) {

    // For readability.
    int row = pos/N;
    int col = pos%N;
    int val = board[pos];

    // Automatically okay.
    if (val == 0) return 1;

    // Box loops are a bit tricky...
    int i,j;
    for (i=3*(row/3); i<3*(row/3)+3; i++) {
        for (j=3*(col/3); j<3*(col/3)+3; j++) {
            if (N*i+j == pos) continue;
            if (board[N*i+j] == val) return 0;
        }
    }

    // It's okay if we get here.
    return 1;
}
