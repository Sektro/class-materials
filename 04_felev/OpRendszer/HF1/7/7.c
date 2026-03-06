#include <stdio.h>
void ertekadas(char* a,char* b);
int main(int argc, char ** argv) {
    char pl[] = new char[];
    char pl2[] = "yipee";

    ertekadas(pl,pl2);
    printf("%s",pl);
}
void ertekadas(char* a,char* b) {
    char* c = a;
    while (b) {
        *c = *b;
        ++b;
        ++c;
    }
    *c = *b;
    /*
    while (a != c)
    {
        --a;
    }
    */
}