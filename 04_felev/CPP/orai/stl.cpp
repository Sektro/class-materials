#include <iostream>
#include <vector>
#include <list>
#include <deque>
#include <string>
#include <set>
#include <fstream>
#include <tuple>

struct Circle
{
  Circle(int x, int y, int r) : x(x), y(y), r(r)
  {
  }

  int x, y, r;
};

bool operator<(const Circle& c1, const Circle& c2)
{
  return c1.r < c2.r;
}

struct CircleCompare
{
  bool operator()(const Circle& c1, const Circle& c2) const
  {
    return std::tie(c1.r, c1.x, c1.y)
      < std::tie(c2.r, c2.x, c2.y);
  }
};

/*
template <typename T>
struct Less
{
  bool operator()(const T& t1, const T& t2) const
  {
    return t1 < t2;
  }
};

template <typename T, typename Comparator = std::less<T> >
class set
{

};
*/

int main()
{
  std::set<Circle, CircleCompare> circles;

  circles.insert(Circle(0, 0, 5));
  circles.insert(Circle(0, 0, 2));
  circles.insert(Circle(0, 0, 3));
  circles.insert(Circle(1, 2, 3));

  std::cout << circles.size() << std::endl;

  std::ifstream f("stl.cpp");

  std::set<std::string> words;

  std::string word;
  while (f >> word)
  {
    words.insert(word);
  }

  std::cout << words.size() << std::endl;
  
  for (std::set<std::string>::iterator i = words.begin(); i != words.end(); ++i)
    std::cout << *i << std::endl;

  f.close();
}
