#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include "spiral_matrix.h"

int main()
{
    int endp = 0;
    int choice = 0;
    int dim = 1;
    int ind = 0;
    int rotation = 0;
    int** matrix = (int**)malloc(sizeof(int)*dim);
    for (int i = 0; i < dim; ++i)
    {
        matrix[i] = (int*)malloc(sizeof(int) *dim);
    }
    for (int i = 0; i < dim; ++i)
    {
        for (int j = 0; j < dim; ++j)
        {
            matrix[i][j] = 0;
        }
    }

    printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    printf("{###}-{Spriral Matrix Program}-{###}\n");
    printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    printf("\n ");
    printf("A program csak páros, fel, cw parameterekkel mukodik");
    printf("\n ");
    do
    {
        program_menu();
        printf("Valasz: ");
        scanf("%d",&choice);
        switch (choice) 
        {
            case 0:
            {
                printf("1: Ezzel keszithetsz uj matrixot \n(indulasi iranyok: fel,le,jobbra,balra, forgasi iranyok: cw, ccw)\n");
                printf("\n");
                printf("2: Ezzel mentheted el egy .txt fajlba a tarolt matrixot\n");
                printf("\n");
                printf("3: *Under Development*\n");
                printf("\n");
                printf("4: Egyszeruen kiirja az eppen tarolt matrixot, legyen az generalt, vagy betoltott\n");
                printf("\n");
                printf("5: Kilepsz vele a programbol. Viszlat!\n");
                printf("\n");
                break;
            }
            case 1: 
            {
                parameterek(&dim,&ind,&rotation);
                for (int i = 0; i < dim; i++) 
                {
                    free(matrix[i]);
                    matrix[i] = NULL;
                }
                free(matrix);
                matrix = NULL;
                matrix = (int**)malloc(sizeof(int)*dim);
                for (int i = 0; i < dim; ++i)
                {
                    matrix[i] = (int*)malloc(sizeof(int) *dim);
                }
                if (dim > 0) {matrix = generate_matrix(dim,ind,rotation,matrix);}
                else {printf("A dimenzio nem eleg nagy!");}
                break;
            }
            case 2:
            {
                save_matrix(dim,matrix,ind,rotation);
                break;
            }
            /*
            case 3:
            {
                load_matrix(&dim,matrix);
            }
            */
            case 4:
            {
                show_matrix(dim,matrix);
                break;
            }
            case 5: 
            {
                endp = 1;
                break;
            }
        }
        /*
        int actmatrix[dim][dim];
        if (dim > 0 && choice == 1) { printf("matrix generated:\n");}
        if (choice == 2) {save_matrix(dim,actmatrix,ind,rotation);}
        if (choice == 3) {load_matrix(&dim,&actmatrix);}
        */
    } while (endp == 0);

    for (int i = 0; i < dim; i++)
    {
        free(matrix[i]);
    }
    free(matrix);
    
    return 0;
}

