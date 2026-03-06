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
            this->a = t;
            this->b = new T[size];
            for (int i = 0; i < size; ++i) {
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
            this->a = &t[0];
            this->b = new T[size];
            for (int i = 0; i < size; ++i) {
                b[i] = t[i];
            }
            this->size = size;
            this->idx = 0;
            this->function = F();
        }
        ~array_accumulate_partition() {
            delete[] b;
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