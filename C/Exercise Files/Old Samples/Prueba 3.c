#include<stdio.h>
main()
{
    char trabajo[20];
    int i;
    for (i=1;i<=7;i++)
    {
        if (i==7)
            strcpy(trabajo,"no");
        else
        {
            strcpy(trabajo,"si");
        }
    printf("Caso %d: Mencha %s trabaja hoy.\n",i,trabajo);
    }
    return 0;
}
