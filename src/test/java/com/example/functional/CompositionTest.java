package com.example.functional;

import org.junit.jupiter.api.Test;

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
    void basicFunctional() {

        /*
            Function<Integer, Integer> addTwo = (Integer number) -> {
                return number + 2;
            };
        */

//      Function<Integer, Integer> addTwo = number -> addTwo(number);
//      Function<Integer, Integer> addTwo = number -> number + 2;
        Function<Integer, Integer> addTwo = this::addTwo;

        int result = addTwo.apply(1);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void funcComposition() {

        int input = 5;
        int addTwoRes = addTwo.apply(input);
        int multipleByThreeRes = multipleByThree.apply(addTwoRes);
        int result = subtractFive.apply(multipleByThreeRes);

        assertThat(result).isEqualTo(16);
    }

    @Test
    void composition() {

        Function<Integer, Integer> composedFunction = compose(addTwo, multipleByThree, subtractFive);

        int result = composedFunction.apply(5);

        assertThat(result).isEqualTo(16);
    }

    static Function<Integer, Integer> addTwo = number -> number + 2;
    static Function<Integer, Integer> multipleByThree = number -> number * 3;
    static Function<Integer, Integer> subtractFive = number -> number - 5;

    static <T> Function<T, T> compose(Function<T, T>... functions) {
        return x -> {
            T result = x;
            for (Function<T, T> func : functions) {
                result = func.apply(result);
            }
            return result;
        };
    }



    @Test
    void other() {

        // input = 5
        // f(x) add_two
        // g(x) multiple_by_three
        // h(x) subtract_five

        // (f.g.h)(x) = h(g(f(x)))
        // addTwo.multipleByThree.subtractFive(5) = subtractFive(multipleByThree(addTwo(5)))

        Function<Integer, Integer> calculateSomeThing = addTwo.andThen(multipleByThree.andThen(subtractFive));

        int result = calculateSomeThing.apply(5);

        assertThat(result).isEqualTo(16);
    }

}
