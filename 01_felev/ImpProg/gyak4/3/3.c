#include <stdio.h>

int main()
{
    //3x4-es, 3 sor, mindegyiknek 4 oszlop
    int arr1[3][4] = {
        {0,1,2,3}, // 0.sor
        {4,5,6,7}, //1.sor
        {8,9,10,11} // 2.sor
    };

    int arr2[3][4] = {0,1,2,3,4,5,6,7,8,9,10,11};
    //ugyanaz, mint arr1

    printf("%d\n", arr2[1][3]);

    return 0;
}