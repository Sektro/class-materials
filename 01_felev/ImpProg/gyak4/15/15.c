#include <stdio.h>
#include <stdbool.h>

int main()
{
    char str[100];
    char str2[100];
    scanf("%s",str);
    scanf("%s",str2);
    _Bool megvan = false;
    int i = 0;

    while (megvan == false)
    {
        if (str[i] != str2[i])
        {
            if (str[i] < str2[i])
            {
                printf("Az elso elobb van abc sorrendben.");
                megvan = true;
            }
            else
            {
                printf("A masodik elobb van abc sorrendben.");
                megvan = true;
            }
        }
        else
        {
            i++;
        }
    }
    return 0;
}