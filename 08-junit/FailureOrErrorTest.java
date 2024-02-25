package put.io.testing.junit;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {
    @Test
    public void test1() { // Failure
        fail();
    }

    @Test
    public void test2() { // Error
        int m[] = {1, 2, 3, 4};
        System.out.println(m[5]);
    }

    @Test
    public void test3() {
        try {
            fail();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
}