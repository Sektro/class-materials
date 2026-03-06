#include <stdio.h>

int main()
{
    //ez elzarja a külvilágtól
    {
        int i = 2;
    }
    int i = 1;
    printf("%d\n",i);//nem fordul le
    return 0;
}