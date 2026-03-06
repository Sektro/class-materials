#include <iostream>

class Rac
{
  friend Rac operator*(const Rac&, const Rac&);
  friend std::ostream& operator<<(std::ostream&, const Rac&);

public:
  Rac(int sz = 0, int n = 1)
  {
    // TODO: Egyszerűsítés
    this->sz = sz;
    this->n = n == 0 ? 1 : n;
  }

/*
  operator double()
  {
    return (double)sz / n;
  }
*/
  double to_double() const
  {
    return (double)sz / n;
  }

/*
  Rac& operator=(const Rac& other)
  {
    sz = other.sz;
    n = other.n;
    return *this;
  }
*/
  
  /*
  Rac operator*(const Rac& right)
  {
    return Rac(sz * right.sz, n * right.n);
  }
  */
private:
  int sz, n;
};

Rac operator*(const Rac& left, const Rac& right)
{
  return Rac(left.sz * right.sz, left.n * right.n);
}

std::ostream& operator<<(std::ostream& out, const Rac& r)
{
  out << r.sz << '/' << r.n;
  return out;
}

int main()
{
  Rac r1(1, 2);  // 1/2
  Rac r2(42);    // 42/1
  Rac r6 = 42;
  Rac r3;        // 0/1
  Rac r4(r1);
  Rac r5 = r1;
  r3 = r1;
  r3 = 42;
  r3.operator=(42);
  r5 = r1 * r2;
  // r5 = r1.operator*(10);
  r5 = r1 * 3;
  r5 = 3 * r1;
  operator*(3, r1);
  //double d = r1;   // 0.5
  double d = r1.to_double();
  (std::cout << r1) << std::endl;
}
