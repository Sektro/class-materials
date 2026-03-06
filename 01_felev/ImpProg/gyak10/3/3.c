#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>

int main()
{
    int a = 0;
    int n = 0;

    int *szamok = malloc(sizeof(int));
    for (;;)
    {
        printf("%d. szam: ",n+1);
        scanf(" %d",&a);
        szamok[n] = a;
        if (a < 1)
        {
            break;
        }
        ++n;
        int *new_ptr = realloc(szamok, sizeof(int)*(n+1));
        szamok = new_ptr;
    }

    for (int i = n - 1; i >= 0; --i)
    {
        printf("%d\n", szamok[i]);
    }

    free(szamok);

    return 0;
}