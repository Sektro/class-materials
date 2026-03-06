#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char* argv[])
{
    for (int i = 0; argv[i] != NULL; i++) {
        printf("\n%d.: %s", i, argv[i]);
    }
}