//P2: WordSearch - Juan M. Alzate Vanegas
//January 29, 2017

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Constants
const int min_numCases = 0;             //Minimum number of cases
const int max_numCases = 100;           //Maximum number of cases
const int board_max = 300;              //Maximum grid size
const int board_min = 4;                //Minimum grid size
const int LETTERS_min = 4;              //Minimum dictionary word length
const int LETTERS_max = 20;             //Maximum dictionary word length (including NULL)
const int DX_SIZE = 8;                  //Number of possible directions
const int DX[] = {-1,-1,-1,0,0,1,1,1};  //DX array for movement along a row
const int DY[] = {-1,0,1,-1,1,-1,0,1};  //DY array for movement along a column
char ** dictionary;                     //Dictionary
int array_Size;                         //Number of words in dictionary

//struct for linked list usage
typedef struct node {
    char* word;
    struct node * next;
} Node;

//Pointer to node as data type
typedef Node * Nodeptr;
Nodeptr head, temp;

//User-defined function prototypes
char** read_dictionary(int *);                                  //Reads dictionary.txt
int inDictionary(char**, int, char*);                           //Binary search for string in dictionary
char* formString(char**, int, int, int, int, int, int);         //Extracts a string from a given position and length
void printWords(char**, int, int, int, int);                    //Searches a word in every direction and length from a position on the grid; stores to linked list
void printGridWords(char**, int, int);                          //Uses printWords from every position on the grid
void free_memory(char**, int);                                  //Frees grid memory
void print_list(Nodeptr);                                       //Prints linked list
void free_list(Nodeptr);                                        //Frees linked list memory
int word_in_list(Nodeptr, char*);                               //Checks for duplicate words in the linked list
void sort_list(Nodeptr);                                        //Sorts linked list using insert sort algorithm

int main() {
    //Counters
    int numCases;       //Number of cases
    int case_number;    //Case number
    int r;              //Number of rows
    int c;              //Number of columns

    //Read dictionary.txt
    dictionary = read_dictionary(&array_Size);

    //Read number of cases
    scanf("%d",&numCases);

    //Check for illegal of cases
    while(numCases < min_numCases || numCases > max_numCases) {
        if (numCases < min_numCases)
            printf("Too few cases. Try again.\n");
        if (numCases > max_numCases)
            printf("Too many cases. Try again.\n");
        scanf("%d",&numCases);
    }

    //Read each case
    for (case_number = 0; case_number < numCases; case_number++) {
       //Scan for grid dimensions
       scanf("%d%d",&r,&c);

       //Check that r and c are [4,300]
       if (r >= board_min && c >= board_min && r <= board_max && c <= board_max) {

           //Initialize head ptr of linked list to NULL
           head = NULL;

           //Dynamic memory allocation for grid
           int i;
           char** grid = malloc(r * sizeof(char*));

           //Read grid rows
           for (i = 0; i < r; i++) {
               grid [i] = malloc(c * sizeof(char));
               scanf("%s",grid[i]);

               //Check to ensure the grid row provided fits
               while(strlen(grid[i]) > c) {
                    printf("Grid length is too long. Try again.\n");
                    scanf("%s",grid[i]);
               }
           }

           //Output
           printf("Words Found Grid #%d:\n",(case_number + 1));
           printGridWords(grid, r, c);

           //Free memory from grid and its elements
           free_memory(grid, r);

           //Free memory from linked list
           free_list(head);
       }
    }
    return 0;
}

char** read_dictionary(int * dictionary_Size) {
    //Counters from i to array_Size words
    int i;
    int array_Size;

    //Check that dictionary.txt was opened successfully
    FILE * dictionary = fopen("dictionary.txt","r");
    if (dictionary == NULL) {
        printf("dictionary.txt could not be opened.\n");
        return NULL;
    }

    //Dynamic memory allocation for dictionary array
    fscanf(dictionary, "%d",&array_Size);
    char** Array = malloc(array_Size * sizeof(char*));

    //Pass array size by reference
    *dictionary_Size = array_Size;

    //Check that memory was successfully allocated to the array
    if (Array == NULL) {
        printf("Not enough memory to create array.\n");
        return NULL;
    }

    //Read dictionary into array
    for (i = 0; i < array_Size; i++)
        if (feof(dictionary) == 0) {
            Array[i] = malloc(LETTERS_max * sizeof(char));
            fscanf(dictionary,"%s",Array[i]);
        }

        //Check if dictionary.txt ends before reaching array_Size words
        else {
            printf("dictionary.txt was not read successfully.\n Only %d words were read.\n",i+1);
            return NULL;
        }

    //Close dictionary.txt
    fclose(dictionary);

    return(Array);
}

