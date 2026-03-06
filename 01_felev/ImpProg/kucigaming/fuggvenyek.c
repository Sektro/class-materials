#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "beadando.h"

void initialize()
{
    printf("\n-----------------------------------------------------\n");
    printf("<3<3<3<3<3<3<3 Spiral Matrix Generator <3<3<3<3<3<3<3\n");
    printf("-----------------------------------------------------\n\n");
    printf("0 : Felhasznaloi kezikonyv\n");
    printf("1 : Matrix generalas\n");
    printf("2 : Matrix megjelenitese a terminalon\n");
    printf("3 : Matrix fajlba mentese\n");
    printf("4 : Matrix betoltese fajlbol\n");
    printf("5 : Kilepes\n");
}

void kezikonyv()
{
    printf("\n********** ^_^ FELHASZNALOI KEZIKONYV ^_^ ***********\n\n");
    printf("Ez a program NxN-es matrixokat kezel, melyben az egymast koveto egesz szamok spiralszeruen helyezkednek el a matrix kozepebol az 1-es ertektol kiindulva.\n\n");
    printf("A 'Valasztasom ->' lehetosegnel meg tudja adni, melyik funkciojat szeretne hasznalni a programnak. Mit csinalnak ezek?\n\n");
    printf("0 => Program mukodesenek reszletes leirasa.\n---------------------\n");
    printf("1 => Egy uj matrixot general le. 3 parametert ker be: a matrix dimenziojat (1-20), az indulasi- (b, f, j, l) es a forgasi iranyt (cw, ccw).\n---------------------\n");
    printf("2 => Az aktualis NxN-es matrixot kirajzolja a terminalra.\n---------------------\n");
    printf("3 => Az aktualis matrixot kiirja es lementi egy .txt allomanyba.\n---------------------\n");
    printf("4 => A megadott fajlbol toltodik be az aktualis matrix, amely felulirja az elozot.\n---------------------\n");
    printf("5 => Kilep a programbol. Az 5-os szam megadasaig minden funkcio elvegzese utan visszaterunk a menube.\n---------------------\n");
}

