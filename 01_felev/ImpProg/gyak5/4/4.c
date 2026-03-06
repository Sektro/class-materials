#include <stdio.h>

void fv(int*b)
{
    *b = 77;
}

int main()
{
    int a  = 0;
    int *p = &a;

    //*&a = 10
    *p = 10;

    printf("%d\n",a);

    fv(p);

    printf("%d\n",a);
    return 0;
}