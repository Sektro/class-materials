#include <stdio.h>

void a(int x);
void b(int x);

int a_iterations = 0;
int b_iterations = 0;

int main()
{
    int x;
    printf("x erteke: ");
    scanf("%d", &x);

    a(x);

    printf("Iteraciok: a: %d b: %d", a_iterations, b_iterations); 

    return 0;
}

void a(int x)
{
    a_iterations++;
    x /= 2;
    if (x > 0)
        b(x);
}

void b(int x)
{
    b_iterations++;
    x -= 1;
    if (x > 0)
        a(x);
}