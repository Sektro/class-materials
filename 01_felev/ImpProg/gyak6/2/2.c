#include <stdio.h>

int main(int argc, char *argv[])
{

    if (argc == 2)
    {
        printf("A megadott argumentumok: %s\n", argv[1]);
    }
    else if (argc > 2)
    {
        printf("Tul sok argumentum.\n");
    }
    else
    {
        printf("Egy argumentum kell.\n");
    }

    return 0;
}