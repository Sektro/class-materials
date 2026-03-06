#include <stdio.h>

int main()
{
    int x;

    scanf("%d", &x);

    if (x > 0)
    {
        printf("%d pozitiv", x);
    }
    else if (x < 0)
    {
        printf("%d negativ", x);
    }
    else
    {
        printf("%d nulla", x);
    }

    return 0;
}