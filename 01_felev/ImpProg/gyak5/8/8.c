#include <stdio.h>

int sum(int* eleje, int* vege)
{
    int i;
    int s = 0;
    int hossz = vege - eleje + 1;
    printf("%d\n", hossz);

    for (i = 0; i < hossz; i++)
    {
        s += eleje[i];
        //s += *(eleje + i);
    }

    return s;
}

int main()
{
    int tomb[] = {1,2,3};

    printf("sum tomb = %d\n", sum(tomb, tomb + 2));

    return 0;
}