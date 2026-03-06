#include <stdio.h>

int *a();

int main()
{
    printf("%p\n",a());

    int *p = a();

    *p = 20;

    //változó fgv lefutása után történik ==> nem lehet rá hivatkozni

    return 0;
}

int *a()
{
    int x = 20;
    return &x;
}