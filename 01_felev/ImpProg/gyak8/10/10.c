#include <stdio.h>

int main()
{
    void swap (int *a, int *b)
    {
        int temp = *a;
        *a = *b;
        *b = temp;
    }

    //így el van rejtve a többi fájl elől

    int x = 3;
    int y = 4;
    printf("x: %d, y: %d\n",x,y);
    swap(&x,&y);
    printf("x: %d, y: %d\n",x,y);
}