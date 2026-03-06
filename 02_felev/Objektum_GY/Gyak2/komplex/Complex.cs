namespace komplex
{
    public class Complex
    {
        public double real;
        public double imaginary;

        public Complex(double real, double imaginary)
        {
            this.real = real;
            this.imaginary = imaginary;
        }
        public static Complex Add(Complex a, Complex b)
        {
            return new Complex(a.real + b.real, a.imaginary + b.imaginary);
        }
        public static Complex Subtract(Complex a, Complex b)
        {
            return new Complex(a.real - b.real, a.imaginary - b.imaginary);
        }
        public static Complex Multiply(Complex a, Complex b)
        {
            return new Complex(a.real * b.real - a.imaginary * b.imaginary, 
                               a.real * b.imaginary + b.real * a.imaginary);
        }
        public static Complex Divide(Complex a, Complex b)
        {
            if (b.real == 0 && b.imaginary == 0)
            {
                throw new Exception();
            }
            return new Complex((a.real + b.real * a.imaginary * b.imaginary) / (b.real * b.real + b.imaginary * b.imaginary),
                               (a.real * b.imaginary - b.real * a.imaginary) / (b.real * b.real + b.imaginary * b.imaginary));
        }

       
        public static Complex operator +(Complex a, Complex b) // => Add(a,b);
        {
            return Add(a,b);
        }
        public static Complex operator -(Complex a, Complex b)
        {
            return Subtract(a, b);
        }
        public static Complex operator *(Complex a, Complex b)
        {
            return Multiply(a, b);
        }
        public static Complex operator /(Complex a, Complex b)
        {
            return Divide(a, b);
        }
        //public static Complex operator +(int a, Complex b) {ide azért le kell írni, hogy hogyan kéne értelmeznie}
        public static Complex operator -(Complex a)
        {
            return new Complex(-a.real, -a.imaginary);
        }
        public override string ToString() => $"{Math.Round(real, 4)}+{Math.Round(imaginary, 4)}i";
    }
}