int** generalas(int dimenzio, char forgas, char* indulas)
{
    //helyfoglalas a matrixnak
    int** vegleges = (int**)malloc(sizeof(int*) * dimenzio);
    for (int i = 0; i < dimenzio; i++) 
    {
        vegleges[i] = (int*)malloc(sizeof(int) * dimenzio);
    }
    //matrix inicializalasa
    for (int i = 0; i < dimenzio; i++) 
    {
        for (int j = 0; j < dimenzio; j++) 
        {
            vegleges[i][j] = 0;
        }
    }
    //indexek - amikkel dolgozni fogunk - memoriaterulet fogalalasa
    int* fixindex1 = malloc(sizeof(int)); 
    int* fixindex2 = malloc(sizeof(int));
    //matrix kozepenek lefixalasa attol fuggoen, hogy a dimenzio paros vagy paratlan
    if (dimenzio % 2 == 0)
    {
        if (forgas == 'b')
        {
            *fixindex2 = dimenzio / 2;
            if (strcmp(indulas, "cw") == 0)
            {
                *fixindex1 = dimenzio / 2;
            }
            else if (strcmp(indulas, "ccw") == 0)
            {
                *fixindex1 = (dimenzio / 2) - 1;
            }
        }      
        else if (forgas == 'f')
        {
            *fixindex1 = dimenzio / 2;
            if (strcmp(indulas, "cw") == 0)
            {
                *fixindex2 = (dimenzio / 2) - 1;
            }
            else if (strcmp(indulas, "ccw") == 0)
            {
                *fixindex2 = dimenzio / 2;
            }
        }
        else if (forgas == 'j')
        {
            *fixindex2 = (dimenzio / 2) - 1;
            if (strcmp(indulas, "cw") == 0)
            {
                *fixindex1 = (dimenzio / 2) - 1;
            }
            else if (strcmp(indulas, "ccw") == 0)
            {
                *fixindex1 = dimenzio / 2;
            }
        }
        else if (forgas == 'l')
        {
            *fixindex1 = (dimenzio / 2) - 1;
            if (strcmp(indulas, "cw") == 0)
            {
                *fixindex2 = dimenzio / 2;
            }
            else if (strcmp(indulas, "ccw") == 0)
            {
                *fixindex2 = (dimenzio / 2) - 1;
            }
        }
    }
    else
    { 
        *fixindex1 = dimenzio / 2;
        *fixindex2 = dimenzio / 2;
    }
    vegleges[*fixindex1][*fixindex2] = 1;
    //segedvaltozok
    int* szam = malloc(sizeof(int));
    *szam = 1;
    int* temp = malloc(sizeof(int));
    *temp = 0;
    //spiralminta letrehozasa
    for (int a = 1; a <= dimenzio - 1; a++)
    {
        int b = 1;
        int c;
        while (b <= 2)
        {
            c = a;
            switch (forgas)
            {
                case 'b':
                    while (c != 0)
                    {
                        vegleges[*fixindex1][--*fixindex2] = ++*szam;
                        c--;
                    }
                    if (strcmp(indulas, "cw") == 0)
                    {
                        forgas = 'f';
                    }
                    else if (strcmp(indulas, "ccw") == 0)
                    {
                        forgas = 'l';
                    }
                    break;
                case 'f':
                    
                    while (c != 0)
                    {
                        vegleges[--*fixindex1][*fixindex2] = ++*szam;
                        c--;
                    }
                    if (strcmp(indulas, "cw") == 0)
                    {
                        forgas = 'j';    
                    }
                    else if (strcmp(indulas, "ccw") == 0)
                    {
                        forgas = 'b';
                    }
                    break;
                case 'j':
                    while (c != 0)
                    {
                        vegleges[*fixindex1][++*fixindex2] = ++*szam;
                        c--;
                    }
                    if (strcmp(indulas, "cw") == 0)
                    {
                        forgas = 'l';    
                    }
                    else if (strcmp(indulas, "ccw") == 0)
                    {
                        forgas = 'f';
                    }
                    break;
                case 'l':
                    while (c != 0)
                    {
                        vegleges[++*fixindex1][*fixindex2] = ++*szam;
                        c--;
                    }
                    if (strcmp(indulas, "cw") == 0)
                    {
                        forgas = 'b';
                    }
                    else if (strcmp(indulas, "ccw") == 0)
                    {
                        forgas = 'j';
                    }
                    break;
            }
            if (a == dimenzio - 1 && b == 2)
            {
                b = 1;
                (*temp)++;
                if (*temp == 2)
                {
                    b = 3;
                }
            }
            b++;
        }
    }
    printf("\nA matrix letrejott!\n");
    //felszabaditasok
    free(szam);
    free(fixindex1);
    free(fixindex2);
    free(temp);

    return vegleges;

    //matrix felszabaditasa
    for (int i = 0; i < dimenzio; i++) 
    {
        free(vegleges[i]);
    }
    free(vegleges);
}

void megjelenites(int** matrix, int dimenzio)
{
    for (int i = 0; i < dimenzio; i++)
    {
        printf("\n");
        for (int j = 0; j < dimenzio; j++)
        {
            if (dimenzio * dimenzio < 10)
            {
                printf("%d ", matrix[i][j]);
            }
            else if (dimenzio * dimenzio > 9 && dimenzio * dimenzio < 100)
            {
                if (matrix[i][j] < 10)
                {
                    printf(" %d ", matrix[i][j]);
                }
                else
                {
                    printf("%d ", matrix[i][j]);
                }
            }
            else if (dimenzio * dimenzio > 99)
            {
                if (matrix[i][j] < 10)
                {
                    printf("  %d ", matrix[i][j]);
                }
                else if (matrix[i][j] < 100)
                {
                    printf(" %d ", matrix[i][j]);
                }
                else 
                {
                    printf("%d ", matrix[i][j]);
                }
            }
        }
    }
    printf("\n");
}

