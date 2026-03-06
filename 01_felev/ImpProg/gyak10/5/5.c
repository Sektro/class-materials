#include <stdio.h>
#define KIIR(a) printf(#a)

#define PRINT(x) printf("%s\n", #x)

int main()
{
    KIIR(makro parametere\n);
    KIIR(54\n);

    PRINT(13);
    PRINT(hello);

    return 0;
}