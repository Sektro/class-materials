#include <iostream>

template <typename T>
const T& max(const T& a, const T& b)
{
  return a < b ? b : a;
}

int main()
{
  std::cout << max(3.0, 3.14) << std::endl;
  std::cout << max(3, 3) << std::endl;
  std::cout << max<double>(3, 3.14) << std::endl;
}