void mentes(int** matrix, int dimenzio, char forgas, char* indulas)
{
    if (forgas == 'X')
    {
        printf("\nEzt a matrixot fajlbol toltottuk fel, igy nem tudjuk a megfelelo formatumban elmenteni.\n");
    }
    else
    {
        //fajlnev letrehozasa
        char* fajlnev = malloc(sizeof(char) * 16);
        sprintf(fajlnev, "spiral%d%c%s.txt", dimenzio, forgas, indulas);
        
        //fajl megnyitasa
        FILE *fajl = fopen(fajlnev, "w");
        printf("\nA fajl letrejott es sikeresen megnyitottuk!\n");
        
        //matrix beleirasa
        for (int i = 0; i < dimenzio; i++)
        {
            for (int j = 0; j < dimenzio; j++)
            {
                if (dimenzio * dimenzio < 10)
                {
                    fprintf(fajl, "%d ", matrix[i][j]);
                }
                else if (dimenzio * dimenzio > 9 && dimenzio * dimenzio < 100)
                {
                    if (matrix[i][j] < 10)
                    {
                        fprintf(fajl, " %d ", matrix[i][j]);
                    }
                    else
                    {
                        fprintf(fajl, "%d ", matrix[i][j]);
                    }
                }
                else if (dimenzio * dimenzio > 99)
                {
                    if (matrix[i][j] < 10)
                    {
                        fprintf(fajl, "  %d ", matrix[i][j]);
                    }
                    else if (matrix[i][j] < 100)
                    {
                        fprintf(fajl, " %d ", matrix[i][j]);
                    }
                    else 
                    {
                        fprintf(fajl, "%d ", matrix[i][j]);
                    }
                }
            }
            fprintf(fajl, "\n");
        }
    
        //fajl bezarasa
        fclose(fajl);
        
        //felszabaditas
        free(fajlnev);
        printf("A matrix belekerult a letrehozott fajlba!\n");
    }
}

int** betoltes(int* dimenzio)
{
    //deklaraciok
    char* fajlnev = malloc(sizeof(char) * 50);
    FILE* fajl;
    *dimenzio = 0;

    do
    {
        //fajlnev beolvasasa
        printf("\nAdja meg a fajl nevet: ");
        scanf("%s", fajlnev);

        //fajl megnyitasa
        fajl = fopen(fajlnev, "r");
        if (fajl == NULL) 
        {
            printf("\nNincs ilyen fajl!\n");
            int* c = malloc(sizeof(int));
            while ((*c = getchar()) != '\n' && *c != EOF);
            free(c);
            continue;
        }

        //dimenzio kiolvasasa a fajlnev-bol
        if (strtol(&fajlnev[6], NULL, 10))
        {
            if (strtol(&fajlnev[7], NULL, 10))
            {
                *dimenzio = (strtol(&fajlnev[6], NULL, 10));
            }
            else
            {
                *dimenzio = strtol(&fajlnev[6], NULL, 10);
            }
        }
        else
        {
            printf("\nA fajl neve hibas, nem tudom kiolvasni a dimenziot. Kerlek igy add meg a fajlnevet:\n");
            printf("<< spiral*dimenzio szama**forgas iranya**indulas iranya*.txt >>\n");
        }

    } while (fajl == NULL || *dimenzio == 0);

    //matrix letrehozasa, feltoltese
    int** matrix = (int**)malloc(sizeof(int*) * *dimenzio);
    for (int i = 0; i < *dimenzio; i++) 
    {
        matrix[i] = (int*)malloc(sizeof(int) * *dimenzio);
    }
    int* siker = malloc(sizeof(int));
    *siker = 0;
    for (int i = 0; i < *dimenzio; i++)
    {
        for (int j = 0; j < *dimenzio; j++)
        {
            if (fscanf(fajl, "%d", &matrix[i][j]) != 1) 
            {
                (*siker)++;
                break;
            }
        }
    }
    if (*siker == 0)
    {
        printf("\nA matrixot sikeresen beolvastuk!\n");
    }
    else
    {
        printf("\nHiba a fajlbol valo beolvasasnal. :(\n");
    }

    //fajl bezarasa
    fclose(fajl);

    //felszabaditas
    free(fajlnev);
    free(siker);

    return matrix;

    //matrix felszabaditasa
    for (int i = 0; i < *dimenzio; i++) 
    {
        for (int j = 0; j < *dimenzio; j++)
        {
            free(matrix[i]);
        }
    }
    free(matrix);
}