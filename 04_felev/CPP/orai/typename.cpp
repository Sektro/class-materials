template <typename T>
struct S
{
  static int i;
};

template <typename T>
int S<T>::i = 0;

template <>
struct S<double>
{
  class i
  {

  };
};

template <typename T>
void f()
{
  typename S<T>::i * x;
}

int main()
{
  S<int> sInt;
  S<double> sDouble;

  S<int>::i = 42;
  S<double>::i x;

  f<double>();
}
