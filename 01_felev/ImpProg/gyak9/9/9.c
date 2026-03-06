#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *reverse(char *str)
{
    int len = strlen(str);
    char *str2 = malloc(sizeof(char) * (len+1));
    for (int i = 0; i < len; i++)
    {
        str2[len -i -1] = str[i];
    }
    str2[len] = '\0';
    return str2;
}

//nincs rossz gyerek, csak unatkozó gyerek

int main()
{
    char str[] = "Alma";
    printf("bemenet: %s\n", str);
    char *str2 = reverse(str);
    printf("forditva: %s\n", str2);
    free(str2);
    return 0;
}