#include <stdio.h>

int main()
{
    typedef struct
    {
        double atlag;  // 8 byte - teljes szo
        int kor;       // 4 byte - megkezd 8 byte-os szot
        int azonosito; // 4 byte - befejez 8 byte-os szot
    } Student1;

    typedef struct
    {
        double atlag;  // 8 byte - teljes szo
        int kor;       // 4 byte - megkezd 8 byte-os szot
        int azonosito; // 4 byte - befejez 8 byte-os szot
        int valami;
    } Student2;

    Student1 s;
    Student2 y;

    return 0;
}