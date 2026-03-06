#include <stdio.h>
#include "box_lib.h"

int main()
{
    initialize();

    Push(1);
    Push(3);
    Push(2);

    printStack();

    Pop(); //kidobtuk a 2-est

    printStack();

    copyTop(); //masoljuk a 3-at

    printStack();

    Push(1);
    Push(2);
    Push(3);

    printStack();

    return 0;
}