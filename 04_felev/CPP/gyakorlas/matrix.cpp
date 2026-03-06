#include <iostream>

class Matrix {
    private:
        int* items;
        int row;
        int col;
    public:
        Matrix(int row = 1, int col = 1) {this->row = row; this->col = col; this->items = new int[row * col];}
        Matrix(const Matrix& other) {this->row = other.row; this->col = other.col; this->items = new int[row * col];
            for (int i = 0; i < row*col; ++i) {
                items[i] = other.items[i];
            }
        }
        ~Matrix() {delete[] items;}
        Matrix& operator=(const Matrix& other) {
            if (this == &other) {
                return *this;
            }
            delete[] items;
            this->row = other.row; this->col = other.col; this->items = new int[row * col];
            for (int i = 0; i < row*col; ++i) {
                items[i] = other.items[i];
            }
            return *this;
        }
        Matrix operator+(const Matrix& right) {
            Matrix result(row, col);
            for (int i = 0; i < row*col; ++i) {
                result.items[i] = items[i] + right.items[i];
            }
            return result;
        }
        int& operator()(int i, int j) {
            return items[i*col + j];
        }

        int getRow() {return row;}
        int getCol() {return col;}
};

void printMatrix(Matrix& matrix) {
    for (int i = 0; i < matrix.getRow(); ++i) {
        for (int j = 0; j < matrix.getCol(); ++j) {
            std::cout << matrix(i,j) << " ";
        }
        std::cout << std::endl;
    }
}

int main() {
    Matrix m1(3,5), m2(3,5);
    for (int i = 0; i < m1.getRow(); ++i) {
        for (int j = 0; j < m1.getCol(); ++j) {
            m1(i,j) = 42;
        }
    }
    printMatrix(m1);
    for (int i = 0; i < m2.getRow(); ++i) {
        for (int j = 0; j < m2.getCol(); ++j) {
            m2(i,j) = 42;
        }
    }
    printMatrix(m2);
    Matrix m3 = m1+m2;
    printMatrix(m3);
}