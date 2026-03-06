#include <stdio.h>

int main()
{
    int a;
    // scanf("%d",a); ez nem jó, mert a szam kezdőértékét kezeli memóriacímként
    scanf("%d",&a); // ez a szam memóriacímére ír
    return 0;
}