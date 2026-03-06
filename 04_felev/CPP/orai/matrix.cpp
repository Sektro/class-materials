void swap1(int* a, int* b)
{
  int t = *a;
  *a = *b;
  *b = t;
}

void swap2(int& a, int& b)
{
  int t = a;
  a = b;
  b = t;
}

void f()
{
  int a = 1, b = 2;
  swap1(&a, &b);
  swap2(a, b);
}


int f()
{
  int i = 42;
  int* p = &i;
  *p == 42;
  int& r = i;
  r == 42;
}


class Matrix
{
public:
  Matrix(int row, int col)
  {
    // TODO: Negative values???
    items = new int[row * col];
    this->row = row;
    this->col = col;
  }

  Matrix(const Matrix& other)
  {
    *this = other;
    /*
    row = other.row;
    col = other.col;

    items = new int[row * col];

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
    items = new int[row * col];

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
  
  int& operator()(int i, int j)
  {
    return items[i * col + j];
  }

  int getRow()
  {
    return row;
  }

  int getCol()
  {
    return col;
  }

  ~Matrix()
  {
    delete[] items;
  }

private:
  int* items;
  int row, col;
};

int main()
{
  Matrix m1(3, 5), m2(3, 5);
  Matrix m3 = m1 + m2;

  for (int i = 0; i < m1.getRow(); ++i)
    for (int j = 0; j < m1.getCol(); ++j)
      m1(i, j) = 42;

  // std::cout << (m1 + m2) << std::endl;
}
