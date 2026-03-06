float skalaris_szorzat(float a[], float b[], int n)
{
    float szorzat = 0;
    for (int i = 0; i < n; ++i)
    {
        szorzat += a[i] * b[i];
    }

    return szorzat;
}