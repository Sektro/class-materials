#include <iostream>

template <int i>
int f(int n)
{
  return i + n;
}

template <int i>
struct Fact
{
  static const int value = Fact<i - 1>::value * i;
};

template <>
struct Fact<0>
{
  static const int value = 1;
};

int main()
{
  std::cout << Fact<5>::value << std::endl;
}
