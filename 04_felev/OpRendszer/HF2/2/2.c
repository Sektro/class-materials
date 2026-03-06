#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main() {
    static char s1[] = "Hajra ";
    static char* s2;
    static char* s3;
    s2 = "Fradi!";
    s3 = s1;
    strcat(s1,s2);
    strcat(s1,s3);
    printf("s1: %s\n", s1);
    printf("s2: %s\n", s2);
    printf("s3: %s\n", s3);
}