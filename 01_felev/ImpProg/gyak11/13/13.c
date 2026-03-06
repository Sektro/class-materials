#include <stdio.h>
#include <stdlib.h>

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

Student *student_init(Type type)
{
    Student *s = malloc(sizeof(Student));
    s->type = type;   //itt nem pontot írunk mert allokálunk
    s->atlag = ((double)rand() / RAND_MAX) * 5;
    s->azonosito = rand() % 1000000;
    s->kor = rand() % 100;
    switch (type)
    {
        case BSc:
            s->extra.kurzusok = rand() % 70;
            break;
        case Msc:
            s->extra.kreditindex = ((double)rand() / RAND_MAX) * 5;
            break;
        case PhD:
            s->extra.impakt = ((double)rand() / RAND_MAX) * 5;
            s->erdos = rand() % 30;
            break;
    }
    return s;
}

Student *legjobb_diak(Student** diakok, int meret)
{
    double max_atlag = diakok[0]->atlag;
    int maxind = 0;

    for (int i = 1; i < meret; ++i)
    {
        printf("%f\t%f\n", max_atlag, diakok[i]->atlag);
        if (max_atlag < diakok[i]->atlag)
        {
            max_atlag = diakok[i]->atlag;
            maxind = i;
        }
    }
    printf("maxind: %d\n", maxind);

    Student *legjobb_atlagu_diak = diakok[maxind];

    return legjobb_atlagu_diak;
}

int main()
{
    srand(time(NULL));
    Student *diakok[10];
    for (int i = 0; i < 10; ++i)
    {
        diakok[i] = student_init(rand() % 3 + 1);
        printf("diak[%d]: azonosito: %d, atlag: %f\n", i, diakok[i]->azonosito, diakok[i]->atlag);
    }

    printf("A legmagasabb atlaggal rendelkezo diak azonositoja: %d\n", legjobb_diak(diakok, 10)->azonosito);

    for (int i = 0; i < 10; ++i)
    {
        free(diakok[i]);
    }

    return 0;
}