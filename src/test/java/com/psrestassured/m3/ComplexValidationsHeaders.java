package com.psrestassured.m3;

import io.restassured.RestAssured;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ComplexValidationsHeaders {

    static final String URL = "https://api.github.com/rate_limit";

    @Test
    void complexHamcrestMatchersHeaders_1() {
        RestAssured.get(URL)
                .then()
                .header("x-ratelimit-limit",  equalTo("60"))
                .header("x-ratelimit-limit",  s -> Integer.parseInt(s), equalTo(60))
                .header("x-ratelimit-limit", Integer::parseInt, equalTo(60));
    }

    @Test
    void complexHamcrestMatchersHeaders_2() {
        RestAssured.get(URL)
                .then()
                .header("date",
                        date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()));
    }

    @Test
    void complexHamcrestMatchersHeaders_3() {
        RestAssured.get(URL)
                .then()
                .header("x-ratelimit-limit",
                        response -> greaterThan(response.header("x-ratelimit-remaining")));
    }
}
