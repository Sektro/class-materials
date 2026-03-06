#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool ugyanaz(char* a, char* b);
int main() {
    // 1
    if (ugyanaz("asd","asd")) {
        printf("true\n");
    }
    else {
        printf("false\n");
    }

    // 2
    if (ugyanaz("asd","asf")) {
        printf("true\n");
    }
    else {
        printf("false\n");
    }
    
    // 3
    char str1[] = "asd";
    char str2[] = "asd";
    if (ugyanaz(str1,str2)) {
        printf("true\n");
    }
    else {
        printf("false\n");
    }

    // 4
    if (ugyanaz(str1,str1)) {
        printf("true\n");
    }
    else {
        printf("false\n");
    }

    // 5
    if (ugyanaz(str1,"asd")) {
        printf("true\n");
    }
    else {
        printf("false\n");
    }
} 

bool ugyanaz(char* a, char* b) {
    return a == b;
}