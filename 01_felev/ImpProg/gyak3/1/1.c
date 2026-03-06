#include <stdio.h>

int main()
{
    int i = 42;
    int j;
    j = (i++ + 10);
    // i is now 43
    // j is now 52 (NOT 53)

    printf("i: %d, j: %d\n", i, j);
    
    j = (++i + 10);
    // i is now 44
    // j is now 54
    
    printf("i: %d, j: %d\n", i, j);

    return 0;
}