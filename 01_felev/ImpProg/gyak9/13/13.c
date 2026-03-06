#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define b 100

int main()
{
    for (;;)
    {
        char **szavak = malloc(sizeof(char *) * b);
        int x = 0;
        for (int i = 0;;i++)
        {
            szavak[i] = malloc(sizeof(char) * b);
            printf("%d. szo: ", i + 1);

            if (scanf(" %s",szavak[i]) == EOF)
            {
                x = i;
                free(szavak[i]);
                break;
            }
        }

        
        printf("\nszavak forditva\n");

        for (int i = x - 1; i >= 0; i--)
        {
            printf("%s\n", szavak[i]);
            free(szavak[i]);
        }

        free(szavak);
    }

    return 0;

}