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
        case MSc:
            s->extra.kreditindex = ((double)rand() / RAND_MAX) * 5;
            break;
        case PhD:
            s->extra.impakt = ((double)rand() / RAND_MAX) * 5;
            s->erdos = rand() % 30;
            break;
    }
    return s;
}

int main()
{
    srand(time(NULL));
    Student *a = student_init(BSc);
    Student *b = student_init(PhD);

    printf("a:\natlag: %.2f, kor: %d, azonosito: %d, kurzusok: %d\n", a->atlag, a->kor, a->azonosito, a->extra.kurzusok);
    printf("b:\natlag: %.2f, kor: %d, azonosito: %d, impakt: %.f, erdos: %d\n", a->atlag, a->kor, a->azonosito, b->extra.impakt, b->erdos);

    free(a);
    free(b);

    return 0;
}