#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char* argv[])
{
    if (argc > 2) {
        printf("\nvan legalább 2 paramétere");
        for (int i = 1; argv[i] != NULL; i++) {
            if (!strcmp(argv[1],argv[i])) {
                printf("\nnem ugyanazok");
                if (strcmp(argv[1],argv[2])) {printf("lol");}
                printf(argv[1]);
                printf(argv[2]);
                return 0;
            }
        }
        printf("\nugyanazok");
    }
    else {
        printf("\nnincs legalább 2 paramétere");
    }
}