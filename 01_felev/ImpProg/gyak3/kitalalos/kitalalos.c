#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int r, tipp;
    int hiba = 0;
    srand((unsigned)time(NULL));
    r = rand() % 100;

    printf("Gondoltam egy szamra 0 es 100 kozott\n");

    for (;tipp != r;)
    {
        printf("Tipp: ");
        scanf("%d",&tipp);

        char *str = (tipp > r) ? "Kisebbre gondoltam" : (tipp < r) ? "Nagyobbra gondoltam" : "Eltalaltad";
        printf("%s\n", str);
    }

    /*
    for ( ; tipp != r; )
    {
        printf("Tipp: ");
        scanf("%d",&tipp);
        if (tipp == r) 
            {
                printf("Kitalaltad!\n");
                printf("Hibas tippek szama: %d", hiba);
            }
        else if (tipp < r) 
            {
                printf("A tipped kisebb, mint a szam.\n");
                hiba++;
            }
        else 
            {
                printf("A tipped nagyobb, mint a szam.\n");
                hiba++;
            }
    }
    */

    /*
    for ( ; ; )
    {
        printf("Tipp: ");
        scanf("%d",&tipp);
        if (tipp == r) 
            {
                printf("Kitalaltad!\n");
                break;
            }
        else if (tipp < r) 
            {
                printf("A tipped kisebb, mint a szam.\n");
            }
        else 
            {
                printf("A tipped nagyobb, mint a szam.\n");
            }
    }
    */

    return 0;   
}

