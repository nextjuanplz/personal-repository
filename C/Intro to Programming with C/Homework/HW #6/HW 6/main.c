//Juan M. Alzate Vanegas - HW #6
//Building and Working with Simple Linked Lists
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//Sets up node struct
typedef struct node {
    int number;
    struct node * next;
} Node;

//Pointer to node as data type
typedef Node * Nodeptr;

//User-defined functions
void printout(Nodeptr); //Prints out linked list
void sum(Nodeptr); //Adds up number elements of linked list
void reverse(Nodeptr); //Reverses linked list

int main()
{
    int n = 0;
    Nodeptr head = NULL;
         if ((head = malloc(sizeof(Node)))== NULL) //check for dynamic allocation of memory to head {
            return 0;
        }
    srand(time(NULL)); //rand() time seed

    //First node seed
    head->number = (rand() % 50) + 50;
    head->next = NULL;

    //Tail
    Nodeptr tail = head;
    Nodeptr newnode;

    for (n = 0; n < 9; n++) //Fills up linked list
     {
         if ((newnode = malloc(sizeof(Node)))== NULL)
            return 0;

         tail->next = newnode;
         newnode->next = NULL;
         newnode->number = (rand() % 50) + 50; //Makes number element random number from 50-100 inclusive

         tail = newnode;
     }

     printout(head);
     reverse(head);

     head = tail; //Sets tail as the beginning of the linked list

     printout(head);
     sum(head);

     while (head != NULL) //Frees up dynamically-allocated memory from list
     {
        newnode = head->next;
        free(head);
        head = newnode;
     }

     return 0;
}

void printout(Nodeptr a)
{
    int n = 0;
    while (a != NULL) {
        printf("Node #%d contains %d.\n",n,a->number);
        n++;
        a = a->next;
    }
    printf("\n"); //To add space between different lists
}

void sum(Nodeptr a)
{
    int add = 0;
    while (a != NULL)
    {
        add = add + a->number;
        a = a->next;
    }
    printf("\nThe sum total of all nodes in this list is %d.\n\n",add);
}

void reverse(Nodeptr a)
{
    Nodeptr temp = a->next;
    Nodeptr save = temp->next;
    a->next = NULL;

    while (temp != NULL)
    {
        temp->next = a;
        a = temp;
        temp = save;
        if (save != NULL)
        {
            save = save->next;
        }
    }
}


