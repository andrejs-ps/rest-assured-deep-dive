package com.psrestassured.m7;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class IntroductionReminder {

    static final String REPOS = "https://api.github.com/user/repos";
    static final String TOKEN = "your token here";

    @Test
    void postTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme\"}")
                .when()
                    .post(REPOS)
                .then()
                .statusCode(201);
    }

    @Test
    void deleteTest() {
        RestAssured
                .given()
                .auth().oauth2(TOKEN)
                .when()
                    .delete("https://api.github.com/repos/andrejs-ps/deleteme")
                .then()
                    .statusCode(204);
    }
}
