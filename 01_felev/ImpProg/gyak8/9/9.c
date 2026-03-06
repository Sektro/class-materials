#include <stdio.h>

int* nagyobb(int* a, int* b)
{
    if (*a > *b)
    {
        return a;
    }
    else 
    {
        return b;
    }
}

int main()
{
    int x=20;
    int y=10;
    printf("x: %d, y: %d, nagyobbik: %d, %p\n", x, y, *nagyobb(&x,&y), nagyobb(&x,&y));
    return 0;
}