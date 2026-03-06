#include <iostream>
#include <functional>
#include <algorithm>
#include <string>
// -------------------------- 2 paraméter --------------------------
template <typename T, typename F = std::plus<T>>
class array_accumulate_partition 
{
    private:
        T* a;
        T* b;
        int size;
        int idx;
        F function;
    public:
        array_accumulate_partition( T* t, int size ) 
        {
            //this->a = a;
            this->a = new T[size];
            this->b = new T[size];
            for (int i = 0; i < size; ++i) {
                a[i] = t[i];
                b[i] = t[i];
            }
            this->size = size;
            this->idx = 0;
            this->function = F();
        }
        template <size_t S>
        array_accumulate_partition( T (&t)[S]) 
        {
            this->size = S;
            //this->a = &t[0];
            this->a = new T[size];
            this->b = new T[size];
            for (int i = 0; i < size; ++i) {
                a[i] = t[i];
                b[i] = t[i];
            }
            this->size = size;
            this->idx = 0;
            this->function = F();
        }
        ~array_accumulate_partition() {
            delete[] b;
            delete[] a;
        }

        void set_partition_idx(int i = 0) {
            int past = this->idx;
            this->idx = i;
            rearrange(idx);
        }
        void rearrange(int idx) {
            for (int i = 0; i < size; ++i) {
                a[i] = b[i];
            }
            for (int i = 1; i < idx; ++i) {
                a[i] = function(a[i],a[i-1]);
            }
        }
        void moveIdx(int i) {
            set_partition_idx(idx + i);
        }
        T* abegin() const {
            return &a[0];
        }
        T* aend() const {
            return &a[idx];
        }
        T* begin() const {
            return &a[idx];
        }
        T* end() const {
            return &a[size];
        }
        void writea() {
            std::cout << "-----------------" << std::endl;
            for (int i = 0; i < size; ++i) {
                std::cout << a[i] << std::endl;
            }
            std::cout << "-----------------" << std::endl;
        }
};

template <typename T,typename F>
array_accumulate_partition<T,F> operator<<(const int& i, array_accumulate_partition<T,F>& right)
{
    right.moveIdx(-i);
    return right;
}
template <typename T,typename F>
array_accumulate_partition<T,F>& operator<<( array_accumulate_partition<T,F>& left, const int& i)
{
    left.moveIdx(-i);
    return left;
}
template <typename T,typename F>
array_accumulate_partition<T,F> operator>>(const int& i, array_accumulate_partition<T,F>& right)
{
    right.moveIdx(i);
    return right;
}
template <typename T,typename F>
array_accumulate_partition<T,F>& operator>>( array_accumulate_partition<T,F>& left, const int& i)
{
    left.moveIdx(i);
    return left;
}



struct is_even
{

  bool operator()( int i ) const
  {
    return 0 == i % 2;
  }

};
struct string_pred
{

  bool operator()( const std::string& s ) const
  {
    return s.find( "Hell" ) != std::string::npos;
  }

};

class FunctionTest {
    private:
        int x;
        int y;
    public:
        FunctionTest(int x = 0, int y = 0) {
            this->x = x;
            this->y = y;
        }
        FunctionTest operator()(const FunctionTest& i, const FunctionTest& j) {
            return FunctionTest(i.x + j.x, i.y + j.y);
        }
        int getX() {return x;}
        int getY() {return y;}
        int getX() const {return x;}
        int getY() const {return y;}
};
std::ostream& operator<<(std::ostream& out, const FunctionTest& f)
{
  out << f.getX() << " - " << f.getY();
  return out;
}

