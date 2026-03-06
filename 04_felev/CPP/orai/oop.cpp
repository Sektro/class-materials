#include <iostream>

class Circle
{
  double x, y;
  double r;

public:
  Circle(double x, double y, double r)
  {
    this->x = x;
    this->y = y;
    this->r = r > 0 ? r : 1;
  }

  void setCenter(double x, double y)
  {
    this->x = x;
    this->y = y;
  }

  void resize(double r)
  {
    if (r > 0)
      this->r = r;
  }

  double area()
  {
    return r * r * 3.14;
  }
};


int main()
{
  Circle c(0, 0, 5);
  std::cout << c.area() << std::endl;

  Circle* p = new Circle(0, 0, 6);
  delete p;

  int* t = new int[10];
  delete[] t;
}
