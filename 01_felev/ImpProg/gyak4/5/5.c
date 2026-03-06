#include <stdio.h>

int main()
{
    int meret;
    scanf("%d", &meret);
    int arr[meret];

    for (int i = 0; i < meret; i++)
    {
        arr[i] = 0;
    }

    for (int i = 0; i < meret; i++)
    {
        printf("%d\n",arr[i]);
    }
    return 0;
}