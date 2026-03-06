#include <stdio.h>

int main()
{
    int a = 12;
    int b = 24;

    csere(&a, &b);
    printf("%d %d\n",a,b);

    int tomb[2] = {a,b};
    csere(&tomb[0],&tomb[1]);
    printf("%d %d\n",tomb[0],tomb[1]);

    return 0;
}

void csere(int *x, int *y)
{
    int c = *x;
    *x = *y;
    *y = c;
}