#ifndef BOX_LIB_
#define BOX_LIB_

struct Box
{
    struct Box *next;
    int weight;
};

typedef struct Box Box;

Box *top;

void initialize();
int isEmpty();
int peek();
void Push(int weight);
void Pop();
void copyTop();
void printStack();

#endif