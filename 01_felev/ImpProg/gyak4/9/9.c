#include<stdio.h>
int main()
{
    int a, b, c;	
    int arr[5] = {1, 2, 3, 25, 7};
    a = ++arr[1]; //arr[1] + 1, utána értékadás tehát itt a = 3
    b = arr[1]++; //b=3, mivel +1 csak értékadás után
    c = arr[a++]; //itt a=3, c=arr[3], c==25, utána a +1, 
    printf("%d : %d :%d", a, b, c);
    return 0;
}