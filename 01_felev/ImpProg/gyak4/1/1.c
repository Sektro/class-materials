#include <stdio.h>

int invert(int i);

int main()
{
    int x = 4;
    printf("%d\n", invert(x));

    unsigned int y = 2147483650; //3mal túlcsordul
    printf("%d\n", invert(y));

    return 0;
}

int invert(int i)
{
    printf("%d\n",i); 
    return i * -1;
}