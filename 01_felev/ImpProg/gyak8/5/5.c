#include <stdio.h>

int main()
{
    {
        int x = 20;
        {
            int y = 40;
            printf("%d\n",x);
        }
    }
    {
        int z = 60;
    }
    return 0;
}