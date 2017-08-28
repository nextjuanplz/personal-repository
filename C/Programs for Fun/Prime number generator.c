#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int mod_return(int num, int a)
{
    int res = num % a;
    return(res);
}
int main()
{
    int n, a, upper_limit, res;
    puts("What would you like to be the upper limit?");
    scanf("%d", &upper_limit);
    puts("0 = Not prime\n1 = Not prime\n2 = Prime");

    for (n = 3;n <= upper_limit;n++)
    {
        a = n - 1;
        int x = mod_return(n,a);
        if (x == 0)
        {
            printf("%d = Not prime\n",n);
        }
        else do
            {
            a = a - 1;
            x = mod_return(n, a);
            } while (x > 1);
            break;
        printf("%d = Prime\n",n);
        }
    }
    return(0);
}
