#include <stdio.h>
main()
{
    int i;
    int b;
    int c;
    int d;
       printf("Case %d: The value is %d.\n",1,1);
       printf("Case %d: The value is %d.\n",2,1);
       b=1;
       c=1;
    for (i=3;i<=46;i++)
    {
        d=b+c;
        printf("Case %d: The value is %d.\n",i,d);
        b=c;
        c=d;
    }
return 0;
}
