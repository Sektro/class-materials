#include <iostream>

namespace geometry
{
  struct Circle;

  operator<<(std::ostream&, const Circle&);
}

int main()
{
  Circle c;
  std::cout << c;
  std::cout std::<< "hello";
  std::operator<<(std::cout, "hello");
  std::operator<<(std::cout, 42);
}
