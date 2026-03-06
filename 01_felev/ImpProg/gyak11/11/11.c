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
    union
    {
        int kurzusok;
        double kreditindex;
        double impakt;
    } extra;
    int erdos;
} Student;

typedef struct
{
    double atlag;
    int kor;
    int azonosito;
    Type type;
    int kurzusok;
    double kreditindex;
    double impakt;
    int erdos;
} Student2;

int main()
{
    printf("%lu, %lu\n", sizeof(Student), sizeof(Student2));

    return 0;
}