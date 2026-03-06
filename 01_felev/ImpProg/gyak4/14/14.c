#include <stdio.h>

int main()
{
    int hossz = 0;
    char str[100];
    scanf("%s",str);
    // scanf("%[^\n]s", str);
    // scanf("%[^;]s", str);  //<- addig olvassa, amíg egy ilyet nem lát

    for (int i = 0; i < 100; i++)
    {
        if (str[i] == '\0')
        {
            hossz = i;
            break;
        }
    }

    int i = 0;
    while (str[i] != '\0')
    {
        i++;
    }


    printf("while hossz: %d\n", i);
    printf("for hossz: %d\n", hossz);

    return 0;
}