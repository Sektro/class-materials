#include <stdio.h>

int sum(int* tomb, int hossz)
{
    int i;
    int s = 0;

    for (i = 0; i < hossz; i++)
    {
        s += tomb[i];
        //s += *(tomb + i);
    }

    return s;
}

int main()
{
    int tomb[] = {1,2,3};

    printf("sum tomb = %d\n", sum(tomb, 3));

    return 0;
}