#include <stdio.h>
#include "my_utils.h"

int main()
{
    int x = 20;
    int y = 10;
    printf("x: %d, y: %d\n",x,y);
    swap(&x,&y);
    printf("x: %d, y: %d\n",x,y);
    printf("x: %d, y: %d, nagyobbik: %d, %p\n", x, y, *nagyobb(&x,&y), nagyobb(&x,&y));
    printf("zsazse: %d, %d", zsa(), zse());
    return 0;
    //fordítás: gcc main.c my_utils.c
}