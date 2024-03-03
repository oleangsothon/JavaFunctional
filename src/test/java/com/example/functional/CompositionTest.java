package com.example.functional;

import org.assertj.core.util.TriFunction;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositionTest {

    @Test
    void basic() {

        int input = 5;

        // 7
        int result1 = addTwo(input);

        // 21
        int result2 = multipleByThree(result1);

        // 16
        int lastResult = subtractFive(result2);

        assertThat(lastResult).isEqualTo(16);
    }

    @Test
    void basicComposition() {
        int input = 5;

        // 16
        int lastResult = subtractFive(multipleByThree(addTwo(input)));

        assertThat(lastResult).isEqualTo(16);
    }

    private int addTwo(int number) {
        return number + 2;
    }

    private int multipleByThree(int number) {
        return number * 3;
    }

    private int subtractFive(int number) {
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

//      Function<Integer, Integer> addTwo = number -> addTwo(number);
//      Function<Integer, Integer> addTwo = number -> number + 2;
        Function<Integer, Integer> addTwo = this::addTwo;

        int result = addTwo.apply(1);

        assertThat(result).isEqualTo(3);
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
