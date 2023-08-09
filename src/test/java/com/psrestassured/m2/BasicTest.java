package com.psrestassured.m2;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicTest {

    @BeforeMethod
    void setup() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=1";
        RestAssured.config = RestAssuredConfig.newConfig()
                .redirect(RedirectConfig.redirectConfig().maxRedirects(2));
    }

    @Test
    void basicTest() {
        RestAssured
                .given()
                    .header("name", "value")
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .body("page", Matchers.equalTo(1))
                    .rootPath("data")
                    .body("first_name[0]", Matchers.equalTo("George"))
                    .body("first_name", Matchers.hasItems("George", "Emma"));
    }
}
