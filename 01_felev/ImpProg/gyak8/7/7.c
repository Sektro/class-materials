#include <stdio.h>

int fgv()
{
    static int x = 20;
    x++;
    printf("%d\n",x);
    return 0;
}

int main()
{
    fgv();
    fgv();
    fgv();

    return 0;
}
