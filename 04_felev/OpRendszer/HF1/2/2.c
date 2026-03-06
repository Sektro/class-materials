#include <stdio.h>
#include <stdbool.h>

bool egyezik(char* a,char* b);
int main(int argc, char ** argv) {
    char pl[] = "sor";
    char pl2[] = "sor";
    if (egyezik(pl,pl2)) {printf("yipeee");}
    else {printf("bummer :(");}
    
}

bool egyezik(char* a,char* b) {
    while (*a == *b) {
        ++a;++b;
    }
    if (*a == 0 && *b == 0) {return true;}
    return false;
}