#include <stdio.h>
#include <stdlib.h>
#include "box_lib.h"

void initialize()
{
    top = NULL;
}

int isEmpty()
{
    return top == NULL;
}

int peek()
{
    if (!isEmpty())
    {
        return top->weight;
    }

    return 0;
}

void Push(int weight)
{
    Box *box = malloc(sizeof(Box));
    if (box == NULL)
    {
        printf("Nem sikerult memoriat foglalni\n");
        exit(1);
    }

    box->weight = weight;
    box->next = top;
    top = box;
}

void Pop()
{
    if (isEmpty())
    {
        printf("Ures!\n");
        exit(1);
    }
    Box *temp = top->next;
    free(top);
    top = temp;
}

void copyTop()
{
    if (isEmpty())
    {
        printf("Ures! Nincs mit masolni!\n");
        exit(1);
    }
    Push(top->weight);
}

void printStack()
{
    Box *box = top;
    while (box != NULL)
    {
        printf("%d,", box->weight);
        box = box->next;
    }
    printf("\n");
}