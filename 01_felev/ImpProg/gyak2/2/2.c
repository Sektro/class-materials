#include <stdio.h>

int main()
{
    int x;

    scanf("%d", &x);

    if (x % 2 == 0)
    {
        printf("%d paros\n", x);
    }
    else 
    {
        printf("%d paratlan\n", x);
    }

    

    return 0;
}