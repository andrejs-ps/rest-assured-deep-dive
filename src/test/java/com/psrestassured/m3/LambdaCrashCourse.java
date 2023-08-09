package com.psrestassured.m3;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

public class LambdaCrashCourse {

    Function<LocalDate, String> dateToString = date -> date.getDayOfMonth() + " of " + date.getMonth();

    String dateToString(LocalDate date) {
        return date.getDayOfMonth() + " of " + date.getMonth();
    }

    public static void main(String[] args) {

        Stream.of(2, 3, 4, 5)
                .filter(x -> x < 4)
                .forEach(System.out::println);  // [2, 3]

        Stream.of("Boston", "Washington")
                .filter(s -> s.equals("Boston"))
                .forEach(System.out::println);

    }
}
