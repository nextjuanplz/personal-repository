//RP4 - duck.c
//Juan M. Alzate Vanegas
//5-29-2017
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Constants
#define NAME_LENGTH 20
#define MAX_VAL 2000

//Linked list node
typedef struct node {
    char Name[NAME_LENGTH + 1];
    struct node* next;
}node;

//Function prototypes
void readNames(struct node*, int);
void insertToBack(struct node*, char[]);
void playGame(struct node*, int, int, int, int);
void eliminate(struct node**, int);
void printFriends(struct node*, int);
void selectionSort(char**, int);
void swap(char**, char**);
void printList(char**, int);

//Functions
int main() {
    //Input
    int g;      //Number of games
    int f;      //Number of friends Jimmy has before starting the game
    int r;      //Number of rounds Jimmy plays
    int d;      //Number of times Jimmy says "Duck" before giving the boot

    //Counters & indexes
    int i = 0;

    //Scan number of games
    scanf("%d",&g);
    //Check for invalid values of g
    while(g < 0) {
        puts("Please try again.");
        scanf("%d",&g);
    }

    //Process games
    while (i < g) {

        //Scan initial number of friends
        scanf("%d",&f);
        //Check for invalid values of f
        while(f < 0) {
            puts("Please try again.");
            scanf("%d",&f);
        }

        //Start circular linked list
        struct node* head = (struct node*)malloc(sizeof(struct node));
        head->next = head;

        //Scan names
        readNames(head, f);

        //Scan number of rounds and number of times he says Duck
        scanf("%d %d",&r,&d);
        //Check for invalid values of r and d
        while(r <= 0 || r > MAX_VAL || d <= 0 || d > MAX_VAL) {
            puts("Please try again.");
            scanf("%d %d",&r,&d);
        }

        //Play game
        playGame(head, i, f, r, d);

        //Free list
        free(head);

        //Increment game number
        i++;

        //New line
        if (i < g) printf("\n");
    }
    //Normal execution
    return 0;
}

void readNames(struct node* head, int f) {
    //Counter
    int friends;
    //Storage for name
    char name[NAME_LENGTH + 1];

    //Read all friend names
    for(friends = 0; friends < f; friends++) {
        scanf("%s",name);
        //Empty case
        if (friends == 0) strcpy(head->Name, name);
        //Insert to back of list
        else insertToBack(head, name);
    }
}

void insertToBack(struct node* head, char name[]) {
    //Navigational node
    struct node* iter;

    //Create temp node and store name
    struct node* temp = (struct node*)malloc(sizeof(struct node));
    strcpy(temp->Name,name);

    //Adding to an empty list
    if (head == NULL) {
        //Self-linking node
        temp->next = temp;

        //temp is the new front of the list
        head = temp;
    }

    //Add to an existing list
    else {
        //Link new node to the first element in the old list
        temp->next = head;

        //List with one element
        if (head->next == head) {
            head->next = temp;
            return;
        }

        //Iterate to the last element in the old list
        iter = head;
        while (iter->next != head)
            iter = iter->next;

        //Relink to our new first element, temp.
        iter->next = temp;
    }
}

void playGame(struct node* head, int g, int f, int r, int d) {
    //Counters
    int rounds;

    //Store original value of f
    int f_orig = f;

    //Header
    printf("Game %d:\n",g + 1);

    for (rounds = 0; rounds < r; rounds++) {
        //Eliminate a friend
        eliminate(&head, d);

        //Update number of friends
        f_orig--;

        //No friends left
        if (f_orig <= 0) {
            puts("Jimmy has friends no more.");
            return;
        }
    }

    //Some friends left
    printFriends(head, f_orig);
}

void eliminate(struct node** head, int d) {
    struct node* prev;

    //Cases of length = 0, 1
    if ((*head) == NULL)
        return;
    if ((*head) == (*(head))->next) {
        free(*head);
        return;
    }

    //Counters
    int d_count;

    //Store node to be deleted
    struct node* del = *head;

    //Navigate to the node
    for (d_count = 0; d_count < d; d_count++) {
        prev = del;
        del = del->next;
    }

    //Point current node to one after del node
    prev->next = del->next;

    //Point to new head of list
    *head = del->next;

    //Free node
    free(del);

}

void printFriends(struct node* head, int f) {
    //Case of 1 friend
    if (f == 1) {
      printf("%s\n",head->Name);
      return;
    }

    //Navigational node
    struct node* nav = head;

    //Counter
    int i;

    //Array of names
    char** friendlist = (char**)malloc(f * sizeof(char*));
    for (i = 0; i < f; i++)
        friendlist[i] = (char*)malloc((NAME_LENGTH + 1)* sizeof(char));

    //Navigate list and copy values into friendlist array
    i = 0;
    do {
        //Copy name into array
        strcpy(friendlist[i],nav->Name);

        //Navigate list
        nav = nav->next;

        //Increment i
        i++;

    } while(nav != head);
    //Sort list
    selectionSort(friendlist,f);

    //Print list
    printList(friendlist,f);

    //Free list
    for (i = 0; i < f; i++)
        free(friendlist[i]);
    free(friendlist);
}


void selectionSort(char** array, int n) {
     int cur, j, smallest;
     for (cur = 0; cur < n; cur++) {
         //First unplaced element is stored
         smallest = cur;

         // Look through the other indices to find a name that should be placed first
         for (j = cur + 1; j < n; j++)

             //Name that should be placed first
             if (strcmp(array[j],array[smallest]) < 0)
                 smallest = j;

         //Swap smallest element left into its correctly sorted location
         swap(&array[cur], &array[smallest]);
     }
}

void swap(char** a, char** b) {
     char temp[NAME_LENGTH + 1];
     strcpy(temp, *a);
     strcpy(*a,*b);
     strcpy(*b,temp);
}

void printList(char** array, int n) {
    //Index
    int i;
    for (i = 0; i < n; i++)
        printf("%s\n",array[i]);
}





