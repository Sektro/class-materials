#include <stdlib.h>
#include <stdio.h>

int main() {
	char nev1[20], *nev2=malloc(20), c; // (char*)malloc(20*sizeof(char))
	long long int egesz;
	double valos;
	scanf("%19[^\n]s",nev1);  	// cím, méret, határoló
	do				// puffer ürítés
	{				//ha maradt benne karakter +
		scanf("%c",&c);		// soremelés mindenképp
	} while (c != '\n');
	scanf("%lli%lf",&egesz,&valos);	// lli, lf: méret, cím
	do				// puffer ürítés
	{				// szám után soremelés a pufferben marad
		scanf("%c",&c);
	} while (c != '\n');
	scanf("%19[^\n]s",nev2);
	do
	{
		scanf("%c",&c);
	} while (c != '\n');
	printf("\nnev1: %s\n", nev1);
	printf("Egész: %lli\nValós: %f\n", egesz, valos);
	printf("nev2: %s\n", nev2);
	free(nev2);			// memória szivárgás
	return 0;
}
