#include <stdio.h>
int hossz(char* a);
int main(int argc, char ** argv) {
    char pl[] = "sor";
    printf("%s: %d",pl,hossz(pl));
}

int hossz(char* a) {
    char* b = a;
    while (*a++);
    return --a-b;
}