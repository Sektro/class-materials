#include <stdio.h>

int main()
{
    int arr1[5] = {1,2,3,4,5};
    int arr2[5] = {1,1,2,3,5};
    int osszeg = 0;
    if (sizeof(arr1) > sizeof(arr2))
    {
       for (int i = 0; i < sizeof(arr2)/ 4; i++)
       {
         osszeg += arr1[i] * arr2[i];
       }
        
    }
    else 
    {
        for (int i = 0; i < sizeof(arr1)/ 4; i++)
        {
            osszeg += arr1[i] * arr2[i];
        }
    }
    
    printf("%d\n",osszeg);
    return 0;
}