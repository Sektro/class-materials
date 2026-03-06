package famous.sequence;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class FibonacciTest
{
    @Test
    public void testOneFib()
    {
        //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610
        assertEquals(0, Fibonacci.fib(1));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        1, 0
        2, 1
        3, 1
        4, 2
        5, 3
        10, 34
    """)

    public void testFib(int index, int expected)
    {
        assertEquals(expected, Fibonacci.fib(index));
    }
}