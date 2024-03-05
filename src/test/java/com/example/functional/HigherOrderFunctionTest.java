package com.example.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HigherOrderFunctionTest {

    @Test
    void highOrderFunctions() {

        BiFunction<Integer, Integer, Integer> add = (Integer x, Integer y) -> x + y;
        BiFunction<Integer, Integer, Integer> multiplyBy2 = (x, y) -> 2 * add.apply(x, y);

        Integer result = multiplyBy2.apply(1, 2);

        assertThat(result).isEqualTo(6);

    }

}
