#include <stdio.h>

int main()
{
    int *ptr = NULL;

    if (!ptr) //ha NULL-al egyenlő
    {
        printf("ptr erteke: %p\n", ptr);
        printf("ptr erteke: %d\n", *ptr); //
    }
    return 0;
}