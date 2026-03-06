#include <stdio.h>

void logolas (int lvl, char *str);

int main()
{
    logolas(0, "Pisti elment a boltba");
    logolas(3, "A bolt nyitva volt");
    logolas(1, "Nem volt tej");
    logolas(2, "Pisti a boltban ragadt");
    return 0;
}

void logolas(int lvl, char *str)
{
    switch (lvl)
    {
    case 0:
        printf("INFO - %s\n", str);
        break;
    case 1:
        printf("WARNING - %s\n", str);
        break;
    case 2:
        printf("ERROR - %s\n", str);
        break;
    default:
        printf("LOG - %s\n", str);
        break;
    }
}