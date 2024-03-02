package com.example.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CurringAndPartialApplyTest {

    // Currying - เป็นเทคนิคการแปลงฟังก์ชันที่มีอาร์กิวเมนต์หลายตัว เป็นฟังก์ชันที่มีอาร์กิวเมนต์ตัวเดียว

    /* Partial application
       - เป็นเทคนิคการสร้างฟังก์ชันใหม่จากฟังก์ชันที่มีอยู่ โดย “แก้ไขอาร์กิวเมนต์บางส่วน” ของฟังก์ชันดั้งเดิม
       - ฟังก์ชันใหม่จะมีอาร์กิวเมนต์น้อยลง
    */

    @Test
    void name() {

        // f(x,y) = f(x)(y)
        Function<Integer, Integer> plusTwo = plus(2);

        int result = plusTwo.apply(3);

        assertThat(result).isEqualTo(5);
    }

    public static Function<Integer, Integer> plus(Integer number) {
        return x -> number + x;
    }
}
