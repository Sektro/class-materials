#include <iostream>
#include <fstream>
#include <map>

/*
template <typename T1, typename T2>
struct pair
{
  T1 first;
  T2 second;
};
*/

void printIncludeCount(const std::map<std::string, int>& m)
{
  int counter = 0;

  std::cout << m["#include"];

  for (std::map<std::string, int>::const_iterator it = m.begin(); it != m.end(); ++it)
    if (it->first == "#include")
      ++counter;

  std::cout << counter << std::endl;
}

int main()
{
  std::map<std::string, int> words;

  std::ifstream f("wordcount.cpp");

  std::string word;
  while (f >> word)
    ++words[word];

  f.close();

  for (std::map<std::string, int>::iterator it = words.begin();
       it != words.end();
       ++it)
    std::cout << it->first << " -> " << it->second << std::endl;
}
