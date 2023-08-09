package com.psrestassured.m6;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import com.psrestassured.UserRecord;

import static org.testng.Assert.assertEquals;

public class ObjectMappingToRecords {

    static final String USER = "https://api.github.com/users/rest-assured";

    @Test
    void objectMappingTestOne() {

        UserRecord user = RestAssured.get(USER)
                .as(UserRecord.class);

        assertEquals(user.login(), "rest-assured");
        assertEquals(user.id(), 19369327);
    }
}
