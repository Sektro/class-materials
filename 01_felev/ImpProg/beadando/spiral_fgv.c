#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include "spiral_matrix.h"

void program_menu()
{
    printf("|0| |Felhasznaloi Kezikonyv|\n");
    printf("|1| |Matrix Generalasa|\n");
    printf("|2| |Matrix Mentese|\n");
    printf("|3| |Matrix Betoltese|\n");
    printf("|4| |Matrix Kiirasa|\n");
    printf("|5| |Kilepes|\n");
}

void parameterek(int *dim, int *ind, int *rotation)
{
    char in[7];
    char ro[4];
    printf("Matrix dimenzioja: ");
    scanf("%d",dim);
    int di = *dim;
    printf("Spiral indulasi iranya: ");
    scanf("%s", in);
    printf("Spiral forgasiranya: ");
    scanf("%s", ro);

    //óramutató járásával megegyező (cw) vagy ellentétes (ccw)
    //balra, fel,jobbra, le
    if (strcmp(in,"balra")==0) {*ind = 4;}
    if (strcmp(in,"fel")==0) {*ind = 1;}
    if (strcmp(in,"jobbra")==0) {*ind = 2;}
    if (strcmp(in,"le")==0) {*ind = 3;}

    if (strcmp(ro,"cw")==0) {*rotation = 1;}
    if (strcmp(ro,"ccw")==0) {*rotation = 2;}
}

int** generate_matrix(int dim, int ind, int rotation, int** actmatrix)
{
    int szam = 1;
    int irany = ind;
    int** matrix = (int**)malloc(sizeof(int)*dim);
    for (int i = 0; i < dim; ++i)
    {
        matrix[i] = (int*)malloc(sizeof(int) *dim);
    }
    int start = (dim/2);

    //generacio
    //fcw
    if (ind == 1 && rotation == 1)
    {
        if (dim % 2 == 0)
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 1)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[j][start-1-(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 2)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4); ++j)
                    {
                        matrix[start-1-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[j][start+(i/4)] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4); j > start-(i/4)-2; --j)
                    {
                        matrix[start+1+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 1;
                }
            }
        }
        else
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 1)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[j][start-(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 2)
                {
                    for (int j = start-(i/4); j < start+(i/4)+1; ++j)
                    {
                        matrix[start-1-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[j][start+(i/4)+1] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4)+1; j > start-(i/4)-1; --j)
                    {
                        matrix[start+1+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 1;
                }
            }
        }
    }
    else if (ind == 1 && rotation == 2)
    {
        if (dim % 2 == 0)
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 1)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[j][start+(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 4;
                }
                else if (irany == 2)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[start+1+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[j][start-1-(i/4)] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[start-1-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
            }
        }
        else
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 1)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[j][start+(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 4;
                }
                else if (irany == 2)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[start+1+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
                    {
                        matrix[j][start-1-(i/4)] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4); j > start-(i/4)-1; --j)
                    {
                        matrix[start-1-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    --irany;
                }
            }
        }
    
    }
    //jcw
    else if (ind == 2 && rotation == 1)
    {
        if (dim % 2 == 0)
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 2)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4); ++j)
                    {
                        matrix[start-1-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4)-1; j < start+(i/4); ++j)
                    {
                        matrix[j][start+(i/4)] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4); j > start-(i/4)-2; --j)
                    {
                        matrix[start+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 1;
                }
                else if (irany == 1)
                {
                    for (int j = start+(i/4); j > start-(i/4)-2; --j)
                    {
                        matrix[j][start-2-(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
            }
        }
        else
        {
            for (int i = 0; i < ((dim * 2)-1); ++i)
            {
                if (irany == 2)
                {
                    for (int j = start-(i/4); j < start+(i/4)+1; ++j)
                    {
                        matrix[start-(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 3)
                {
                    for (int j = start-(i/4); j < start+(i/4)+1; ++j)
                    {
                        matrix[j][start+(i/4)+1] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
                else if (irany == 4)
                {
                    for (int j = start+(i/4)+1; j > start-(i/4)-1; --j)
                    {
                        matrix[start+1+(i/4)][j] = szam;
                        ++szam;
                    }
                    //printf("%d",irany);
                    irany = 1;
                }
                else if (irany == 1)
                {
                    for (int j = start+(i/4)+1; j > start-(i/4)-1; --j)
                    {
                        matrix[j][start-1-(i/4)] = szam; 
                        ++szam;
                    }
                    //printf("%d",irany);
                    ++irany;
                }
            }
        }
    }

    return matrix;

    for (int i = 0; i < dim; i++) 
    {
        free(matrix[i]);
    }
    free(matrix);
}

void show_matrix(int d, int **matrix)
{
    if ((d*d) < 10)
    {
        for (int i = 0; i <d;++i)
        {
            for (int j = 0; j<d; ++j)
            {
                printf("%d ",matrix[i][j]);
            }
            printf("\n");
        }
    }
   else if ((d*d) < 100)
   {
        for (int i = 0; i <d;++i)
        {
            for (int j = 0; j<d; ++j)
            {
                if (matrix[i][j] < 10) {printf("%d  ",matrix[i][j]);}
                else {printf("%d ",matrix[i][j]);}
            }
            printf("\n");
        }
   }
   else
   {
        for (int i = 0; i <d;++i)
        {
            for (int j = 0; j<d; ++j)
            {
                if (matrix[i][j] < 10) {printf("%d   ",matrix[i][j]);}
                else if (matrix[i][j] < 100) {printf("%d  ",matrix[i][j]);}
                else {printf("%d ",matrix[i][j]);}
            }
            printf("\n");
        }
   }
}

void save_matrix(int d, int** matrix, int ind, int rotation)
{
    FILE *fp;
    char nev[20];
    char *in;
    char *ro;
    
    if (ind == 1) {in = "balra";}
    if (ind == 2) {in = "fel";}
    if (ind == 3) {in = "jobbra";}
    if (ind == 4) {in = "le";}

    if (rotation == 1) {ro = "cw";}
    if (rotation == 2) {ro = "ccw";}

    sprintf(nev, "spiral%d%c%s.txt",d,in[0],ro);
    
    fp = fopen(nev, "w");
    for (int i = 0; i <d;++i)
    {
        for (int j = 0; j<d; ++j)
        {
            fprintf(fp, "%d ",matrix[i][j]);
        }
        fprintf(fp, "\n");
    }   
    fclose(fp);
}

/*
void load_matrix(int *dim, int** matrix)
{
    FILE *fp;
    *dim = 1;
    int hossz = 0;
    int x;
    char nev[20];
    scanf("%s",nev);
    int* temp = (int*)malloc(sizeof(int)*(*dim)*(*dim));

    fp = fopen(nev, "r");
    while (fscanf(fp, "%d", &x) == 1)
    {
        matrix = x;
        ++matrix;
        ++hossz;
    }
    *dim = sqrt(hossz);
    fclose(fp);
}
*/
