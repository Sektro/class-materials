#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverse(char *str)
{
    char c;
    int len = strlen(str);
    for (int i = 0; i < len / 2; i++)
    {
        c = str[i];
        str[i] = str[len - i -1];
        str[len - i - 1] = c;
    }
}

int main()
{
    char str[] = "Alma";
    printf("bemenet: %s\n",str);
    reverse(str);
    printf("forditva: %s\n",str);
    return 0;
}