#include<stdio.h>

int main() {
    
    int i;
               //--i esetén más lenne?
    for(i=0; 10>=i--; i+=2) {
        printf("Fut %d\n", i);
        // Vajon hányszor fut le?
    } 

    printf("\n");

    int j;
               //--i esetén más lenne?
    for(j=0; 10>=--j; j+=2) {
        printf("Fut %d\n", j);
        // Vajon hányszor fut le?
    } 

    return 0;
}