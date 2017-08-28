// Arup Guha
// 8/18/2013
// Solution to 2013 UCF HS Contest Problem: Squirrels

// Note: using standard in for COP 3502, which is how I want ALL assignments, both
//       individual and pair recitation assignments done.

#include <stdio.h>
#include <math.h>

#define MAXTREES 300
#define PI 3.14159265358979

double dist(int pt1[], int pt2[]);

int main() {

    int numCases, numTrees, loop, i, j;
    scanf("%d", &numCases);

    // Go through each case.
    for (loop=1; loop<=numCases; loop++) {

        scanf("%d", &numTrees);
        int trees[MAXTREES][2];

        // Read in the position of the trees.
        for (i=0; i<numTrees; i++)
            scanf("%d%d", &trees[i][0], &trees[i][1]);

        // This is big enough, based on the spec.
        double distance = 10000;

        // Try each pair and find the shortest distance between any pair of trees.
        for (i=0; i<numTrees; i++)
            for (j=i+1; j<numTrees; j++)
                if (dist(trees[i], trees[j]) < distance)
                    distance = dist(trees[i], trees[j]);

        // Compute the max area of each circle.
        double radius = distance/2;
        double area = PI*radius*radius;

        // Output result.
        printf("Campus #%d:\n", loop);
        printf("Maximum territory area = %.3f\n\n", area);
    }

    return 0;
}

// Returns the distance between pt1 and pt2, in two dimensions.
double dist(int pt1[], int pt2[]) {
    return sqrt(pow(pt1[0]-pt2[0],2)+pow(pt1[1]-pt2[1],2));
}
