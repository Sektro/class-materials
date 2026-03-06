#include <stdio.h>

int main()
{
    int meret, osszeg;
    scanf("%d", &meret);
    int arr[meret];

    for (int i = 0; i < sizeof(arr) / 4; i++) //4-el osztunk, mert egy számot 4 bájton tárolunk
    {
        arr[i] = i;
    }
    for (int i = 0; i < meret; i++)
    {
        osszeg += arr[i];
    }
    printf("%d\n", osszeg);
    return 0;
}