#include <stdio.h>
void levag(char* a,int b);
int main(int argc, char ** argv) {
    char pl[] = "sor";
    int pl2 = 2;
    levag(pl,pl2);
    printf("%s",pl);
}
void levag(char* a,int b) {
    while (b--) {++a;}
    printf("%s",a);
}