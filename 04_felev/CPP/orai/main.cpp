#include <iostream>

class vector
{
public:
  explicit vector(int size) {}
};

void f(vector v) {}

class Shape
{
public:
  Shape() { std::cout << "Construct shape" << std::endl; }
  Shape(const Shape&) { std::cout << "copy shape" << std::endl; }
  virtual ~Shape() { std::cout << "destruct shape" << std::endl; }
};

class Circle : public Shape
{
public:
  Circle() { std::cout << "Construct Circle" << std::endl; }
  Circle(const Circle&) { std::cout << "copy Circle" << std::endl; }
  ~Circle() { std::cout << "destruct Circle" << std::endl; }
};

class C2
{
public:
  C2(int i) {}
};

class C
{
public:
  C(int x) : i(42), c2(x)
  {
  }
  const int i;
  C2 c2;
};

int g() {}

void f()
{
  int t[g()];
}

int main()
{
  Shape* c = new Circle;
  delete c;

  {
    Shape s;
  }
  std::cout << "blabla" << std::endl;


  /*
  f(42); // rossz
  vector v(42); // jó
  vector v2 = 42; // rossz

  v.push_back(1);
  v.push_back(2);
  v.push_back(3);
  */
}
