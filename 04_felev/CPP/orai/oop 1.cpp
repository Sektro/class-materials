#include <iostream>

class Shape
{
public:
  Shape(int x, int y)
  {
    this->x = x;
    this->y = y;
  }

  virtual double area() = 0;

protected:
  double x, y;
};

class Circle : public Shape
{
  double r;

public:
  Circle(double x = 0, double y = 0, double r = 1) : Shape(x, y)
  {
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

  double area() override
  {
    return r * r * 3.14;
  }
};

int main()
{
  Circle c(1, 2, 3);

  Shape* shapes[3];

  Circle c1, c2, c3;
  shapes[0] = &c1;
  shapes[1] = &c2;
  shapes[2] = &c3;

  double sum = 0;

  for (int i = 0; i < 3; ++i)
    sum += shapes[i]->area();

  std::cout << "A területek összege: " << sum << std::endl;
}
