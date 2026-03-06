#include<stdio.h>

int main() {
    
    int i;
            //++i esetén más lenne?
    for(i=5; i++; i-=2) {
        printf("Fut %d\n", i);
        // Vajon hányszor fut le?
        // 5+1=6 -> 6-2=4, 4+1=5 -> 5-2=3...
    } 

    printf("\n");

    int j;
            //++i esetén más lenne?
    for(j=5; ++j; j-=2) {
        printf("Fut %d\n", j);
        // Vajon hányszor fut le?
    } 

    return 0;
}