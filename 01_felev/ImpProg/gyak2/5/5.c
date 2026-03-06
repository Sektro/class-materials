#include <stdio.h>
#include <limits.h>

int main()
{
    int intmeret = sizeof(int);
    int intbitben = intmeret * 8;
    int min = INT_MAX + 1;
    printf("int merete: %d\nint bitekben: %d\nlegnagyobb egesz szam: %d\nlegkisebb egesz szam (overflow): %d\nlegkisebb egesz szam: %d\n", intmeret, intbitben, INT_MAX, min, INT_MIN);
    return 0;
}