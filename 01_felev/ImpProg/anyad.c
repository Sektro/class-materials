#include <stdio.h>
void fv(int* b);

int main()
{
    int i = 12;
    int* j = &i;
    int** ptr = &j;
    int*** ptr1 = &ptr;

    printf("%d\n", i);

    *j = 13;
    printf("%d\n",i);
    printf("%d\n",*j);
    printf("%d\n",**ptr);

    fv(j);
    printf("%d\n",*j);

    

    return 0;
}

void fv(int *b)
{
    *b = 123;
}