#include <stdio.h>

int main()
{
    FILE *fp;
    int szamok[] = {2, 45, 6, 3, 1, 4, 34, 123, 43, 32};

    fp = fopen("even_numbers.txt", "a");
    for (int i = 0; i < sizeof(szamok) / sizeof(szamok[0]); i++)
    {
        if (szamok[i] % 2 == 0)
            fprintf(fp, "%d ", szamok[i]);
    }

    fclose(fp);

    return 0;

}