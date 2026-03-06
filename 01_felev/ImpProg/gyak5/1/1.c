#include <stdio.h>

int main()
{
    int i = 123;

    printf("Ertek: %d, cim: %p\n", i, &i);

    int* j = &i;

    printf("cim megint: %p\n", j);

    i = 220;

    printf("Ertek(egy cim): %p\n", j);
    printf("A cimen tarolt ertek: %d\n", *j);

    /*
    int* j vagy int *j ugyanaz
    int* i, j vagy int *i,*j NEM ugyanaz
    */

    int arr[] = {10,11,12,13};

    printf("arr: %p\n", arr);
    printf("arr: %d\n", *arr);
    printf("arr[1] == (arr + 1)[0] == *(arr + 1): %d == %d == %d\n", arr[1],(arr + 1)[0],*(arr + 1));

    return 0;
}