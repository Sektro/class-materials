#include<stdio.h>
int main(void)
{
    // int arr[3][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    // printf("%d - %d",(int) *arr == (int)arr, *(*(arr) + 1));
    // a[3][2] = *(*(a + 3) + 2)

    int a[] = {1, 2};
    int b = 69696;
    //printf("%d", a[2]);
    call();
    return 0;
}

/* int[3][2] => 
    int[2] -> int[0][1]
    int[2]
    int[2]

    a[n] = *(a + n)[2]

    temp[3]


*/

void call()
{
    int c;
    printf("%d", *(&c - 2));
}