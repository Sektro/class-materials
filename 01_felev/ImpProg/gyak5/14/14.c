#include <stdio.h>
#include <string.h>

int strlen_x(char *str);
int strcmp_x(char *str1, char *str2);

int main()
{
    printf("strlen_x: %d\n", strlen_x("aabca"));
    printf("strcmp_x: %d\n\n", strcmp_x("aazc", "aaac"));

    printf("strlen: %ld\n", strlen("aabca"));
    printf("strcmp: %ld\n", strcmp("aazc", "aaac"));

    return 0;
}

int strlen_x(char *str)
{
    int len = 0;

    while(*str != '\0')
    {
        len++;
        str++;
    }

    return len;
}

int strcmp_x(char *str1, char *str2)
{
    while (*str1 == *str2)
    {
        str1++;
        str2++;
    }

    printf("str1: %d, str2: %d\n", *(char *)str1, *(char *)str2);

    return *(char *)str1 - *(char *)str2;
}