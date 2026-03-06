#include <stdio.h>

typedef struct
    {
        double atlag;  // 8 byte - teljes szo
        int kor;       // 4 byte - megkezd 8 byte-os szot
        int azonosito; // 4 byte - befejez 8 byte-os szot
    } Student;

int legjobb_diak(Student diakok[], int meret)
{
    Student max = diakok[0];
    for (int i = 1; i < meret; ++i)
    {
        if (max.atlag < diakok[i].atlag)
        {
            max = diakok[i];
        }
    }

    return max.azonosito;
}

int main()
{
    Student diakok[] = {{3.5, 19, 234532},{4.2,18,234634},{4.0,20,125436}};
    printf("A legmagasabb atlaggal rendelkezo diak azonositoja: %d\n", legjobb_diak(diakok, 3));
}