#include <stdio.h>
#include "8_lib.h"

int main()
{
    float a[] = {1, 0.5, 3};
    float b[] = {0, 2, 1.5};

    printf("Skalaris szorzat: %.2f\n", skalaris_szorzat(a,b,3));

    return 0;
}