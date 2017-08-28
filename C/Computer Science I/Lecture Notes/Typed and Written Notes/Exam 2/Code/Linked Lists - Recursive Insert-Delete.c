// Arup Guha
// 2/5/2013
// Written for COP 3502H - Recursive linked list insert, delete
#include <stdio.h>
#include <stdlib.h>

struct node {
     int data;
     struct node  *next;
};

struct node* insert(struct node* list,int d );
struct node* del(struct node* list,int d );
void print( struct node *list);
void freelist(struct node* list);

int main( ) {

    int number = 0, choice;
    struct node *pList=NULL;

    // Let the user add values until they enter -1.
    while(number!= -1) {

        // Get the operation.
        printf("Do you want to (1)insert, (2)delete, (3)quit.\n");
        scanf("%d", &choice);

        // Done.
        if (choice >= 3) break;

        // Get the number.
        printf("Enter the value to insert or delete.\n");
        scanf("%d", &number);

        // Execute the operation.
        if (choice == 1)
            pList = insert(pList, number);
        else
            pList = del(pList, number);

        // Look at the list.
        printf("Items in linked list: ");
        print(pList);
        printf("\n");
    }

    // Free this memory.
    freelist(pList);
    return 0;
}

// Recursively inserts item into the LL pointed to by list.
struct node* insert(struct node *list,int item) {

    // Node is at front, insert and return.
    if (list == NULL || item <= list->data) {
        struct node * pNew=(struct node *) (malloc(sizeof(struct  node)));
        pNew->data = item;
        pNew->next = list;
        return pNew;
    }

    // Recursively insert and return the front of the list.
    list->next = insert(list->next, item);
    return list;
}

// Recursively deletes the first node storing item in the list pointed to
// by list. If no such node exists, no changes are made. a pointer to the front
// of the resulting list is returned.
struct node* del(struct node *list, int item) {

    // Simple case.
    if (list == NULL)
        return NULL;

    // Free this node and return a pointer to the rest of the list.
    if (list->data == item) {
        struct node* rest = list->next;
        free(list);
        return rest;
    }

    // Recursively delete and return the front of the list.
    list->next = del(list->next, item);
    return list;
}

void print(struct node *list) {

    // Iterate through each item and print it!
    while (list != NULL) {
        printf("-->%d", list->data);
        list = list->next;
    }

    printf("\n");
}

// Frees each node in the LL pointed to by list.
void freelist(struct node* list) {

    // We can stop.
    if (list == NULL) return;

    // Free the rest of the list.
    if (list->next != NULL)
        freelist(list->next);

    // Free first node.
    free(list);
}
