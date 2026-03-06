#include  <stdio.h>

int osszeg(int x, int y)
{
    return x + y;
}

int szorzat(int x, int y)
{
    return x*y;
}

int vektor_muvelet(int vektor[], int nulla, int (*fv)(int, int), int meret)
{
    int eredmeny = nulla;
    for (int i = 0; i < meret; ++i)
    {
        eredmeny = fv(eredmeny,vektor[i]);
    }

    return eredmeny;
}

int main()
{
    int vektor[3] = {2,3,4};
    printf("osszeg: %d\n", vektor_muvelet(vektor, 0, osszeg, 3));

    printf("szorzat: %d\n", vektor_muvelet(vektor, 1, szorzat, 3));

    return 0;
}