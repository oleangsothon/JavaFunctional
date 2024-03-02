package com.example.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class HigherOrderFunctionTest {

    @Test
    void highOrderFunctions() {

        /* สมการของเรา
            f(x)(y) = f(g(x,y))
        */
        Function<Integer, Function<Integer, Integer>> addFunction = createOperation(HigherOrderFunctionTest::add);
        Function<Integer, Function<Integer, Integer>> multiplyFunction = createOperation(HigherOrderFunctionTest::multiply);


        // Apply functions with arguments (composition)
        int resultAddition = addFunction.apply(3).apply(4);
        int resultMultiplication = multiplyFunction.apply(3).apply(4);

        assertThat(resultAddition).isEqualTo(7);
        assertThat(resultMultiplication).isEqualTo(12);
    }


    public static Function<Integer, Function<Integer, Integer>> createOperation(BiFunction<Integer, Integer, Integer> operation) {
        return x -> y -> operation.apply(x, y);
    }


    public static Integer add(Integer x, Integer y) {
        return x + y;
    }

    public static Integer multiply(Integer x, Integer y) {
        return x * y;
    }
}
