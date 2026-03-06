#include <stdio.h>

int main()
{
    int meret, osszeg, max;
    scanf("%d", &meret);
    int arr[meret];

    for (int i = 0; i < sizeof(arr) / 4; i++) //4-el osztunk, mert egy számot 4 bájton tárolunk
    {
        arr[i] = i;
    }
    max = arr[0];
    for (int i = 0; i < meret; i++)
    {
        if (max < arr[i])
        {
            max = arr[i];
        }
    }
    printf("%d\n", max);
    return 0;
}