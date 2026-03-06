int *nagyobb(int* a, int* b)
{
    if (*a > *b)
    {
        return a;
    }
    else 
    {
        return b;
    }
}

void swap (int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

int zsa()
{
    return 5;
}

int zse()
{
    return 3 * zsa();
}