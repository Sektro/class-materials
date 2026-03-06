#include <iostream>

class C
{
public:
  C() { std::cout << "construct" << std::endl; }
  C(const C&) { std::cout << "copy construct" << std::endl; }
  ~C() { std::cout << "destruct" << std::endl; }
  C& operator=(const C&) { std::cout << "op=" << std::endl; return *this; }
};

C f()
{
  C c;
  std::cout << "world" << std::endl;
  return c;
}

void swap(int& x, int& y)
{
  int t = x;
  x = y;
  y = t;
}

const int& max(const int& a, const int& b)
{
  return a < b ? b : a;
}

int main()
{
  C c;
  std::cout << "hello" << std::endl;
  C* p;

  {
    C c2;
    std::cout << "world" << std::endl;
    p = &c2;
  }

  std::cout << "blabla" << std::endl;
  *p

  /*
  const int i = 42;
  const int& r = i;
  r = 43;

  int i2;
  const int& r2 = i2;

  int x = 1;
  int y = 2;
  int nagyobb = max(1, 2);

  std::cout << nagyobb << std::endl;

  int* p = &i;  // Rossz
  const int* cp = &i;  // Jó
  const int j = 32;
  cp = &j;

  int* const cp2 = &i;
  const int* const cp3 = &i;
*/



/*
  C c1 = f();
  std::cout << "hello" << std::endl;

  int a, b;
  swap(42, b);
  */
}
