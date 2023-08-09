package com.psrestassured.m7;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static com.psrestassured.m7.ReusableAuth.TOKEN;

public class PreemptiveDemo {

    static final String REPOS_EP = "https://api.github.com/user/repos";

    @Test
    void testPreemptive() {
        RestAssured
                .given()
                .auth()
                .preemptive()       // redundant
                .oauth2(TOKEN)
                .get(REPOS_EP)
                .then()
                .statusCode(200);
    }

    @Test
    void basicPreemptive() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }

    @Test
    void basicChallenged() {

        RestAssured
                .given()
                .auth()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void digest() {

        RestAssured
                .given()
                .auth()
                .digest("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }
}
