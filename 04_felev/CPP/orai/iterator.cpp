#include <iostream>
#include <algorithm>
#include <vector>
#include <list>
#include <set>

template <typename It, typename T>
It find(It b, It e, const T& item)
{
  while (b != e)
  {
    if (*b == item)
      return b;
    ++b;
  }

  return e;
}

int main()
{
  std::list<int> v;

  v.push_back(3);
  v.push_back(2);
  v.push_back(5);

  std::sort(v.begin(), v.end());

  for (auto i : v)
    std::cout << i << std::endl;

  std::list<int>::iterator it = v.begin();
  *it = 42;

  /*
  std::set<int> s;
  std::set<int>::iterator it = s.find(42);

  std::list<int> v;

  v.push_back(1);
  v.push_back(2);
  v.push_back(3);

  auto i = ::find(v.begin(), v.end(), 3);
  if (i == v.end())
    std::cout << "Nincs benne" << std::endl;
  else
  {
    std::cout << "Benne van" << std::endl;
    *i = 42;
  }
  */
}
