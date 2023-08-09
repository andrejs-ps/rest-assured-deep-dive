package com.psrestassured.m5;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class GroovySimpleDemo {

    static final String USERS_EP = "https://reqres.in/api/users?page=1";

    String object = """
            '{"page":1,"per_page":6,"total":12,"total_pages":2,' +
                    '"data":[' +
                    '{"id":1,"email":"george.bluth@reqres.in","first_name":"George","last_name":"Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},' +
                    '{"id":2,"email":"janet.weaver@reqres.in","first_name":"Janet","last_name":"Weaver","avatar":"https://reqres.in/img/faces/2-image.jpg"},' +
                    '{"id":3,"email":"emma.wong@reqres.in","first_name":"Emma","last_name":"Wong","avatar":"https://reqres.in/img/faces/3-image.jpg"},' +
                    '{"id":4,"email":"eve.holt@reqres.in","first_name":"Eve","last_name":"Holt","avatar":"https://reqres.in/img/faces/4-image.jpg"},' +
                    '{"id":5,"email":"charles.morris@reqres.in","first_name":"Charles","last_name":"Morris","avatar":"https://reqres.in/img/faces/5-image.jpg"},' +
                    '{"id":6,"email":"tracey.ramos@reqres.in","first_name":"Tracey","last_name":"Ramos","avatar":"https://reqres.in/img/faces/6-image.jpg"}],' +
                    '"support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}}'
    )
    """;

    @Test
    void arrayAccessTest() {
        RestAssured.get(USERS_EP)
                .then()
                .body("data.first_name[0]", equalTo("George"))
                .body("data.first_name[-1]", equalTo("Tracey"));
    }

    @Test
    void arrayCollectInGroovy() {

        RestAssured.get(USERS_EP)
                .then()
                .body("data.size()", equalTo(2));
    }

    @Test
    void arrayCollectInJava() {

        List<String> result = RestAssured.get(USERS_EP).body().jsonPath().getList("data");
        Assert.assertEquals(result.size(), 6);
    }

    @Test
    void arrayCollectUsingComplexBodyAssertion() {
        RestAssured.get(USERS_EP)
                .then()
                .body("per_page", response -> equalTo(response.body().jsonPath().getList("data").size()));
    }
}
