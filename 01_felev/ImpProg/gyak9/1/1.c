#include <stdlib.h>
#include <stdio.h>

int main()
{
    int *x = malloc(4); // 4 byte lefoglalása //heap, kupac
    *x = 42;            // 4 byte feltöltése
    printf("%d\n", *x); // kiírás
    free(x);            //felszabadítás
    
    // Dinamikus tömb, VLA-k (Variable-Length Array / változó hosszúságú tömb) helyett
    int n;
    printf("Tomb merete? ");
    scanf("%d", &n);
    int *a = malloc(sizeof(int)*n);
    a[123] = 42;
    *(a+124) = 43;
    printf("%d, %d\n", *(a+123), a[124]);
    free(a);
	
    return 0;
}