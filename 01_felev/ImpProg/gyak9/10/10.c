#include <stdio.h>
#include <stdlib.h>
#define a 5
#define b 100

int main()
{
    char **szavak = malloc(sizeof(char *) * a);
    for (int i = 0; i < a; i++)
    {
        szavak[i] = malloc(sizeof(char) * b);
        printf("%d. szo: ", i+1);
        //fgets(szavak[i], sizeof(szavak[i]),stdin);
        fgets(szavak[i],b-1,stdin);
    }

    for (int i = a - 1; i >= 0; i--)
    {
        printf("%s", szavak[i]);
        free(szavak[i]);
    }
    free(szavak);
    return 0;
}