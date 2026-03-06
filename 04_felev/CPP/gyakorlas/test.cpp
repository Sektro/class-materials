#include <iostream>
template <typename F, typename T = void>
class Test {
    private:
        int x;
        int y;
        F function;
    public: 
        Test(int x = 12, int y = 12) {
            this->x = x;
            this->y = y;
        }
        Test(const Test& other) {
            this->x = other.x;
            this->y = other.y;
        }
        ~Test() {} // nem kell semmi, mivel nincs semmi a heap-en
        Test& operator=(const Test& other) {
            this->x = other.x;
            this->y = other.y;
        }

        int& getX() {return x;}
        int& getY() {return y;}
        void increaseX() {++x;}
        void increaseY() {++y;}
        void xyFunction() {
            std::cout << function(x,y) << std::endl;
        }
};
template <typename F>
class Test<F,void>
{
    
};

void kiiras(Test<std::multiplies<int>, int>& t) {
    std::cout << t.getX() << " - " << t.getY() << std::endl;
}

int main() {
    /*
    // konstruktor
    Test t;
    std::cout << "Hello brother!" << std::endl;
    std::cout << "t" << std::endl;
    kiiras(t);

    // copy konstruktor
    Test t2 = t;
    std::cout << "t2" << std::endl;
    t2.increaseX();
    t2.increaseX();
    t2.increaseY();
    kiiras(t2);

    // = operator
    Test t3;
    t3 = t2;
    std::cout << "t3" << std::endl;
    kiiras(t3);
    */

    int m[] = {2,3,4};
    Test<std::multiplies<int>, int> t;
    t.xyFunction();
    Test<int> t2;
}