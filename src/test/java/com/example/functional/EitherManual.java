package com.example.functional;

import java.util.function.Function;

public class EitherManual<Left, Right> {
    private final Left leftValue;
    private final Right rightValue;

    private EitherManual(Left left, Right right) {
        this.leftValue = left;
        this.rightValue = right;
    }

    public static <Left, Right> EitherManual<Left, Right> left(Left left) {
        return new EitherManual<>(left, null);
    }

    public static <Left, Right> EitherManual<Left, Right> right(Right right) {
        return new EitherManual<>(null, right);
    }

    public boolean isLeft() {
        return leftValue != null;
    }

    public boolean isRight() {
        return rightValue != null;
    }

    public Left getLeft() {
        if (isLeft()) {
            return leftValue;
        } else {
            throw new IllegalStateException("Not a Left value");
        }
    }

    public Right getRight() {
        if (isRight()) {
            return rightValue;
        } else {
            throw new IllegalStateException("Not a Right value");
        }
    }

    public <NextRight> EitherManual<Left, NextRight> then(Function<Right, EitherManual<Left, NextRight>> f) {
        if (isLeft()) {
            return EitherManual.left(leftValue);
        } else {
            return f.apply(rightValue);
        }
    }
}

