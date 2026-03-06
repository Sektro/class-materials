#include <stdio.h>
int szovegkeres(char* a,char* b);
int main(int argc, char ** argv) {
    char pl[] = "sor";
    char pl2[] = "d";

    int where = szovegkeres(pl,pl2);
    printf("%d",where);
}
int szovegkeres(char* a,char* b) {
    char* c = a;
    char* d = b;
    int i = 0;
    while (a)
    {
        while (*c == *d && *c != 0) {++c;++d;}
        if (*d == 0) {return i;}
        
        ++a;
        c = a;
        d = b;
        ++i;
    }
    return -1;
}