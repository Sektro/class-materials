#include<stdio.h>
#include<stdlib.h>
int main()
{
	int *p;
	p = (int *)malloc(20);
	printf("%d\n", sizeof(p)); // a pointer mérete, nem tudjuk lekérdezni az allokált...
	free(p);
	return 0;
}