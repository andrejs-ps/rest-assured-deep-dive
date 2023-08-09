package com.psrestassured.m3;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ComplexValidationsBody {

    static final String URL = "https://api.github.com/";

    @Test
    void complexBodyExample() {
        RestAssured.get(URL + "users/andrejs-ps")
                .then()
                .body("url", response -> Matchers.endsWith("andrejs-ps"));
    }

    @DataProvider
    public Object[][] names() {
        return new Object[][]{
                {"andrejs-ps"},
                {"rest-assured"}
        };
    }

    @Test(dataProvider = "names")
    void complexBodyExampleWithDp(String name) {

        RestAssured.get(URL + "users/" + name)
                .then()
                .body("url", response -> Matchers.endsWith(response.body().jsonPath().get("login")));
    }
}
