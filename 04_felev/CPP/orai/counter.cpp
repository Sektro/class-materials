#include <iostream>

struct S
{
  class C
  {
    int i;
  };

  typedef int Integer;

  static int counter;

  S() { ++counter; }
  S(const S&) { ++counter; }
  ~S() { --counter; }
};

int S::counter;

template <typename T>
class vector
{
public:
  void push_back() {}
private:
  T* items;
};

template <>
class vector<bool>
{
public:
  void push_back() {}
  magic operator[](int n)
}

int main()
{
  std::cout << S::counter << std::endl;

  S::C x;
  S::Integer i = 42;

  std::vector<int> v;

  bool b[10];
  bool* p = &b[2];
  b + 1


  char c[] = "hello";
  c + 1
}
