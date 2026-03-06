#include <stdio.h>

int main()
{
    double arr1[5] = {1,2,3,4,5};
    double arr2[5] = {1,1,2,3,5};
    double osszeg = 0;
    double atlag = 0;
    if (sizeof(arr1) > sizeof(arr2))
    {
       for (int i = 0; i < sizeof(arr2)/ 8; i++)
       {
         osszeg += arr1[i] * arr2[i];
       }
       atlag = osszeg / (sizeof(arr2) / 8);
    }
    else 
    {
        for (int i = 0; i < sizeof(arr1)/ 8; i++)
        {
            osszeg += arr1[i] * arr2[i];
        }
        atlag = osszeg / (sizeof(arr1) / 8);
    }
    
    printf("osszeg: %f, atlag: %f\n",osszeg, atlag);
    return 0;
}