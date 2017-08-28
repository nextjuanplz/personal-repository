#include <stdio.h>
#include <stdlib.h>
#include <

//Constants
#define FILE_NAME_LENGTH 40									//Length of file name
#define ERROR -1											//Error code{0 = OK, -1 = DMA, -2 = fin, -3 = fout}
#define HEADER_FLAG 1                                       //Designates if a header is output
#define OUTPUT_TYPE 0                                       //Designates use {0 = Output/Test, 1 = Data}

//Files
char* inputfileName;										//Input file
char* outputfileName;										//Output file

//List node
struct node {
	double data;
	struct node* next;
	struct node* prev;
};

//Output array
double* outputArray;

//Output variables
double avgSpeed;											//Average speed

//Functions
char* enterFile();											//File name entry
FILE* readFile(char*);										//Reads file
struct node* createNode();									//Creates new node for the list
struct node* readList(FILE*);								//Creates list out of file
void calculateSpeed(struct node*);							//Calculates speed from list

int main() {
	//Counters for files to be processed
	int file_count = 0;										//File counter
	int files;												//Number of files to be processed

	//Header
	char header[] = "|\tFile  \tAverage speed (s.)  |\n--------------------------------------------------------------\n";

	//Opening prompt
	printf("How many data files?\n");
	scanf("%d",&files);
	printf("\n");

	//Check for invalid number of participants
	if (isdigit(files)) printf("Please try again.\n");
	while(isdigit(files))
        scanf("%d",&files);

    //Output file name entry
    printf("Please enter the OUTPUT file name.\n(Be careful, as this will overwrite an existing file of the same name!)\n");
    char* outputfileName = enterFile();

    //Check for dynamic memory allocation
    if(outputfileName == NULL) {
        printf("There was insufficient memory to read the output file name.\n");
        return ERROR;
    }

    //Open output file
    FILE* fout = fopen(outputfileName, "w");
    //Check that output file was opened correctly.
    if(fout == NULL) {
        printf("Output file %s could not be read.",outputfileName);
        return ERROR - 2;
    }

    //Print header
    if(HEADER_FLAG) {
        fprintf(fout,"--------------------------------------------------------------\n");
        fprintf(fout,header);
    }

	//Allocate memory to output array
    outputArray = (double*)malloc(files * sizeof(double));

	//Compute values for all files requested
	while(file_count < (int)files) {
		//Input file name entry
		printf("\nPlease enter data file #%d.\n",file_count + 1);
		char* inputfileName = enterFile();

        //Check for dynamic memory allocation
		if(inputfileName == NULL) {
			printf("There was insufficient memory to read the input file name.\n");
			return ERROR;
		}

		//Open input file
		FILE* fin = fopen(inputfileName, "r");

		//Check that files were opened correctly
		if(fin == NULL) {
	        printf("Input file %s could not be read.",inputfileName);
	        return ERROR - 1;
		}

		//Read file into list
		struct node* myList = readList(fin);

		//Calculate speed from list
		calculateSpeed(myList);

        //File output
        if (OUTPUT_TYPE) fprintf(fout, "%-s\t%-f\n\n",inputfileName,avgSpeed);
        else fprintf(fout, "\t%-s\t     \t\t\t   %-.3f\n\n",inputfileName,avgSpeed);

		//Array output
        outputArray[file_count] = avgSpeed;

        //Free list
        free(myList);

		//Free memory for file names
		free(inputfileName);
		free(outputfileName);

		//Close input file
	    fclose(fin);

	    //Update number of files processed so far
		file_count++;
	}

	//Standard output
    file_count = 0;
    printf("--------------------------------------------------------------\n");
    printf(header);
    for (file_count = 0; file_count < files; file_count++)
        printf("\t  %-d\t     %-.3f\n\n",file_count + 1,outputArray[file_count]);

    //Free array memory
    free(outputArray);

    //Close output file
    fclose(fout);

    //Normal execution
    return 0;
}

char* enterFile() {
	//Alocate memory
	char* fileName = (char*)malloc((FILE_NAME_LENGTH + 1) * sizeof(char));

    //If memory was allocated correctly...
	if(fileName != NULL) {

		//Scan file name
		scanf("%s",fileName);

		//In case enter key was pressed, rescan
		while (fileName == NULL) {
	        printf("Please try again.\n");
	        scanf("%s",fileName);
	    }
    }

    //Add extra line
    printf("\n");

    //Return name of file
    return fileName;
}

struct node* createNode() {
	//Create node
	struct node* newNode = (struct node*)malloc(sizeof(struct node));
	//Insufficient memory error
	if (newNode == NULL) {
		printf("There was insufficient memory to read the input file name.\n");
		return NULL;
	}
	//Normal execution
	else {
		//Initialize pointers
		newNode->next = NULL;
		newNode->prev = NULL;
		//Return new node
		return (newNode);
	}
}

struct node* readList(FILE* input) {
	//Set up list
	struct node* head = createNode();
	struct node* front = head;

	//Scan entire file
	while (!feof(input)) {

		//Save raw values into x and t
		fscanf(input,"%l",head->data);

		//Create next node
		head->next = createNode();
		//Add head as prev of next node
		head->next->prev = head;

		//Go to the next node
		head = head->next;
	}
	//Return front of list
	return front;
}


void calculateSpeed(struct node* front) {
	//Helper pointers
	struct node* head = front;
	struct node* prevNode;
	struct node* startNode;
	struct node* endNode;

	//Average counters
	int counter = 0;
	double speedSum = 0.0;

	//Find starting point
	while(head != NULL) {
		//Save previous node
		prevNode = head->prev;

		//First non-0 point
		if(head->data > 0) {
			//Mark starting place
			startNode = head;
		}
		//Navigate the list until the end
		head = head->next;
	}

	//Set head as the last non-NULL node
	head = prevNode;

	//Find end point
	while(head != front) {
		//Last non-0 point
		if(head->data > 0)
			//Mark ending place
			endNode = head;
		//Navigate the list until the beginning
		head = head->prev;
	}

	//Navigate the list from start to end to count the values
	head = startNode;
	while(head != endNode) {
		//Count values
		speedSum += head->data;
		counter++;
		//Navigate list
		head = head->next;
	}

	//Count the end node
	speedSum += endNode->data;
	counter++;

	//Calculate average speed
	avgSpeed = speedSum / (double) counter;
}
