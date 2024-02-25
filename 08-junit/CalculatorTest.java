package put.io.testing.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() throws Exception {
        this.calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        int a = this.calculator.add(2, 3);
        assertEquals(5, a);

        int b = this.calculator.add(5, 4);
        assertEquals(9, b);
    }

    @Test
    public void testMultiply() {
        int m = this.calculator.multiply(2,3);
        assertEquals(6, m);

        int n = this.calculator.multiply(5, 4);
        assertEquals(20, n);
    }

    @Test
    public void testAddPositiveNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            int a = this.calculator.addPositiveNumbers(-1, 2);
            assertEquals(1, a);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.calculator = null;
    }
}