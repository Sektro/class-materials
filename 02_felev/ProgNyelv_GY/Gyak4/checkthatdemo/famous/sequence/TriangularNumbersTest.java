package famous.sequence;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class TriangularNumbersTest
{
    @Test
    public void testOneTrig()
    {
        assertEquals(0, TriangularNumbers.getTriangularNumber(0));
    }

    @ParameterizedTest
    @CsvSource(textBlock="""
        1, 1
        100, 5050
        -1, 0
        """
    )

    public void testTrig(int index, int expected)
    {
        assertEquals(expected, TriangularNumbers.getTriangularNumber(index));
    }
}