#include <stdio.h>

typedef enum
{
    BSc, Msc, PhD
}Type;

typedef struct
{
    double atlag;  // 8 byte - teljes szo
    int kor;       // 4 byte - megkezd 8 byte-os szot
    int azonosito; // 4 byte - befejez 8 byte-os szot
    Type type;
} Student;

int main()
{
    return 0;
}