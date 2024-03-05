package com.example.functional;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MonadVavrTest {

    private static Either<String, Integer> multiplyBy2(Integer number) {
        if (number == 0) {
            return Either.left("Multiply by zero");
        } else {
            return Either.right(number * 2);
        }
    }

    private static Either<String, Integer> divideBy2(Integer number) {
        if (number == 0) {
            return Either.left("Divide by zero");
        } else {
            return Either.right(number / 2);
        }
    }

    private static Either<String, Integer> subtract2(Integer number) {
        if (number == 0) {
            return Either.left("Subtract by zero");
        } else {
            return Either.right(number - 2);
        }
    }

    private static Either<String, Integer> add3(Integer number) {
        return Either.right(number + 3);
    }

    private MessageResult calculateSomething(Integer number) {
        Either<String, Integer> result = multiplyBy2(number)
                .flatMap(MonadVavrTest::subtract2)
                .flatMap(MonadVavrTest::divideBy2)
                .flatMap(MonadVavrTest::add3);

        if (result.isRight()) {
            return MessageResult.builder()
                    .status(true)
                    .value(result.get())
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
