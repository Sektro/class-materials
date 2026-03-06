#include <stdio.h>
#include <stdlib.h>
#include <time.h>
    int b, tipp, hiba = 0;
    int setup();
    void guessing();
    void guess();

    int main()
    {
        setup(b);
        guessing();
        return 0;
    }

    int setup()
    {
        int a;
        srand((unsigned)time(NULL));
        a = rand() % 100;

        printf("Gondoltam egy szamra 0 es 100 kozott\n");
        return a;
    }

    void guessing()
    {
        for ( ; tipp != b; )
        {
            guess();

            if (tipp == b) 
                {
                    printf("Kitalaltad!\n");
                    printf("Hibas tippek szama: %d", hiba);
                }
            else if (tipp < b) 
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

