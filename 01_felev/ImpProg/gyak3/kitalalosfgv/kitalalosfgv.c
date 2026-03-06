#include <stdio.h>
#include <stdlib.h>
#include <time.h>

    int r, tipp, hiba = 0;
    void setup();
    void guessing();
    void guess();

    int main()
    {
        setup();
        guessing();
        return 0;
    }

    void setup()
    {
        srand((unsigned)time(NULL));
        r = rand() % 100;

        printf("Gondoltam egy szamra 0 es 100 kozott\n");
    }

    void guessing()
    {
        for ( ; tipp != r; )
        {
            guess();

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
    }

    void guess()
    {
        printf("Tipp: ");
        scanf("%d",&tipp);
    }

