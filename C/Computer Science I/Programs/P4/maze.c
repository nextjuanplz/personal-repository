//P4 - Maze (3/12/2017)
//COP 3502C
//Juan M. Alzate Vanegas
#include <stdio.h>
#include <stdlib.h>

//Constants
#define EMPTY -1
const int max_numCases = 100;
const int min_Dim = 3;
const int max_Dim = 300;

//DX/DY Arrays
const int DX[] = {-1,0,0,1};  //DX array for movement along a row
const int DY[] = {0,-1,1,0};  //DY array for movement along a column

//Queue
struct queue {
    struct pairs** elements;
    int front;
    int numElements;
    int queueSize;
};

//Coordinate pair
struct pairs {
    int x;
    int y;
};


//User-defined function prototypes
int** createArray(int, int);
char** readMaze(int, int);
void init(struct queue*, int, int);
int bfs(struct queue*, int**, char**, int, int, int, int);
int enqueue(struct queue*, int, int);
int dequeue(struct queue*, int*, int*);
int empty(struct queue*);
int front(struct queue*, int*, int*);
int findStart(char**, int, int, int*, int*);

int main() {
    //Maze dimensions
    int c;
    int rows;
    int cols;

    //Indexes
    int i;
    int c_index = 0;

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

    //Read every case
    while (c_index < c) {

        //Scan inputs
        scanf("%d %d", &rows, &cols);

        //Checks for input value ranges
        while (rows < min_Dim || rows > max_Dim) {
            printf("The value of rows is invalid. Try again.\n");
            scanf("%d",&rows);
        }
        while (cols < min_Dim || rows > max_Dim) {
            printf("The value of columns is invalid. Try again.\n");
            scanf("%d",&cols);
        }

        //Read case
        char** maze = readMaze(rows, cols);

        //Set up queue
        struct queue* QPtr = (struct queue*)malloc(sizeof(struct queue));
        if (QPtr == NULL) return -1;
        init(QPtr, rows, cols);

        //Set up distances array
        int** distances = createArray(rows, cols);

        //Find starting point and set to 0
        int start_x, start_y;
        findStart(maze, rows, cols, &start_x, &start_y);
        distances[start_x][start_y] = 0;


        //Start BFS
        printf("%d\n", bfs(QPtr, distances, maze, rows, cols, start_x, start_y));

        //Free maze
        for (i = 0; i < rows; i++)
            free(maze[i]);
        free(maze);

        //Free distances array
        for (i = 0; i < rows; i++)
            free(distances[i]);
        free(distances);

        //Free queue
        for (i = 0; i < QPtr->queueSize; i++)
            free(QPtr->elements[i]);
        free(QPtr);

        //Move onto the next case
        c_index++;
    }
    return 0;
}

//User-defined functions
int** createArray(int rows, int columns) {
    //Indexes
    int i, j;

    //Allocate memory
    int** array = (int**)malloc(rows * sizeof(int*));
    for (i = 0; i < rows; i++){
        array[i] = (int*)malloc(columns * sizeof(int));

        //Mark spots as empty
        for (j = 0; j < columns; j++)
            array[i][j] = -1;
    }
    return(array);
}

char** readMaze(int rows, int columns) {
    //Indexes
    int i, j;

    //Allocate memory to maze
    char** maze = (char**)malloc(rows * sizeof(char*));

    //Check for dynamic memory allocation
    if (maze != NULL) {
        //Allocate memory for columns + 1 for NULL
        for (i = 0; i < rows; i++)
            maze[i] = (char*)malloc((columns + 1) * sizeof(char));

        //Read rows
        for (i = 0; i < rows; i++)
            scanf("%s", maze[i]);
    }
    return maze;
}

void init(struct queue* qPtr, int rows, int cols) {
     int i;
     qPtr->front = 0;
     qPtr->numElements = 0;
     qPtr->elements = (struct pairs**)malloc(rows * cols * sizeof(struct pairs*));
     for (i = 0; i < (rows * cols); i++)
         qPtr->elements[i] = (struct pairs*)malloc(sizeof(struct pairs));
     qPtr->queueSize = rows * cols;
}

