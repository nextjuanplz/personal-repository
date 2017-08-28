//P3 - birthday (COP 3502C)
//Juan M. Alzate Vanegas
//2-26-2017

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define maxNameLength 30
#define maxNumStudents 1000
#define monthLength 10

//Constants and global variables
const int noleap[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
const char monthNames[][monthLength] = {"JANUARY", "FEBRUARY", "MARCH", "APRIL",
                                        "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
                                        "OCTOBER", "NOVEMBER", "DECEMBER"};
int leapyear_check;

//Data types
struct student{
    char first_name[maxNameLength];
    char last_name[maxNameLength];
    char birth_month[monthLength];
    int birth_day;
    int birth_year;
};

//User-defined functions
void readValues(struct student**, int*);
int monthValue(char*);
int dayNumber(char*, int);
int findVal(struct student**, int, char[maxNameLength], char[maxNameLength]);
void swap(struct student* a, struct student* b);
void Merge(struct student**, int, int, int);
void MergeSort(struct student**, int, int);
int compare(struct student*, struct student*);
void query(struct student**, int);
void processQueries(struct student**, int, int);


int main() {
    //Variables
    int n;                      //Number of classes
    int k;                      //Number of students in a class
    int m;                      //Number of queries

    //Counters
    int class_counter = 0;      //Counter for classes

    //Read number of classes
    scanf("%d", &n);

    //Check for illegal number of classes
    while(n < 1) {
        printf("The number of cases is too small. Try again.\n");
        scanf("%d", &n);
    }

    while (class_counter < n){
        //Read in number of students
        scanf("%d", &k);

        //Check for illegal number of students
        while(k < 1) {
            printf("The number of students is too small. Try again.\n");
            scanf("%d", &k);
        }
        while(k > maxNumStudents) {
            printf("The number of students is too large. Try again.\n");
            scanf("%d", &k);
        }

        //Read values
        struct student* classArray[maxNumStudents];

        readValues(classArray, &k);

        //MergeSort by Day Number
        MergeSort(classArray, 0, k - 1);

        //Read in number of queries
        scanf("%d", &m);

        //Check for illegal number of queries
         while(m < 1 ) {
            printf("The number of queries is too large. Try again.\n");
            scanf("%d", &m);
        }
        while(m > k) {
            printf("The number of queries is too large. Try again.\n");
            scanf("%d", &m);
        }

        //Class number
        printf("Class #%d\n",class_counter + 1);

        //Process each query
        processQueries(classArray, m, k);

        //Go onto the next class
        class_counter++;
    }
    return 0;
}

//Read data into array of pointers to struct
void readValues(struct student** array, int* arraySize) {
    int i;

    //Check for leap years
    leapyear_check = 0;

    for (i = 0; i < *(arraySize); i++) {
        //Allocate memory dynamically
        array[i] = (struct student*)malloc(sizeof(struct student));

        //Read values
        scanf("%s", array[i]->first_name);
        scanf("%s", array[i]->last_name);
        scanf("%s", array[i]->birth_month);
        scanf("%d", &array[i]->birth_day);

        //Change leapyear_check flag if February 29th is found
        if ((monthValue(array[i]->birth_month) == 1) && (array[i]->birth_day == 29))
            leapyear_check = 1;

        scanf("%d", &array[i]->birth_year);
    }
}

//Take in month and return value
int monthValue(char* monthName) {
    int i;
    for (i = 0; i < 12; i++)
        if (strcmp(monthName, monthNames[i]) == 0) break;
    return i;
}

//Day number
int dayNumber(char* monthName, int birthDay) {
    int i = monthValue(monthName);
    int days = noleap[i] + birthDay;

    //Add 1 for leap years after February
    if(i > 1 && leapyear_check == 1) days += 1;

    return days;
}

//Returns index of a name found in a list
int findVal(struct student** list, int len, char firstName [maxNameLength], char lastName [maxNameLength]) {
    int i;
    for (i = 0; i < len; i ++)
        if ((strcmp(list[i]->last_name, lastName) == 0) && (strcmp(list[i]->first_name, firstName) == 0)) break;
    return i;
}

//Swaps 2 struct pointers
void swap(struct student* a, struct student* b){
    struct student temp = *a;
    *a = *b;
    *b = temp;
}

//Sorts list by day number
void Merge(struct student** array, int start, int middle, int end) {
    //Variables
    int i, length, count1, count2, mc;

    length = end - start + 1;

    //Allocate memory dynamically
    struct student** temp = (struct student**)malloc(length * sizeof(struct student*));
    for (i = 0; i < length; i++)
        temp[i] = (struct student*)malloc(sizeof(struct student));

    // Left and right indexes
    count1 = start;
    count2 = middle;
    // Keep track of index into auxiliary array
    mc = 0;
    // Copy values into auxiliary array while there are numbers from both to copy

    while ((count1 < middle) || (count2 <= end)) {
        // Next value to copy comes from list one - make sure list 1 isn't exhausted yet. Also make sure we don't access index count2 if we aren't supposed to.
        if (count2 > end || (count1 < middle && (compare(array[count1], array[count2]) < 0))) {
            temp[mc] = array[count1];
            count1++;
            mc++;
        }

        // We copy the next value from list two.
        else {
            temp[mc] = array[count2];
            count2++;
            mc++;
        }
    }
    // Copy back all values into original array.
    for (i = start; i <= end; i++)
        array[i] = temp[i - start];
    free(temp);
}

//MergeSort
void MergeSort(struct student** array, int start, int end) {
    int mid;
    // Check if sorting range is at least one element
    if (start < end) {
        mid = (start + end) / 2;
        // Sort  first half
        MergeSort(array, start, mid);

        // Sort second half
        MergeSort(array, mid + 1, end);

        // Merge lists
        Merge(array, start, mid + 1, end);
    }
}

//Compare 2 students based on sorting criteria
int compare(struct student* a, struct student* b) {
    int days_a = dayNumber(a->birth_month, a->birth_day);
    int days_b = dayNumber(b->birth_month, b->birth_day);

    //Compare day numbers
    int dayNumber_comp = days_a - days_b;
    if (dayNumber_comp < 0) return -1;
    if (dayNumber_comp > 0) return 1;

    //Last name tie-breaker
    int lastName_comp = strcmp(a->last_name, b->last_name);
    if (lastName_comp < 0) return -1;
    if (lastName_comp > 0) return 1;

    //Last name tie-breaker
    int firstName_comp = strcmp(a->first_name, b->first_name);
    if (firstName_comp < 0) return -1;
    if (firstName_comp > 0) return 1;

    //Same exact student
    return 0;
}

//Process a query
void query(struct student** roster, int roster_length) {
    char firstName[maxNameLength];
    char lastName[maxNameLength];

    scanf("%s %s", firstName, lastName);

    int search_index = findVal(roster, roster_length, firstName, lastName);
    int above = search_index + 1, below = search_index - 1;

    //Check for search_index out of bounds
    if (below == -1) below = roster_length - 1;
    if (above == roster_length) above = 0;

    //Check to ensure we do not get out of the array
    if (search_index >= 0 && search_index < roster_length) {
        //Compare against student above
        int upper_distance = dayNumber(roster[above]->birth_month, roster[above]->birth_day) - dayNumber(roster[search_index]->birth_month, roster[search_index ]->birth_day);
        upper_distance = abs(upper_distance);
        //Compare against student below
        int lower_distance = dayNumber(roster[search_index]->birth_month, roster[search_index]->birth_day) - dayNumber(roster[below]->birth_month, roster[below]->birth_day);
        lower_distance = abs(lower_distance);

        //Choose person before query if lower_distance is smaller
        if (upper_distance > lower_distance)
            printf("%s %s has the closest birthday to %s %s\n", roster[below]->first_name, roster[below]->last_name,
                                                                roster[search_index]->first_name, roster[search_index]->last_name);

        //Choose person after query if upper_distance is the same or smaller
        else
            printf("%s %s has the closest birthday to %s %s\n", roster[above]->first_name, roster[above]->last_name,
                                                                roster[search_index]->first_name, roster[search_index]->last_name);
    }
    return;
}

//Process all queries for a class
void processQueries(struct student** roster, int query_max, int roster_length) {
    int query_counter = 0;
    while (query_counter < query_max){
            query(roster, roster_length);
            query_counter++;
    }
}
