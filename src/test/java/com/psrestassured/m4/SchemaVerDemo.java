package com.psrestassured.m4;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaVerDemo {

    static final String POST = "https://reqres.in/api/users?page=1";

    String json = """      
            {
              "page": 1,
              "per_page": 6,
              "data": [
                {
                  "id": 1,
                  "email": "george.bluth@reqres.in",
                  "first_name": "George",
                  "last_name": "Bluth",
                  "avatar": "https://reqres.in/img/faces/1-image.jpg"
                },
                {
                  "id": 2,
                  "email": "janet.weaver@reqres.in",
                  "first_name": "Janet",
                  "last_name": "Weaver",
                  "avatar": "https://reqres.in/img/faces/2-image.jpg"
                }
              ],
              "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
              }
            }
            """;

    @Test
    void basicExample() {

        RestAssured.get(POST)
                .then()
        .body(matchesJsonSchemaInClasspath(
        "schema_versions.json"));
    }
}
