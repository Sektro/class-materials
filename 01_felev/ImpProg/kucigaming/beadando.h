#ifndef BEADANDO
#define BEADANDO

void initialize();
void kezikonyv();
int** generalas(int dimenzio, char forgas, char* indulas);
void megjelenites(int** matrix, int dimenzio);
void mentes(int** matrix, int dimenzio, char forgas, char* indulas);
int** betoltes(int* dimenzio);

#endif