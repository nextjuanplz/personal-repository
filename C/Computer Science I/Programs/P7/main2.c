#include <stdio.h>
#include <stdlib.h>

#define SIZE 10000
#define MAX_N 1000000
#define MAX_T 10000000000
#define MAX_LENGTH 19

struct lecture {
    char lectureName[MAX_LENGTH+1];
    int lectureDuration;
    int lectureStart;
    int lectureEnd;
    int roomNumber;
};

struct heap {
    struct lecture** heaparray;
    int capacity;
    int size;
};

//Function prototypes
struct heap* initHeap();
int simulate(int*, int, int);
int binsearch(int*, int, int);
int compare(struct lecture*,struct lecture*);
void swap(struct lecture**, struct lecture**);
void insert(struct heap*, struct lecture*);
struct lecture* deleteMin(struct heap*);
void percolateUp(struct heap*, int);
void percolateDown(struct heap*, int);

int main() {
    //Inputs
    int n = 8;       //Number of lectures that must be scheduled
    int t = 6401;    //Max time alloted for conference (s)
    int n_count;     //Lecture index

    //Output
    int k;

    //Debug array
    int lectureArray[8] = {1000, 2000,
    1500, 1500, 1300, 2200, 1800, 800};

    int numRooms = binsearch(lectureArray,n,t);
    //k = simulate(lectureArray, n, numRooms);
    printf("%d\n",numRooms);

/**
    //Scan inputs
    scanf("%d %d",n,t);

    //Check valid inputs
    while (t > MAX_T || n > MAX_T){
        printf("Try again.\n");
        scanf("%d %d",n,t);
    }


    //Process each lecture
    for (n_count = 0; n_count < n; n_count++) {
        scanf("%s %d",lecture_name,lecture_time);
    }
*/

    return 0;
}

int simulate(int* times, int length, int numRooms) {
    //Heap
    struct heap* myHeap = initHeap();

    //Index
    int i;

    //Last lecture
    int k;

    //Initialize each element and add first numRoom items in times to myHeap
    for (i = 0; i < numRooms; i++) {
        //Temporary lecture*
        struct lecture* temp_lecture = (struct lecture*)malloc(sizeof(struct lecture));
        temp_lecture->lectureDuration = times[i];
        temp_lecture->lectureStart = 0;
        temp_lecture->lectureEnd = times[i];
        temp_lecture->roomNumber = i;
        //Insert element
        insert(myHeap,temp_lecture);
    }

    //Delete min. value from heap and then add new value
    for (i = numRooms; i < length; i++) {
        //Delete min
        struct lecture* temp_lecture2 = deleteMin(myHeap);
        //Setup temp_lecture2
        temp_lecture2->lectureStart = temp_lecture2->lectureEnd;
        temp_lecture2->lectureDuration = times[i];
        temp_lecture2->lectureEnd = times[i] + temp_lecture2->lectureStart;
        //Insert
        insert(myHeap, temp_lecture2);
    }

    //Empty heap up until second-to-last element
    for (i = 0; i < numRooms - 1; i++) {
        struct lecture* temp_lecture3 = deleteMin(myHeap);
        free(temp_lecture3);
    }

    //Last lecture
    struct lecture* last_lecture = deleteMin(myHeap);
    k = last_lecture->lectureEnd;

    //Free last_lecture
    free(last_lecture);

    return k;
}

struct heap* initHeap() {
    int i;

    struct heap* h;

    //Allocate space for the heap and set the size for an empty heap.
    h = (struct heap*)malloc(sizeof(struct heap));
    h->capacity = SIZE;
    h->heaparray = (struct lecture**)malloc(sizeof(struct lecture*)*(SIZE+1));
    for (i = 0; i < SIZE; i++)
        h->heaparray[i] = (struct lecture*)malloc(sizeof(struct lecture));
    h->size = 0;
    return h;
}

int binsearch(int* times, int length, int maxTime) {
    //Max, min, and mid
    int max = length + 1;
    int min = 1;
    int mid;
    int simval;

    while (min <= max) {
        mid = (max + min)/2;
        simval = simulate(times, length, mid);
        printf("mid = %d simval = %d\n",mid,simval);

        //Found max time
        if (simval == maxTime) return mid;

        //Too long
        if (simval > maxTime) {
            min = mid + 1;
            //printf("%d\n",mid);
        }

        //Too short
        else {
            max =  mid - 1;
            //printf("%d\n",mid);
        }
    }

    //Fix particular case
    if (simval > maxTime) return mid + 1;
    else return mid;
}

int compare(struct lecture* a, struct lecture* b) {
    //Compare by end times
    if (a->lectureEnd != b->lectureEnd)
        return a->lectureEnd - b->lectureEnd;
    //Break ties by room number
    return a->roomNumber - b->roomNumber;
}

void swap(struct lecture** ptrA, struct lecture** ptrB) {
    struct lecture* tmp = *ptrA;
    *ptrA = *ptrB;
    *ptrB = tmp;
}

void insert(struct heap* hPtr, struct lecture* item) {

    //Increment the size of the heap.
    hPtr->size++;

    //Copy new item into first open slot in the heap.
    hPtr->heaparray[hPtr->size] = item;

    //Now percolate this item up so it goes to its rightful place.
    percolateUp(hPtr, hPtr->size);
}

struct lecture* deleteMin(struct heap* hPtr) {

    //If empty, return
    if(hPtr->size == 0) return NULL;

    //This is the item to delete.
    struct lecture* retval = hPtr->heaparray[1];

    //Copy last item into first slot.
    hPtr->heaparray[1] = hPtr->heaparray[hPtr->size];

    //Now our heap has one fewer item.
    hPtr->size--;

    //Now, we just have to percolate this item down.
    percolateDown(hPtr, 1);

    //This is the pointer I need to return.
    return retval;
}

void percolateUp(struct heap* hPtr, int index) {

    //At root, can't go up any more.
    if (index == 1) return;

    int pIndex = index/2;

    //Node and parent are out of order, swap and recurse.
    if (compare(hPtr->heaparray[index], hPtr->heaparray[pIndex]) < 0) {
        swap(&hPtr->heaparray[index], &hPtr->heaparray[pIndex]);
        percolateUp(hPtr, pIndex);
    }
}

void percolateDown(struct heap* hPtr, int index) {

    //Leaf node
    if (2*index > hPtr->size) return;

    //You only have a left child
    if (2*index == hPtr->size) {

        //Last swap
        if (compare(hPtr->heaparray[2*index], hPtr->heaparray[index]) < 0)
            swap(&hPtr->heaparray[2*index], &hPtr->heaparray[index]);
        return;
    }

    //Figure out whether or not the left or right child is better.
    int swapIndex = compare(hPtr->heaparray[2*index], hPtr->heaparray[2*index+1]) < 0 ? 2*index : 2*index+1;

    // Node and parent are out of order, swap and recurse.
    if (compare(hPtr->heaparray[swapIndex], hPtr->heaparray[index]) < 0) {
        swap(&hPtr->heaparray[swapIndex], &hPtr->heaparray[index]);
        percolateDown(hPtr, swapIndex);
    }
}

