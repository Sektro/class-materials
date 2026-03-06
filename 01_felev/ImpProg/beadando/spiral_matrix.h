#ifndef SPIRAL_MATRIX
#define SPIRAL_MATRIX

void program_menu();
void show_matrix(int d, int** matrix);
void save_matrix(int d, int **matrix, int ind, int rotation);
void parameterek(int *dim, int* ind, int* rotation);
int** generate_matrix(int dim, int ind, int rotation, int** actmatrix);
//void load_matrix(int *dim, int **matrix);

#endif