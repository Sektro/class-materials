#include<stdio.h>
#include<stdlib.h>
int main()
{
	int *ptr = (int *)malloc(sizeof(int));
	*ptr = 10;
	free(ptr);
	ptr = 9; // a pointert változtatja meg
	printf("%d", ptr);
}