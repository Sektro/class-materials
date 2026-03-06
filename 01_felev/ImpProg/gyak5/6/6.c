#include <stdio.h>

int main()
{
    int x = 1;
    int y = 2;
    int *ptr = &x;
    int **ptr1 = &ptr;
    int ***ptr2 = &ptr1;

    printf("%p\n",ptr);
    printf("%p\n",ptr1);
    printf("%p - %d\n",**ptr2,***ptr2);

    **ptr2 = &y;
    printf("%p\n",ptr);
    printf("%p\n",*ptr1);
    printf("%p - %d\n",**ptr2,***ptr2);

    ***ptr2 = 3;
    printf("%d\n",***ptr2);
    return 0;
}