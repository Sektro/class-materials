#include <stdio.h>

int fuggveny()
{
    int x = 20;
    if (x == 20)
        printf("%d\n",x);
    return 0;
}

int main()
{
    fuggveny();  //nem látja x-et
    printf("%d\n",x);
    for (int i = 0; i <3; i++)
        printf("%d\n",i);
    printf("%d\n",i);
    return 0;
}