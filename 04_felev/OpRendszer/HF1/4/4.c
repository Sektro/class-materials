#include <stdio.h>
void karaktercsere(char* a,char b,char c);
int main(int argc, char ** argv) {
    char pl[] = "sor";
    char pl2 = 's';
    char pl3 = 'r';

    karaktercsere(pl,pl2,pl3);
    printf("%s",pl);
}
void karaktercsere(char* a,char b,char c) {
    char* anchor = a;
    while (a) {
        if (*a == b) {*a = c;}
        ++a;
    }
    a = anchor;
}