#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "beadando.h"

int main()
{
    //deklaraciok: 
    int mode;
    int* dimenzio = malloc(sizeof(int));
    *dimenzio = 1;
    char* forgas = malloc(sizeof(char));
    *forgas = 'o';
    char indulas[4];
    strcpy(indulas, "boo");
    //ideiglenes matrix letrehozasa
    int** matrix = (int**)malloc(sizeof(int*) * *dimenzio);
    for (int i = 0; i < *dimenzio; i++) 
    {
        matrix[i] = (int*)malloc(sizeof(int) * *dimenzio);
    }
    for (int i = 0; i < *dimenzio; i++)
    {
        for (int j = 0; j < *dimenzio; j++)
        {
            matrix[i][j] = 0;
        }
    }
    initialize();
    do 
    {
        printf("\nMod, amit valasztok -> ");
        if (scanf("%d", &mode) != 1) 
        {
        printf("\nHIBAS BEMENET! Adj meg egy egesz szamot!\n");
        fflush(stdin);
        continue;
        }

        switch (mode)
        {
            case 0:
            {
                kezikonyv();
                break;
            }
            case 1: 
            {
                //ideiglenes matrix felszabaditasa annak erdekeben, hogy ne legyen memoriaszemetunk
                for (int i = 0; i < *dimenzio; i++) 
                {
                    free(matrix[i]); 
                    matrix[i] = NULL;
                }
                free(matrix);
                matrix = NULL;
                //ertekellenorzeses beolvasas dimenzio-ra
                do
                {
                    printf("\nAdja meg a dimenziot (1-20): ");
                    if (scanf("%d", dimenzio) != 1) 
                    {
                        printf("\nHIBAS BEMENET! Adj meg egy egesz szamot!\n");
                        int* c = malloc(sizeof(int));
                        while ((*c = getchar()) != '\n' && *c != EOF);
                        *dimenzio = -1;
                        free(c);
                        continue;
                    }
                    if (*dimenzio < 1 || *dimenzio > 20)
                    {
                        printf("\n* AZ ERTEK 1 ES 20 KOZT LEGYEN *\n");
                    }
                } while (*dimenzio < 1 || *dimenzio > 20);
                //új matrix letrehozasa
                matrix = (int**)malloc(sizeof(int*) * *dimenzio);
                for (int i = 0; i < *dimenzio; i++) 
                {
                    matrix[i] = (int*)malloc(sizeof(int) * *dimenzio);
                }
                
                //ertekellenorzeses beolvasas forgas-ra
                do
                {
                    printf("Adja meg a forgas iranyat (b, f, j, l): ");
                    scanf(" %c", forgas);
                    fflush(stdin);
                    if (*forgas != 'b' && *forgas != 'f' && *forgas != 'j' && *forgas != 'l')
                    {
                        printf("\n* AZ ERTEK b, f, j VAGY l LEGYEN / AZ ELSO KARAKTER AZ EGYIK LEGYEN EZEK KOZUL! *\n\n");
                    }
                } while (*forgas != 'b' && *forgas != 'f' && *forgas != 'j' && *forgas != 'l');

                //ertekellenorzeses beolvasas indulas-ra
                do
                {
                    printf("Adja meg az indulas iranyat (cw, ccw): ");
                    scanf("%s", indulas);
                    if (strcmp(indulas, "cw") != 0 && strcmp(indulas, "ccw") != 0)
                    {
                        printf("\n* AZ ERTEK cw VAGY ccw LEGYEN *\n\n");
                    }
                } while (strcmp(indulas, "cw") != 0 && strcmp(indulas, "ccw") != 0);

                matrix = generalas(*dimenzio, *forgas, indulas);
                break;
            }
            case 2:
            {
                if (matrix[0][0] != 0) 
                {
                    megjelenites(matrix, *dimenzio);
                    break;
                }
                else
                {
                    printf("\nNincs meg matrix, amit kirajzolhatnank. Generalj egyet!\n");
                    break;
                }
            }
            case 3:
            {
                if (matrix[0][0] != 0) 
                {
                    mentes(matrix, *dimenzio, *forgas, indulas);
                    break;
                }
                else
                {
                    printf("\nNincs meg matrix, amelyet beleirhatnank fajlba. Generalj egyet!\n");
                    break;
                }
            }
            case 4:
            {
                matrix = betoltes(dimenzio);
                *forgas = 'X';
                break; 
            }
            case 5:
                continue;
            default:
                printf("\nAz ertekek, amiket megadhat: 0, 1, 2, 3, 4 es 5\n");
                continue;
        }
    } while (mode != 5);
    printf("\nViszlat!\n\n");

    //matrix felszabaditasa
    for (int i = 0; i < *dimenzio; i++) 
    {
        for (int j = 0; j < *dimenzio; j++)
        {
            free(matrix[i]);
        }
    }
    free(matrix);

    //minden mas felszabaditasa
    free(dimenzio);
    free(forgas);

    return 0;
}