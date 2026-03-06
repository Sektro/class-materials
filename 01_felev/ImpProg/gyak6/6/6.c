#include <stdio.h>
#include <stdlib.h>
/*
* n! = n * (n-1)!
* 0! = 1;
*/
long int fakt(int n);

int main(int argc, char *argv[])
{
    printf("%ld\n", fakt(atoi(argv[1])));

    return 0;
}

long int fakt(int n)
{
    if (n >= 1)
        return n * fakt(n - 1);
    else
        return 1;
}