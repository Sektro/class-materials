#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char* argv[])
{
    int sum = 0;
    for (int i = 0; argv[i] != NULL; i++) {
        sum += strlen(argv[i]);
    }
    printf("hossz: %d",sum);
}