#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char* argv[])
{
    for (int i = 0; i < argc; i++) {
        printf("\n%d.: %s", i, argv[i]);
    }
    for (int i = 0; i < argc; i++) {
        printf("\n%d.: %s", argc-i-1, argv[argc-i-1]);
    }
}