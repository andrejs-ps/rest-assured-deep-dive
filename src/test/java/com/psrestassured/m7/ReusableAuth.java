package com.psrestassured.m7;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReusableAuth {

    static final String REPOS_EP = "https://api.github.com/user/repos"; // requires Auth
    static final String TOKEN = "your token here";

    // option 1
    static RequestSpecification spec = new RequestSpecBuilder()
            .setAuth(RestAssured.oauth2(TOKEN)).build();

    @BeforeMethod
    void setup() {
        // option 2
        RestAssured.authentication = RestAssured.oauth2(TOKEN);
    }

    @Test
    void authTest() {
        RestAssured.given()
                .spec(spec)
                .get(REPOS_EP)
                .then()
                .statusCode(200);

    }
}
