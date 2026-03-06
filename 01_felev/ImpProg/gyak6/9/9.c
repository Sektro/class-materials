#include <stdio.h>

int main()
{
    FILE *fp;

    fp = fopen("player.txt", "w");
    fputs("Kovats Bela\n", fp);
    fclose(fp);

    return 0; 
}