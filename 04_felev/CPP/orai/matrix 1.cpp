#include <iostream>

template <typename T>
class Matrix
{
public:
  Matrix(int row, int col)
  {
    // TODO: Negative values???
    items = new T[row * col];
    this->row = row;
    this->col = col;
  }

  Matrix(const Matrix& other)
  {
    items = NULL;
    *this = other;
    /*
    row = other.row;
    col = other.col;

    items = new T[row * col];

    for (int i = 0; i < row * col; ++i)
      items[i] = other.items[i];
    */
  }

  Matrix& operator=(const Matrix& other)
  {
    if (this == &other)
      return *this;
    
    row = other.row;
    col = other.col;

    delete[] items;
    items = new T[row * col];

    for (int i = 0; i < row * col; ++i)
      items[i] = other.items[i];

    return *this;
  }

  Matrix operator+(const Matrix& right)
  {
    // TODO: Are they the same size?
    Matrix result(row, col);

    for (int i = 0; i < row * col; ++i)
      result.items[i] = items[i] + right.items[i];

    return result;
  }
  
  const T& operator()(int i, int j) const
  {
    return const_cast<Matrix*>(this)->operator()(i, j);
    // return (*const_cast<Matrix*>(this))(i, j);
  }

  T& operator()(int i, int j)
  {
    return items[i * col + j];
  }

  int getRow() const
  {
    return row;
  }

  int getCol() const
  {
    return col;
  }

  ~Matrix()
  {
    delete[] items;
  }

private:
  T* items;
  int row, col;
};

template <typename T>
void printMatrix(const Matrix<T>& m)
{
  for (int i = 0; i < m.getRow(); ++i)
  {
    for (int j = 0; j < m.getCol(); ++j)
      std::cout << m(i, j) << '\t';
    std::cout << std::endl;
  }
}

int main()
{
  Matrix<int> m1(3, 5), m2(3, 5);
  Matrix<int> m3 = m1 + m2;

  for (int i = 0; i < m1.getRow(); ++i)
    for (int j = 0; j < m1.getCol(); ++j)
      m1(i, j) = 42;

  printMatrix(m1);
  // std::cout << (m1 + m2) << std::endl;
}
