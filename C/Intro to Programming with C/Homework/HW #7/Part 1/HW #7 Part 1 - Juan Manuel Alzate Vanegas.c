//HW #7 Part 1 - Juan M. Alzate Vanegas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Sets up node struct
typedef struct node {
    int number;
    struct node * next;
} Node;

//Pointer to node as data type
typedef Node * Nodeptr;

void main()
{
    Nodeptr head = malloc(sizeof(Node));
    if (head == NULL)
    {
        puts("malloc() failed.\n");
        exit(0);
    }

    head->number = 99;
    head->next = NULL;

    int n = 99, input, comp, node_pos;
    Nodeptr tail = head, aux;

    for (n = 98; n > -1; n--) //List creation
    {
        aux = malloc(sizeof(Node));
        if (aux == NULL)
        {
            puts("malloc() failed.\n");
            exit(0);
        }

        tail->next = aux;
        aux->next = NULL;
        aux->number = n;
        tail = aux;
    }
    while (input != -1) //Exit condition
    {
        puts("\nPlease enter the number to be sought in the list. Type -1 to stop.\n");
        scanf("%d",&input);
        aux = head;
        node_pos = 1;

        if (input == -1) //Prevents program from searching for -1
            {
                puts("Goodbye.");
                break;
            }
        else if (input == head->number) //Separate case for head of list
        {
            node_pos = 1;
            comp = head->number;
        }
        else //Sequential search
        {
            while (aux != NULL)
            {
                comp = aux->number;
                if (input == comp)
                    break;

                aux = aux->next;
                node_pos++;
            }
        }

        if (comp == input) //Prints node position if input is found
            printf("The number %d was found in node #%d.\n",input,node_pos);

        else //Prints failure if input cannot be found
            printf("Search failed.\n");
    }
}
