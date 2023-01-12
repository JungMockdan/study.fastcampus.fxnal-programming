package study.fastcampus.fxnalprogramming.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdderTest {

    @Test
    public void adderTest() {
        Function<Integer,Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        assertEquals(15,result);
    }

}