int main() {
/*
    int a[] = { 7, 3, 4, 1, 2 };
    array_accumulate_partition<int> ap( a, sizeof( a ) / sizeof( a[ 0 ] ) );

    ap.writea();
    ap.set_partition_idx(5);
    ap.writea();
    ap.set_partition_idx(1);
    ap.writea();
*/
/*
    double d[] = { 1.5, 1.8, 3.8, 1.1 };
    array_accumulate_partition<double> dp( d, sizeof( d ) / sizeof( d[ 0 ] ) );

    dp.writea();
    dp.set_partition_idx( 3 );
    dp.writea();
    dp.set_partition_idx( 0 );
    dp.writea();
*/
/*
    std::string s[] = { "Hello", "World", "!" };
    array_accumulate_partition<std::string> sp( s,sizeof( s ) / sizeof( s[ 0 ] ) );

    sp.writea();
    sp.set_partition_idx( 3 );
    sp.writea();
    sp.set_partition_idx( 1 );
    sp.writea();
*/
/*
    std::string s[] = { "Hello", "World", "!" };
    array_accumulate_partition<std::string> sp( s,sizeof( s ) / sizeof( s[ 0 ] ) );

    sp.writea();
    sp.set_partition_idx( 3 );
    sp.writea();
    1 << sp;
    sp << 1;
    sp.writea();
    1 >> sp;
    sp >> 1;
    sp.writea();
*/

    /*
    int your_mark = 1;
    // 2-es
    int a[] = { 7, 3, 4, 1, 2 };
    array_accumulate_partition<int> ap( a, sizeof( a ) / sizeof( a[ 0 ] ) );
    ap.set_partition_idx( 2 );
    ap.set_partition_idx( 4 );
    ap.set_partition_idx( 1 );
    
    double d[] = { 1.5, 1.8, 3.8, 1.1 };
    array_accumulate_partition<double> dp( d, sizeof( d ) / sizeof( d[ 0 ] ) );
    dp.set_partition_idx( 3 );
    
    std::string s[] = { "Hello", "World", "!" };
    array_accumulate_partition<std::string> sp( s,
                                                sizeof( s ) / sizeof( s[ 0 ] ) );
    sp.set_partition_idx( 2 );
    sp.set_partition_idx( 1 );
    sp.set_partition_idx( 2 );
    
    if( "!" == s[ 2 ] && 3.14 < d[ 1 ] && 7.0 < d[ 2 ] && 3 == a[ 1 ] &&
        4 == a[ 2 ] && 1.2 > d[ 3 ] && 1.55 > d[ 0 ] && "Hello" == s[ 0 ] )
    {
      your_mark = std::count( s[ 1 ].begin(), s[ 1 ].end(), 'o' );
    }
    
    // 3-as
    sp >> 1;
    2 << dp;
    3 >> ap;
    if( 15 == a[ 3 ] && a[ 1 ] * 1U == s[ 1 ].size() && 1.9 > d[ 1 ] )
    {
      your_mark = std::count( s[ 2 ].begin(), s[ 2 ].end(), 'l' );
    }
    
    // 4-es
    if( 3 == std::count_if( sp.abegin(), sp.aend(), string_pred() ) &&
        *ap.begin() == std::count_if( ap.abegin(), ap.aend(), is_even() ) )
    {
      your_mark = ap.aend() - ap.abegin();
    }

    // 5-os
    
    int m[] = { 5, 3, 2, 1, 6 };
    int t[] = { 2, 2, 2, 2, 2, 2 };
    array_accumulate_partition<int, std::multiplies<int> > mp( m );
    array_accumulate_partition<int, std::multiplies<int> > tp( t );
    tp.set_partition_idx( 4 );
    mp.set_partition_idx( 2 );
    mp >> 1;
    if( 30 == m[ 2 ] && 1 == m[ 3 ] && 16 == t[ 3 ] && 2 == t[ 4 ] &&
        1 == *mp.begin() && 15 == m[ 1 ] )
    {
      your_mark = ( mp.end() - mp.begin() ) + ( mp.aend() - mp.abegin() );
    }
      
    std::cout << "Your mark is " << your_mark;
    std::endl( std::cout );
    */
    /*
    int t2[] = { 2, 2, 2, 2, 2, 2 };
    array_accumulate_partition<int, std::divides<int> > np( t2 );
    np.writea();
    np.set_partition_idx( 4 );
    np.writea();
    */
   /*
    FunctionTest t3[] = { FunctionTest(1,2),FunctionTest(3,3),FunctionTest() };
    array_accumulate_partition<FunctionTest, FunctionTest > np( t3 );
    np.writea();
    np.set_partition_idx( 4 );
    np.writea();
    */
    std::cout << std::endl;
    std::cout << "asd" << std::endl;
}