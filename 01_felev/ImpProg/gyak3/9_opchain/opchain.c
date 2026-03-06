#include <stdio.h>

int main()
{
    //balrol jobbra
    printf("%d\n", 2 < 3); // (2 < 3), 0 vagy 1
    printf("%d\n", 3 < 2); // (3 < 2), 0 vagy 1
    printf("%d\n", 2 < 3 < 4); // ((2 < 3) < 4), 0 vagy 1


    int x;
    int y = 3;
    //jobbrol balra
    printf("%d\n", x = y = 4); // 4 lesz

    return 0;
}