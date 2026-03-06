#include <stdio.h>

int main()
{
    int meret, osszeg, max, max2;
    scanf("%d", &meret);
    int arr[meret];

    for (int i = 0; i < sizeof(arr) / 4; i++) //4-el osztunk, mert egy számot 4 bájton tárolunk
    {
        arr[i] = i;
    }
    
    if (arr[0] < arr[1])
    {
        max = arr[1];
        max2 = arr[0];
    }
    else
    {
        max = arr[0];
        max2 = arr[1];
    }
    for (int i = 0; i < meret; i++)
    {
        if (max < arr[i])
        {
            max2 = max;
            max = arr[i];
        }
    }

    printf("elso: %d , masodik: %d\n", max, max2);
    return 0;
}