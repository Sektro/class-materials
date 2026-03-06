#include <stdio.h>

int main()
{
    int var;
    int *ptr;
    int **pptr;

    var = 3000;

    //var cime
    ptr = &var;

    //ptr cime
    pptr = &ptr;

    //ertek
    printf("var = %d\n",var);
    printf("*ptr = %d\n",*ptr);
    printf("**pptr = %d\n",**pptr);

    return 0;
}