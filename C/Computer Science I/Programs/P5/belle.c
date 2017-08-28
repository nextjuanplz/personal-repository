//P5 - Binary Belle
//COP 3502C
//Juan Manuel Alzate Vanegas
//3-28-2017

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Constants
#define MAX_NAME 19             //Max file/directory name length
#define MAX_DEPTH 100           //Max depth in binary tree
#define DIR_SIZE 17             //Size of a subdirectory in bytes

//File node
typedef struct filenode {
    int isDir;                  //Boolean flag {0 = file, 1 = directory}
    char name[MAX_NAME+1];           //File or directory name
    struct filenode* left;      //Pointer to left branch
    struct filenode* right;     //Pointer to right branch
    struct filenode* parent;    //Pointer to parent
    int numFiles;               //Number of files in directory
    int numSubDir;              //Number of subdirectories in directory
    int totalSize;              //Total size of directory
} filenode;

//Function prototypes
//Modification
struct filenode* create_node(char[], struct filenode*, int);
void insertNode(struct filenode*, struct filenode*, char[]);
void deleteNode(struct filenode* root, char[]);
void freeAll(struct filenode*);
//Traversal
int find(struct filenode*, char[]);
struct filenode* findNode(struct filenode*, char[]);
//Report values
int num_Files(struct filenode*, char[]);
int num_SubDir(struct filenode*, char[]);
int totSize(struct filenode*, char[]);

//Functions
int main() {
    //Create root directory
    struct filenode* C = create_node("C",NULL,DIR_SIZE);

    //Scan instructions
    int numInstructions, instruction, instructions_index = 0;
    scanf("%d",&numInstructions);

    while(instructions_index <= numInstructions){
        scanf("%d",&instruction);
        char path[MAX_DEPTH];
        char Name[MAX_NAME + 1];
        int node_size;

        switch(instruction) {
        case 1: //Create node

            //Scan directory path
            scanf("%s",path);

            //Scan directory name
            scanf("%s",Name);

            //Scan size
            scanf("%d",&node_size);

            //Create node and insert
            struct filenode* node = create_node(Name, NULL, node_size);
            insertNode(C, node, path);

            break;

        case 2://Delete directory
            //Scan directory path
            scanf("%s",path);

            //Delete node
            deleteNode(C, path);

            break;

        case 3://Number of files
            //Scan directory path
            scanf("%s",path);

            //Print number of files
            printf("%d\n",num_Files(C,path));

            break;

        case 4://Number of subdirectories
            //Scan directory path
            scanf("%s",path);

            //Print number of subdirectories
            printf("%d\n",num_SubDir(C,path));

            break;

        case 5://Total size
            //Scan directory path
            scanf("%s",path);

            //Print total size
            printf("%d\n",totSize(C, path));

            break;
      }

      //Advance to next instruction
      instructions_index++;
    }

    return 0;
}

struct filenode* create_node(char Name[], struct filenode* Parent, int Size) {
  // Allocate space for the node, set the fields.
  struct filenode* temp;
  temp = (struct filenode*)malloc(sizeof(struct filenode));
  strcpy(temp->name, Name);
  temp->parent = Parent;
  temp->left = NULL;
  temp->right = NULL;
  temp->numFiles = 0;
  temp->numSubDir = 0;
  temp->totalSize = Size;

  //Set as directory or file
  if (strlen(Name) == 1) temp->isDir = 1;
  else temp->isDir = 0;

  return temp;
}

void insertNode(struct filenode* root, struct filenode* element, char directory[]) {
  //Empty tree
  if (root == NULL)
    //Create new root
    root = element;

  //Non-empty tree
  else {

    //Update values
    element->parent = root;
    //Subdirectory
    if(element->isDir) {
        root->numSubDir++;
        root->totalSize += DIR_SIZE;

    }
    //File
    else {
        root->numFiles++;
        root->totalSize += element->totalSize;

    }

    //Insert to the right
    if (directory[0] == 'R') {

      //Subdirectory to the right
      if (root->right != NULL)
        insertNode(root->right, element, &directory[1]);

      //Place node directly to the right of root
      else
        root->right = element;
    }

    //Insert to the left
    else {

      //Subdirectory to the left
      if (root->left != NULL)
        insertNode(root->left, element, &directory[1]);

      //Place node directly to the left of root
      else
        root->left = element;
    }
  }
}

void deleteNode(struct filenode* root, char Name[]) {

  struct filenode * delnode, * save_node, * par;

  delnode = findNode(root, Name); // Get a pointer to the node to delete

  par = delnode->parent; // Get the parent of this node
  save_node = par;

  //Update values
  while(par != NULL) {
      if (delnode->isDir) {
            par->numSubDir -= (delnode->numSubDir + 1);
            par->numFiles -= delnode->numFiles;
      }
      else
            par->numFiles--;

      par->totalSize -= delnode->totalSize;
      par = par->parent;
  }

  par = save_node;

  if (par == NULL) {
    free(root); // free the memory for the node
  }

  // Deletes the node if it's a left child.
  if (Name[strlen(Name)-1] == 'L') {
    freeAll(par->left);
    par->left = NULL;
  }

  // Deletes the node if it's a right child.
  else {
    freeAll(par->right);
    par->right = NULL;
  }
  return;
}

void freeAll(struct filenode* node){
    if(node->left != NULL)
        freeAll(node->left);
    if(node->right != NULL)
        freeAll(node->right);
    free(node);
}

int num_Files(struct filenode* root, char directory[]) {
    struct filenode* node = findNode(root, directory);
    if(node != NULL) return node->numFiles;
    else return 0;
}
int num_SubDir(struct filenode* root, char directory[]) {
    struct filenode* node = findNode(root, directory);
    if(node != NULL) return node->numSubDir;
    else return 0;
}

int totSize(struct filenode* root, char directory[]) {
    struct filenode* node = findNode(root, directory);
    if(node != NULL) return node->totalSize;
    else return 0;
}

struct filenode* findNode(struct filenode* current_ptr, char Name[]) {
  char path = Name[0];

  // Check if there are nodes in the tree.
  if (current_ptr != NULL) {

    // Base case
    if (path == '\0')
      return current_ptr;

    // Root
    if (path == 'C')
        return current_ptr;

    // Search to the left.
    if (path == 'L')
      return findNode(current_ptr->left, &Name[1]);

    // Search to the right.
    else
      return findNode(current_ptr->right, &Name[1]);

  }
  else
    return NULL; // No node found.
}



