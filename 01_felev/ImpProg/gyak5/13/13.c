#include <stdio.h>

int main()
{
    int a, b;
    printf("a: ");
    scanf("%d",&a);
    printf("b: ");
    scanf("%d",&b);
    int arr[3] = {1,2,3};
    melyik(&arr[a],&arr[b]);
}

void melyik(int* a, int* b)
{
    if (a < b) {printf("a kisebb indexure mutat");}
    else if (a > b) {printf("b kisebb indexure mutat");}
    else {printf("ugyanolyan indexure mutatnak");}
}