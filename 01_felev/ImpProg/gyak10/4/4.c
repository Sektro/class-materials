#include <stdio.h>
#define nagyobb(a, b) a < b ? b : a //ha a < b, akkor b, ha nem, akkor a

int main()
{
    printf("%d\n", nagyobb(3,5));
    return 0;
}