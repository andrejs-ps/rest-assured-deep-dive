package com.psrestassured.m2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredDemo {

    @Test
    void comparison() {

        // non-fluent way
        String value = RestAssured.get("url").body().jsonPath().get("property");
        Assert.assertEquals(value, "abc");

        // vs. using the Fluent Interface
        RestAssured.get("url")
                .then()
                .assertThat() // optional
                .body("property", Matchers.equalTo("abc"));
    }



    @Test
    void syntacticSugar() {

        RestAssured
                .given()
                .header("", "")
                .when() // syntactic sugar
                    .get("")
                .then()
                    .assertThat()   // syntactic sugar
                        .statusCode(201)
                    .and()          // syntactic sugar
                        .body("", Matchers.equalTo(2));
    }
}
