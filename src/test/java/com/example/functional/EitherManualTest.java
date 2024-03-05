package com.example.functional;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EitherManualTest {

    private static EitherManual<String, Integer> multiplyBy2(Integer number) {
        if (number == 0) {
            return EitherManual.left("Multiply by zero");
        } else {
            return EitherManual.right(number * 2);
        }
    }

    private static EitherManual<String, Integer> divideBy2(Integer number) {
        if (number == 0) {
            return EitherManual.left("Divide by zero");
        } else {
            return EitherManual.right(number / 2);
        }
    }

    private static EitherManual<String, Integer> add3(Integer number) {
        return EitherManual.right(number + 3);
    }


    private static EitherManual<String, Integer> subtract2(Integer number) {
        if (number == 0) {
            return EitherManual.left("Subtract by zero");
        } else {
            return EitherManual.right(number - 2);
        }
    }

    private MessageResult calculateSomething(Integer number) {
        EitherManual<String, Integer> result = multiplyBy2(number)
                .then(EitherManualTest::subtract2)
                .then(EitherManualTest::divideBy2)
                .then(EitherManualTest::add3);

        if (result.isRight()) {
            return MessageResult.builder()
                    .status(true)
                    .value(result.getRight())
                    .build();
        }

        return MessageResult.builder()
                .status(false)
                .message(result.getLeft())
                .build();
    }

    @Test
    void calculateSuccess() {
        MessageResult messageResult = calculateSomething(10);

        log.debug("messageResult ={}", messageResult);
        assertThat(messageResult.getValue()).isEqualTo(12);
    }

    @Test
    void calculateZero() {
        MessageResult messageResult = calculateSomething(0);

        log.debug("messageResult ={}", messageResult);
        assertThat(messageResult.getMessage()).isEqualTo("Multiply by zero");
    }

    @Test
    void calculateDivideByZero() {
        MessageResult messageResult = calculateSomething(1);

        log.debug("messageResult ={}", messageResult);
        assertThat(messageResult.getMessage()).isEqualTo("Divide by zero");
    }
}
