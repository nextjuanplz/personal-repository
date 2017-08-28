#include <stdio.h>
#include <stdlib.h>

//Constants
#define FILE_NAME_LENGTH 40									//Length of file name
#define ERROR -1											//Error code{0 = OK, -1 = DMA, -2 = fin, -3 = fout}
#define CONS_CUTOFF 3										//Number of consecutive 1s and 0s that count as braking and driving
#define HEADER_FLAG 1                                       //Designates if a header is output
#define OUTPUT_TYPE 0                                       //Designates use {0 = Output/Test, 1 = Data}

//Files
char* inputfileName;										//Input file
char* outputfileName;										//Output file

//Output array node
typedef struct node {
    int brakeCount;
    double avgBrakeDur;
}node;

node* outputArray;

//Output variables
int brakes;													//Number of brakes
double avg_brake_dur;										//Average brake duration

//Functions
char* enterFile();											//File name entry
FILE* readFile(char*);										//Reads file
void countBrakes(FILE*);									//Counts number of brakes and calculates average brake duration

int main() {
	//Counters for files to be processed
	int file_count = 0;										//File counter
	int files;												//Number of files to be processed

	//Header
	char header[] = "|\tFile  \t# of brakes\tAverage brake duration (s.)  |\n--------------------------------------------------------------\n";

	//Opening prompt
	printf("How many data files?\n");
	scanf("%d",&files);
	printf("\n");

	//Check for invalid number of participants
	if (isdigit(files)) printf("Please try again.\n");
	while(isdigit(files))
        scanf("%d",&files);

    //Output file name entry
    printf("Please enter the OUTPUT file name.\n(Be careful, as this will overwrite an existing file!)\n");
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
    outputArray = (node*)malloc(files * sizeof(node));

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

		//Start computations
		countBrakes(fin);

		/*Old file output
		if (OUTPUT_TYPE) fprintf(fout, "%-d\t%-d\t%-f\n\n",file_count + 1,brakes,avg_brake_dur);
        else fprintf(fout, "\t  %-d\t     %-d\t\t\t   %-.3f\n\n",file_count + 1,brakes,avg_brake_dur);
        */

        //File output
        if (brakes == 0) {
            if (OUTPUT_TYPE) fprintf(fout, "%-s\t%-d\t%-s\n\n",inputfileName,brakes,"N/A");
            else fprintf(fout, "\t%-s\t     %-d\t\t\t   %-s\n\n",inputfileName,brakes,"N/A");

        }

        else {
            if (OUTPUT_TYPE) fprintf(fout, "%-s\t%-d\t%-f\n\n",inputfileName,brakes,avg_brake_dur);
            else fprintf(fout, "\t%-s\t     %-d\t\t\t   %-.3f\n\n",inputfileName,brakes,avg_brake_dur);
        }

		//Array output
        outputArray[file_count].brakeCount = brakes;
		outputArray[file_count].avgBrakeDur = avg_brake_dur;

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
    for (file_count = 0; file_count < files; file_count++) {
        if (outputArray[file_count].brakeCount == 0)
            printf("\t  %-d\t     %-d\t\t\t   %-.s\n\n",file_count + 1,outputArray[file_count].brakeCount,"N/A");
        else
            printf("\t  %-d\t     %-d\t\t\t   %-.3f\n\n",file_count + 1,outputArray[file_count].brakeCount,outputArray[file_count].avgBrakeDur);
    }

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

void countBrakes(FILE* input) {
	//Braking variables
	brakes = 0;										//Counter for brake instances
	int x;											//Storage for driving status values

	//Time variables
	avg_brake_dur = 0.0;							//Average brake duration
	float t = 0.0;									//Storage for brake time
	double init_t = 0.0;							//Storage for onset of braking
	double prev_t = 0.0;							//Previous time
	double save_t = 0.0;							//Previous time saved
	double t_diff = 0.0;				    		//Span of a single brake
	double final_t = 0.0;							//Storage for end of braking
	double sumBrake = 0.0;						    //Sum of brake times

	//Flags
	int vehicle_start = 0;							//Flag for vehicle start
	int status;										//Flag for driving status {0 = driving, 1 = braking}
	int save_state;									//Previous status
	int state_change = 0;							//Marks whether status is different from previous one
	int brakePress_count = 0;						//Counter for consecutive brake presses
	int braking = 0;								//Flag for consecutive braking
	int drive_count = 0;							//Counter for consecutive driving
	int brake_start = 0;                            //Marks if braking has begun
	int brake_start_prev = 0;                       //Saves previous value of brake_start

	//Scan entire file
	while (!feof(input)) {

		//Save raw values into x and t
		fscanf(input,"%f\t%d",&t,&x);

		//Scan file until vehicle has started moving
		if(!vehicle_start && x == 0) {
			//Set as started
			vehicle_start = 1;
			//Initialize status flag
			status = 0;
		}

		//Record only once vehicle has started moving
		if(vehicle_start) {

			//Save previous status
			save_state = status;

			//Update current status and instances of driving
			//Braking
			if(x == 1) {
                status = 1;
                brakePress_count++;
			}

			//Not braking
			if(x == 0) {
                status = 0;
                drive_count++;
			}

			//Check if status changed from previous iteration
			if(status != save_state) state_change = 1;
			else state_change = 0;

			//If previously driving and now braking...
			if(state_change && status)
				//Mark initial time of braking
				init_t = t;

            //If previously braking and now driving...
			if(state_change && !status)
				save_t = prev_t;

            //Save save_t in case it gets overwritten
			final_t = save_t;

			//If 3 consecutive brake presses...
			if(brakePress_count >= CONS_CUTOFF) {
                //Reset driving counts
                if(x == 1) drive_count = 0;
                //Set as braking
                braking = 1;
            }

            //If 3 consecutive driving instances...
            if(drive_count >= CONS_CUTOFF) {
                //Reset braking counts
                if(x == 0) brakePress_count = 0;

                //If first consecutive sequence of 3 driving instances...
                if(braking) {
                    //Calculate a single brake duration
                    t_diff = (final_t - init_t);

                    //Add brake duration from previous brake only when really driving again
                    sumBrake += t_diff;
                }

                //Set as driving
                braking = 0;
            }

			//Save previous brake_start status
			brake_start_prev = brake_start;

			//Consecutive braking or driving
			if(braking)
                brake_start = 1;
            if(!braking)
                brake_start = 0;

			//Save previous time
			prev_t = t;

			//Count brakes
            if(brake_start != brake_start_prev)
                if(brake_start) brakes++;
		}
	}

	//Do not count parking at the end as a brake
	if(status)
		//Decrement brakes counter
		brakes--;

	//Calculate average brake durations
	avg_brake_dur = sumBrake / (double) brakes;
}
