#include <stdio.h>
#include <string.h>
#define SIZE 100

int main()
{
    char strs[SIZE][SIZE];

    FILE *fp;
    fp = fopen("1_szavak.txt", "r"); //r = read, olvasással nyitjuk meg a fájlt
    char str[100];
    int len,i;

    for (i = 0; fscanf(fp, "%s", str) == 1; i++)
    {
        printf("Str: %s\n",str);
        strcpy(strs[i], str);
    }

    len = i;


    fclose(fp);

    printf("\n5 karakternel hosszabb: ");
    for (i = 0; i < len; i++)
    {
        if (strlen(strs[i]) > 5)
        {
            printf("%s ", strs[i]);
        }
    }
    printf("\nx karaktert tartalmazza: ");
    for (i = 0; i < len; i++)
    {
        if (strchr(strs[i], 'x'))
        {
            printf("%s ", strs[i]);
        }
    }
    printf("\nalma szot tartalmazza: : ");
    for (i = 0; i < len; i++)
    {
        if (strstr(strs[i],"alma"))
        {
            printf("%s ",strs[i]);
        }
    }
    printf("\ncica szerepel-e a szavak kozott: ");
    for (i = 0; i < len; i++)
    {
        if (strcmp(strs[i], "cica") == 0)
        {
            printf("Igen ");
            break;
        }
    }
    printf("\nszamjegyeket tartalmaz: ");
    for (i = 0; i < len; i++)
    {
        for (int j = 0; strs[i][j] != '\0'; j++)
        {
            if (strs[i][j] >= 48  && strs[i][j] <= 57)
            {
                printf("%s ", strs[i]);
                break;
            }
        }
    }
    printf("\nszamjegyeket tartalmaz strpbrk(): ");
    for (i = 0; i < len; i++)
    {
        if (strpbrk(strs[i], "0123456789") != NULL)
        {
            printf("%s ", strs[i]);
        }
    }
}