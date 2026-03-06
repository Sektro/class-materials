#include <stdio.h>

int main()
{
    int x;
    int forditott = 0;
    int maradek;

    scanf("%d", &x);
    printf("%d ",x);
    while (x != 0)
    {
        maradek = x % 10;
        forditott = forditott * 10 + maradek;
        x /= 10;
    }
    printf("forditottja = %d\n", forditott);
    return 0;
}