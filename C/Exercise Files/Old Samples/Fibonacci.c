#include <stdio.h>
main()
{
    int i;
    for (i=1;i<=10;i++)
    {
        int b;
        {
            b=i*i;
            printf("Case %d: The value is %d\n",i,b);
        }
    }
return 0;
}
