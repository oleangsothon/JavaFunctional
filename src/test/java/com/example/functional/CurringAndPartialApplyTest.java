package com.example.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CurringAndPartialApplyTest {

    // Currying - เป็นเทคนิคการแปลง function ที่มี argument หลายตัว เป็นฟังก์ชันที่มีอาร์กิวเมนต์ตัวเดียว

    /* Partial application
       - เป็นเทคนิคการสร้างฟังก์ชันใหม่จาก function ที่มีอยู่ โดย “แก้ไข argument บางส่วน” ของ function ดั้งเดิม
       - function ใหม่จะมี argument น้อยลง
    */

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    @Test
    void curryingTest() {

        Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedAdd = curryAdd();

//        Function<Integer, Function<Integer, Integer>> add5 = curriedAdd.apply(5);
//        Function<Integer, Integer> add3 = add5.apply(3);
//        int resultAdd2 = add3.apply(2);

        int curriedResult = curriedAdd.apply(5).apply(3).apply(2);

        assertThat(curriedResult).isEqualTo(10);
    }

    public static Function<Integer, Function<Integer, Function<Integer, Integer>>> curryAdd() {
        return a -> curryAdd2((a));
    }

    private static Function<Integer, Function<Integer, Integer>> curryAdd2(Integer a) {
        return b -> curryAdd3(a, b);
    }

    private static Function<Integer, Integer> curryAdd3(Integer a, Integer b) {
        return c -> add(a, b, c);
    }

//    static Function<Integer, Function<Integer, Function<Integer, Integer>>> curryAdd() {
//        return a -> b -> c -> add(a, b, c);
//    }

    @Test
    void partialTest() {
        BiFunction<Integer, Integer, Integer> partiallyApplied = partialApply(5);

        int partiallyResult = partiallyApplied.apply(3, 2);

        assertThat(partiallyResult).isEqualTo(10);


        BiFunction<Integer, Integer, Integer> partiallyApplied2 = partialApply(10);

        int partiallyResult2 = partiallyApplied2.apply(3, 2);

        assertThat(partiallyResult2).isEqualTo(15);
    }

    static BiFunction<Integer, Integer, Integer> partialApply(Integer a) {
        return (Integer b, Integer c) -> add(a, b, c);
    }
}
