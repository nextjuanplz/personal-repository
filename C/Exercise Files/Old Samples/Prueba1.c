#include <stdio.h>
int main()
{
    char mineral[10];
    int i;
    for (i=1; i<=6; i++) {
        if (i==5) {
                strcpy(mineral,"potasio");}
        else {
            strcpy(mineral, "nitrogeno");
        }
        printf("%d - Los bananos son altos en %s.\n", i, mineral);
        }
    return(0);
}
