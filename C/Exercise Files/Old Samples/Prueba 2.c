#include <stdio.h>
int main()
{
    char dia[20];
    int i;
    for(i=1;i<8;i++)
    {
        if (i==1)
            strcpy(dia,"lunes");
        else
        {
            if (i==2)
                strcpy(dia,"martes");
            else
                {
                    if (i==3)
                        strcpy(dia,"miercoles");
                        else
                    {
                        if (i==4)
                            strcpy(dia,"jueves");
                        else
                        {
                            if (i==5)
                                 strcpy(dia,"viernes");
                            else
                            {
                                 if (i==6)
                                     strcpy(dia,"sabado");
                                 else
                                 {
                                     if
                                        (i==7)
                                         strcpy(dia,"domingo");
                                 }
                            }
                        }
                    }
                }
            }
        printf("Caso %d. Hoy es %s.\n",i,dia);
        }
    return 0;
}
