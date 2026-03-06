#include <stdio.h>

int main()
{
    char str[100];
    char str2[100];
    scanf("%s",str);
    scanf("%s",str2);
    int i = 0;
    while (str[i] == str2[i])
    {
        i++;
    }
    if (str[i+1] < str2[i+1] || str[i+1] == '\0')
            {
                printf("Az elso elobb van abc sorrendben.\n");  
            }
    else if (str[i+1] > str2[i+1] || str2[i+1] == '\0')
            {
                printf("A masodik elobb van abc sorrendben.\n");
            }
            else {printf("what");}
    for (int j = 0; j < i+1; j++)
    {
        printf("%c",str[j]);
    }
    printf("\n");
    for (int j = 0; j < i+1; j++)
    {
        printf("%c",str2[j]);
    }
    printf("\n");
    return 0;
}