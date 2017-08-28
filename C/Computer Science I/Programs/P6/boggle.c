// Juan Manuel Alzate Vanegas
// 4/9/2017
// P6 - Boggle Solver

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 4
#define NUMDIR 8

// Directions of movement in Boggle.
const int DX[] = {-1,-1,-1,0,0,1,1,1};
const int DY[] = {-1,0,1,-1,1,-1,0,1};

typedef struct trie {
    int isWord;
    struct trie* nextLetter[26];
} trie;

//Non-framework function prototypes
void solveAll(char board[][SIZE], trie* dictionary);
void solveIt(char prefix[], int used[][SIZE], int curX, int curY, char board[][SIZE], trie* dictionary);

//Framework function prototypes
void readboard(char board[][SIZE]);
int inbounds(int x, int y);
void insert(trie* root, char word[]);
trie* insertRec(trie* root, char word[], int index);
int isWord(char word[], trie* dictionary);
int isPrefix(char word[], trie* dictionary);
void freetree(trie* root);

int main() {

    FILE* ifp = fopen("dictionary.txt", "r");

    // Set up the dictionary structure.
    trie* dictionary = malloc(sizeof(trie));
    int i;
    for (i=0; i<26; i++)
        dictionary->nextLetter[i] = NULL;
    dictionary->isWord = 0;

    // Read in the number of words.
    int numWords;
    fscanf(ifp, "%d", &numWords);
    for (i=0; i<numWords; i++) {
        char word[100];
        fscanf(ifp, "%s", word);

        // Just insert the words that might count for boggle.
        if (strlen(word) >= 3 && strlen(word) <= 16)
            insert(dictionary, word);
    }
    fclose(ifp);

    // Start processing input cases.
    int numCases;
    scanf("%d", &numCases);

    // Go through each game.
    for (i=1; i<=numCases; i++) {

        printf("Words for Game #%d:\n", i);

        // Read in the board and solve.
        char board[SIZE][SIZE];
        readboard(board);
        solveAll(board, dictionary);
        printf("\n\n");
    }

    // Free this memory.
    freetree(dictionary);
    return 0;
}

// Reads in a single board from the file pointed to by ifp into board.
// The file pointer must be pointing to a SIZE x SIZE board with SIZE
// lines each with exactly SIZE contiguous letters.
void readboard(char board[][SIZE])  {

    char temp[SIZE+1];

    int i, j;

    // Go through each line.
    for (i=0; i<SIZE; i++) {

        scanf("%s", temp);

        // Set up each character.
        for (j=0; j<SIZE; j++)
            board[i][j] = temp[j];
    }

}

// Outputs all possible words that can be formed in board with words from dictionary
void solveAll(char board[][SIZE], trie* dictionary) {
    //Indexes
    int i, j;

    //Initialize used[] array
    int used[SIZE][SIZE];
    for (i = 0; i < SIZE; i++)
        for (j = 0; j < SIZE; j++)
            used[i][j] = 0;

    //Storage for words
    char temp_word[SIZE*SIZE + 1];

    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            temp_word[0] = board[i][j];
            temp_word[1] = '\0';
            used[i][j] = 1;
            solveIt(temp_word, used, i, j, board, dictionary);
            used[i][j] = 0;
        }
    }

}

// Prints out all valid words on the board that start with prefix, where the
// last letter used starts at coordinates (curX, curY). used stores which
// board squares have currently been used and board stores the playing board.
void solveIt(char prefix[], int used[][SIZE], int curX, int curY, char board[][SIZE], trie* dictionary) {
    //Index
    int i;

    //Temporary storage
    int temp_x, temp_y, last_letter;
    char temp_prefix[SIZE*SIZE + 1];

    //Base case
    if(isWord(prefix,dictionary))
        printf("%s\n",prefix);

    //Check every direction
    for (i = 0; i < NUMDIR; i++) {
        temp_x = curX + DX[i];
        temp_y = curY + DY[i];

        //If inbounds and unused...
        if (inbounds(temp_x, temp_y) && !used[temp_x][temp_y]) {
            //Mark as used
            used[temp_x][temp_y] = 1;
            //Copy prefix
            strcpy(temp_prefix, prefix);

            //Store position of last letter
            last_letter = strlen(temp_prefix);

            //Add new letter
            temp_prefix[last_letter] = board[temp_x][temp_y];
            temp_prefix[last_letter + 1] = '\0';

            //Check if word is a prefix
            if (isPrefix(temp_prefix,dictionary))
                //Recursive call
                solveIt(temp_prefix, used, temp_x, temp_y, board, dictionary);

            //Clear used[temp_x][temp_y]
            used[temp_x][temp_y] = 0;

        }
    }
}

// Returns 1 iff (x,y) are valid indexes to board. Returns 0 otherwise.
int inbounds(int x, int y) {
    return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
}

// Wrapper function to insert word into the dictionary stored at root.
void insert(trie* root, char word[]) {
    root->nextLetter[(int)(word[0]-'a')] = insertRec(root->nextLetter[(int)(word[0]-'a')], word, 1);
}

// Recursive function that is given a pointer to location in the alphatree, the word
// to insert, and the index into that word for the next letter to insert.
trie* insertRec(trie* root, char word[], int index) {

    // The node where we want to insert hasn't been created yet.
    if (root == NULL) {

        trie* newnode = (trie*)malloc(sizeof(trie));
        int i;
        for (i=0; i<26; i++)
            newnode->nextLetter[i] = NULL;

        // We have a word, we can stop.
        if (index == strlen(word)) {
            newnode->isWord = 1;
        }
        else {
            newnode->isWord = 0;
            newnode->nextLetter[(int)(word[index]-'a')] = insertRec(newnode->nextLetter[(int)(word[index]-'a')], word, index+1);
        }

        return newnode;
    }

    // If root isn't NULL
    if (index == strlen(word))
        root->isWord = 1;
    else
        root->nextLetter[(int)(word[index]-'a')] = insertRec(root->nextLetter[(int)(word[index]-'a')], word, index+1);


    return root;
}

// Properly free the memory for our alphatree.
void freetree(trie* root) {

    if (root == NULL) return;
    int i;
    for (i=0; i<26; i++)
        freetree(root->nextLetter[i]);

    free(root);
}

// Returns 1 iff word is stored in dictionary.
int isWord(char word[], trie* dictionary) {

    int i;

    // Go through each letter.
    for (i=0; i<strlen(word); i++) {

        // If the node doesn't exist, it's definitely NOT a word.
        if (dictionary->nextLetter[(int)(word[i]-'a')] == NULL)
            return 0;

        // Go to the next letter.
        dictionary = dictionary->nextLetter[(int)(word[i]-'a')];
    }

    // We're at the end, check to see if it's a word!
    return dictionary->isWord;
}

// Returns 1 iff word is stored in dictionary.
int isPrefix(char word[], trie* dictionary) {

    int i;

    // Go through each letter.
    for (i=0; i<strlen(word); i++) {

        // If the node doesn't exist, it's definitely NOT a word.
        if (dictionary->nextLetter[(int)(word[i]-'a')] == NULL)
            return 0;

        // Go to the next letter.
        dictionary = dictionary->nextLetter[(int)(word[i]-'a')];
    }

    // We're at the end, what we've read through is definitely a prefix!
    return 1;
}
