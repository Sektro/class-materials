#include<stdio.h>

int main() {
    
    int i = 0;
     /*          
    for(i=0; i<=10; i++) {
        printf("Fut %d\n", i);
        // Vajon hányszor fut le?
        // 6 - szor
        i++;
    } 
    printf("%d\n", i);
    */
    do 
    {
        printf("Fut %d\n", i);
        // Vajon hányszor fut le?
        // 6 - szor
        i++;
    }while (++i <= 10);
    printf("%d\n", i);
    return 0;
}