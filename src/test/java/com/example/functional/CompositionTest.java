package com.example.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositionTest {

    @Test
    void basic() {

        int input = 5;

        // 7
        int result1 = add_two(input);

        // 21
        int result2 = multiple_by_three(result1);

        // 16
        int lastResult = subtract_five(result2);

        assertThat(lastResult).isEqualTo(16);
    }

    @Test
    void basicComposition() {
        int input = 5;

        // 16
        int lastResult = subtract_five(multiple_by_three(add_two(input)));

        assertThat(lastResult).isEqualTo(16);
    }

    private int add_two(int number) {
        return number + 2;
    }

    private int multiple_by_three(int number) {
        return number * 3;
    }

    private int subtract_five(int number) {
        return number - 5;
    }

    @Test
    void basic_functional() {

        /*
            Function<Integer, Integer> addTwo = (Integer number) -> {
                return number + 2;
            };

            สมการของเรา
            f(x) = x + 2
            f(1) = 1 + 2
            f(1) = 3
        */

        Function<Integer, Integer> addTwo = number -> number + 2;

        int result = addTwo.apply(1);

        assertThat(result).isEqualTo(3);
    }

    public static Function<Integer, Integer> addTwo(Integer number) {
        return x -> number + 2;
    }

    @Test
    void functionalComposition() {

        // input = 5
        // f(x) add_two
        // g(x) multiple_by_three
        // h(x) subtract_five

        // (f.g.h)(x) = h(g(f(x)))
        // addTwo.multipleByThree.subtractFive(5) = subtractFive(multipleByThree(addTwo(5)))

        Function<Integer, Integer> addTwo = number -> number + 2;
        Function<Integer, Integer> multipleByThree = number -> number * 3;
        Function<Integer, Integer> subtractFive = number -> number - 5;

        Function<Integer, Integer> calculateSomeThing = addTwo.andThen(multipleByThree.andThen(subtractFive));

        int result = calculateSomeThing.apply(5);

        assertThat(result).isEqualTo(16);
    }
}
