#include "pointers.h"
#include <iostream>

void pointers()
{
    // Pár egyszerû feladat a pointerek gyakorlására

    // Mi A értéke?
    int A = 3;
    int* pA = &A;
    *pA = 2;
    std::cout << "a: " << A << std::endl;
    //cout << "a: " << A << endl; is lehet, ha void elé: using namespace std; (hosszú projekteknél nem ajánlott)

    // Mik az értékek b-ben?
    // Segítség:++i == i += 1 -> i használata
    //          i++ == i használata -> i += 1
    int b[] = { 0,1,2 };
    int* bp = b;
    *(bp++) = 3;
    // eredmény: {3,1,2}, ++b-vel: {0,1,3}
    /*
    std::cout << "b: ";
    for (int i = 0; i < 3; ++i) std::cout << b[i] << " ";
    std::cout << std::endl;
    */

    // Const értéket nem lehet megváltoztatni
    const int c = 0;
    //c = 1; // Forditási hiba
    //int* cp = &c; // Forditási hiba
    const int* cp = &c; //const intre mutató változtatható pointer
    // const int * const cp ==> const intre mutató NEM változtatható pointer

    // Mi a különbség, ha van?
    int d = 1;

    int* dp1                = &d;
    int const* dp2          = &d;
    int* const dp3          = &d;
    int const* const dp4    = &d; // = 6
    const int* dp5          = &d;
    const int* const dp6    = &d; // = 4

    // Írjuk át a toFahrenheit fv-t, hogy módosítsa a változó értékét
    float temperature = 10.0f;
    std::cout << "Temperature in Celsius: " << temperature << std::endl;
    toFahrenheit(temperature);
    std::cout << "Temperature in Fahrenheit: " << temperature << std::endl;
}

void toFahrenheit(float inCelsius) {
    inCelsius = (inCelsius * 9.0f / 5.0f) + 32.0f;
}
//void toFahrenheit(float& inCelsius) referencia (nem lesz másolás, az eredeti változóval fog dolgozni)
/*
* void toFahrenheit(float* inCelsius) {
    *inCelsius = (*inCelsius * 9.0f / 5.0f) + 32.0f;
}
*/