int inDictionary(char** words, int numWords, char* searchWord) {
    //Dictionary indexes
    int low = 0, high = (numWords - 1), middle;

    // Search within dictionary while there is an available search space
    while (low <= high) {
        middle = (low + high)/2;

        //word is closer to A
        if (strcmp(searchWord,words[middle]) < 0)
            high = (middle - 1);

        //word is closer to Z
        else if (strcmp(searchWord,words[middle]) > 0)
            low = (middle + 1);

        //word was found
        else if ((strcmp(searchWord,words[middle]) == 0)) {
            return 1;
        }
    }

    //word was not found
    return 0;
}

char* formString(char** grid, int numRows, int numC, int x, int y, int len, int dir) {
    //Counter
    int i;

    //Checks that we are still within the grid
    int x_check = (x + (len - 1) * DX[dir]);
    int y_check = (y + (len - 1) * DY[dir]);
    if (x_check >= numC || x_check < 0 || y_check >= numRows || y_check < 0)
        //word was not found in the specified location and direction
        return NULL;

    //Dynamic memory allocation for word
    char* word = malloc((len + 1) * sizeof(char));

    //Extract strings of length i in direction dir
    for (i = 0; i < len; i++) {
        word[i] = grid [y] [x];
        x += DX[dir];
        y += DY[dir];
    }

    //Mark end of word
    word[len] = '\0';

    return(word);
}

void printWords(char** grid, int numRows, int numC, int x, int y) {
    //Counters
    int len;        //Word length
    int dir;        //Direction
    char* word;     //Word

    //Check every direction
    for (dir = 0; dir < DX_SIZE; dir++) {

        //Check every length
        for (len = LETTERS_min; len < LETTERS_max; len++) {

            //Extracted string
            word = formString(grid, numRows, numC, x, y, len, dir);

            //Check if extracted string is in dictionary, and if it is a unique word
            if (word != NULL && inDictionary(dictionary, array_Size, word) == 1 && word_in_list(head, word) == 0) {

                //Check that head Nodeptr was allocated dynamically to memory
                if ((temp = malloc(sizeof(Node)))== NULL)
                    break;

                //Found word is stored in linked list
                temp->word = word;
                temp->next = head;
                head = temp;
            }
        }
    }
}

void printGridWords(char** grid, int numRows, int numC) {
    //Counters
    int i;  //Column counter
    int j;  //Row counter

    //Check every column
    for (i = 0; i < numC; i++) {

        //Check every row
        for (j = 0; j < numRows; j++)

            //Check for strings in this position that are words
            printWords(grid, numRows, numC, i, j);
    }

    //Print linked list
    sort_list(head);
    print_list(head);
}

void free_memory(char** grid, int numRows){
    //Free grid from i to r
    int i;
    for (i = 0; i <= numRows; i++)
        free(grid[i]);

    //Free grid
    free(grid);
}

void print_list(Nodeptr list_head) {
    while (list_head != NULL) {
        //Print each word
        printf("%s\n",list_head->word);

        //Traverse list
        list_head = list_head->next;
    }
}

void free_list(Nodeptr list_head) {
    //Temporary node
    Nodeptr list_temp;

    //Free each node
    while (list_head != NULL) {
        list_temp = list_head->next;
        free(list_head);
        list_head = list_temp;
    }
}

int word_in_list(Nodeptr list_head, char* searchWord) {
    while(list_head != NULL) {
        //Word was found on the list
        if (strcmp(searchWord,list_head->word) == 0)
            return 1;

        //Traverse list
        list_head = list_head->next;
    }

    //Word was not found on the list
    return 0;
}

void sort_list(Nodeptr list_head) {

    //Temporary nodes
    Nodeptr j, small;
    while (list_head->next != NULL) {
        j = list_head->next;
        small = list_head;
        while(j != NULL){

            //Check if temp->word comes before small->word
            if (strcmp(j->word, small->word) < 0)
                small = j;
            j = j->next;
        }

        //Swap
        char* tempWord = list_head->word;
        list_head->word = small->word;
        small->word = tempWord;

        //Traverse list
        list_head = list_head->next;
    }
}
