#include <stdio.h>

int main()
{
    struct Student1
    {
        int kor;        // 4 byte - megkezd: 8 byte-os uj szot - 4 byte szabad
        double atlag;   // 8 byte - teljes szo
        int azonosito;  // 4 byte - megkezd: 8 byte-os uj szot - 4 byte szabad
    };
    struct Student2
    {
        double atlag;   // 8 byte - teljes szo
        int kor;        // 4 byte - megkezd: 8 byte-os uj szot - 4 byte szabad
        int azonosito;  // 4 byte - befejezi 8 byte-os szot
    };

    printf("1: %lu, 2: %lu\n", sizeof(struct Student1), sizeof(struct Student2));
    //tagok méret szerinti csökkenése vezet a legoptimálisabb memóriahasználathoz

    return 0;
}