int bfs(struct queue* qPtr, int** dist, char** maze, int rows, int cols, int s_x, int s_y) {
    //Indexes
    int i;
    int X, Y;
    int next_x, next_y;

    //Enqueue source
    enqueue(qPtr, s_x, s_y);

    //While queue is not full
    while(qPtr->numElements != qPtr->queueSize) {
        //Set x and y to queued values
        if (dequeue(qPtr, &X, &Y) == EMPTY) return -1;

        //If destination, return dist[x][y]
        if (maze[X][Y] == '~') return dist[X][Y];

        //Loop through each direction
        for (i = 0; i < 4; i++) {
            next_x = X + DX[i];
            next_y = Y + DY[i];

            //Inbound checks
            int x_check = 0, y_check = 0;
            if (next_x < 0 || next_x > rows) x_check = -1;
            if (next_y < 0|| next_y > cols) y_check = -1;

            //Check if inbounds, unvisited, and visitable
            if (x_check == 0 && y_check == 0 && dist[next_x][next_y] == -1 && (maze[next_x][next_y] != 'X')) {
                dist[next_x][next_y] = dist[X][Y] + 1;
                enqueue(qPtr, next_x, next_y);
            }

        }
    }

    //No exit was found
    return -1;
}

int enqueue(struct queue* qPtr, int X, int Y) {
    //Index
    int i;

    //Non-full queue
    if (qPtr->numElements != qPtr->queueSize) {

        // Enqueue the current element (use of mod for the wraparound case); modify qPtr->elements
        qPtr->elements[(qPtr->front + qPtr->numElements)%qPtr->queueSize]->x = X;
        qPtr->elements[(qPtr->front + qPtr->numElements)%qPtr->queueSize]->y = Y;

        //Edit number of elements
        (qPtr->numElements)++;

        //Complete
        return 1;
    }

    //Full queue
    else {
         //Reallocate more space
         qPtr->elements = (struct pairs**)realloc(qPtr->elements, (qPtr->queueSize)* sizeof(struct pairs*)*2);
         for (i = qPtr->queueSize; i < ((qPtr->queueSize * 2) - 1); i++)
            qPtr->elements[i] = (struct pairs*)malloc(sizeof(struct pairs));

         //Copy all of the items stored before front and copy into their new correct locations
         for (i = 0; i <= qPtr->front-1; i++){
             qPtr->elements[i+qPtr->queueSize]->x = qPtr->elements[i]->x;
             qPtr->elements[i+qPtr->queueSize]->y = qPtr->elements[i]->y;
         }

         //Enqueue the new item
         qPtr->elements[i + qPtr->queueSize]->x = X;
         qPtr->elements[i + qPtr->queueSize]->y = Y;


         //Update queueSize and numElements
         (qPtr->queueSize) *= 2;
         (qPtr->numElements)++;

         //Complete
         return 1;
    }
}

int dequeue(struct queue* qPtr, int* X, int* Y) {
    //Empty list
    if (qPtr->numElements == 0)
        return EMPTY;

    //Store coordinates that should be returned
    struct pairs* coordinates = qPtr->elements[qPtr->front];

    //Adjust front index
    qPtr->front = (qPtr->front + 1)% qPtr->queueSize;

    //Adjust numElements
    (qPtr->numElements)--;

    //Pass coordinates by reference
    *X = coordinates->x;
    *Y = coordinates->y;

    return 0;
}

int empty(struct queue* qPtr) {
    //Return 1 if empty
    return qPtr->numElements == 0;
}

int front(struct queue* qPtr, int* X, int* Y) {
    if (qPtr->numElements != 0) {
        *X = qPtr->elements[qPtr->front]->x;
        *Y = qPtr->elements[qPtr->front]->y;
        return 0;
    }
    else
        return EMPTY;
}

int findStart(char** maze, int rows, int cols, int* X, int* Y) {
    //Indexes
    int i, j;

    //Check every row
    for (i = 0; i < rows; i++) {
        //Check every column
        for (j = 0; j < cols; j++) {
            if (maze[i][j] == 'S'){
                *X = i;
                *Y = j;

                //Starting point found
                return 1;
            }
        }
    }

    //Starting point not found
    return 0;

}
