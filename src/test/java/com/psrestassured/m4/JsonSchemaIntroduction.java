package com.psrestassured.m4;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaIntroduction {

    static final String POSTS_EP = "https://jsonplaceholder.typicode.com/posts/1";

    String json = """      
            {
                "userId": 1,
                "id": 1,
                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            }
            """;

    @Test
    void basicExample() {

        RestAssured.get(POSTS_EP)
                .then()
                .body(matchesJsonSchemaInClasspath("basic_example.json"));
    }
}
