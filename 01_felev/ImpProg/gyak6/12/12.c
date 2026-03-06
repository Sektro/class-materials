#include <stdio.h>

int main(int argc, char *argv[])
{
    FILE *fp;
    int osszeg = 0;
    int x;

    fp = fopen("even_numbers.txt", "r");
    //fp = fopen(argv[1], "r");
    while (fscanf(fp, "%d", &x) == 1)
    {
        osszeg += x;
    }
    fclose(fp);

    printf("Osszeg: %d\n", osszeg);

    return 0;
}