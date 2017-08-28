// Juan Manuel Alzate Vanegas
// 4/23/2017
// P7 - Conference
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
int simulate(struct lecture**, int, int);
int binsearch(struct lecture**, int, int);
int compare(struct lecture*,struct lecture*);
void swap(struct lecture**, struct lecture**);
void insert(struct heap*, struct lecture*);
struct lecture* deleteMin(struct heap*);
void percolateUp(struct heap*, int);
void percolateDown(struct heap*, int);
void Merge(struct lecture**, int, int, int);
void MergeSort(struct lecture**, int, int);
void printTalks(struct lecture**, int n);

int main() {
    //Inputs
    int n;           //Number of lectures that must be scheduled
    int t;           //Max time alloted for conference (s)
    int n_count;     //Lecture index

    //Output
    int numRooms;    //Min number of rooms needed

    //Scan inputs
    scanf("%d %d",&n,&t);

    //Check valid inputs
    while (t > MAX_T || n > MAX_T) {
        printf("Try again.\n");
        scanf("%d %d",&n,&t);
    }

    //Create array of lectures
    struct lecture** talks = (struct lecture**)malloc(n * sizeof(struct lecture*));

    //Process each lecture
    for (n_count = 0; n_count < n; n_count++) {
        talks[n_count] = (struct lecture*)malloc(sizeof(struct lecture));
        scanf("%s %d",talks[n_count]->lectureName,&talks[n_count]->lectureDuration);
    }

    numRooms = binsearch(talks,n-1,t);
    printf("%d\n",numRooms);

    //Run simulate again to populate with appropriate values for simulation
    simulate(talks, n, numRooms);

    //Sort
    MergeSort(talks, 0, n-1);

    //Print talks
    printTalks(talks, n);

    return 0;
}

int simulate(struct lecture** talks, int length, int numRooms) {
    //Heap
    struct heap* myHeap = initHeap();

    //Index
    int i;

    //Last lecture
    int k;

    //Initialize each element and add first numRoom items in times to myHeap
    for (i = 0; i < numRooms; i++) {
        talks[i]->lectureStart = 0;
        talks[i]->lectureEnd = talks[i]->lectureDuration;
        talks[i]->roomNumber = i;
        //Insert element
        insert(myHeap,talks[i]);
    }

    //Delete min. value from heap and then add new value
    for (i = numRooms; i < length; i++) {
        //Delete min
        struct lecture* temp_lecture2 = deleteMin(myHeap);
        //Setup
        talks[i]->lectureStart = temp_lecture2->lectureEnd;
        talks[i]->lectureEnd = talks[i]->lectureStart + talks[i]->lectureDuration;
        talks[i]->roomNumber = temp_lecture2->roomNumber;
        //Insert
        insert(myHeap, talks[i]);
    }

    //Empty heap up until second-to-last element
    for (i = 0; i < numRooms - 1; i++)
        deleteMin(myHeap);

    //Last lecture
    struct lecture* last_lecture = deleteMin(myHeap);
    k = last_lecture->lectureEnd;

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

int binsearch(struct lecture** talks, int length, int maxTime) {
    //Max, min, and mid
    int max = length + 1;
    int min = 1;
    int mid;
    int simval;

    while (min <= max) {
        mid = (max + min)/2;
        simval = simulate(talks, length, mid);

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

void Merge(struct lecture** talks, int start, int middle, int end) {
    //Variables
    int i, length, count1, count2, mc;

    length = end - start + 1;

    //Allocate memory dynamically
    struct lecture** temp = (struct lecture**)malloc(length * sizeof(struct lecture*));
    for (i = 0; i < length; i++)
        temp[i] = (struct lecture*)malloc(sizeof(struct lecture));

    //Left and right indexes
    count1 = start;
    count2 = middle;
    // Keep track of index into auxiliary array
    mc = 0;

    //Copy values into auxiliary array while there are numbers from both to copy
    while ((count1 < middle) || (count2 <= end)) {
        // Next value to copy comes from list one - make sure list 1 isn't exhausted yet. Also make sure we don't access index count2 if we aren't supposed to.
        if (count2 > end || (count1 < middle && (strcmp(talks[count1]->lectureName, talks[count2]->lectureName) < 0))) {
            temp[mc] = talks[count1];
            count1++;
            mc++;
        }

        // We copy the next value from list two.
        else {
            temp[mc] = talks[count2];
            count2++;
            mc++;
        }
    }
    // Copy back all values into original array.
    for (i = start; i <= end; i++)
        talks[i] = temp[i - start];
    free(temp);
}

void MergeSort(struct lecture** talks, int start, int end) {
    int mid;
    // Check if sorting range is at least one element
    if (start < end) {
        mid = (start + end) / 2;
        // Sort  first half
        MergeSort(talks, start, mid);

        // Sort second half
        MergeSort(talks, mid + 1, end);

        // Merge lists
        Merge(talks, start, mid + 1, end);
    }
}

void printTalks(struct lecture** talks, int n) {
    int i;
    for (i = 0; i < n; i++)
        printf("%s %d %d\n",talks[i]->lectureName,talks[i]->roomNumber,talks[i]->lectureStart);
}
