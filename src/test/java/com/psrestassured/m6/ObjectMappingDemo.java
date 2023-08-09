package com.psrestassured.m6;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psrestassured.AnotherUser;
import com.psrestassured.User;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ObjectMappingDemo {

    static final String URL = "https://api.github.com/users/rest-assured";

    @Test
    void objectMappingTestOne() {
        User user = RestAssured.get(URL).as(User.class);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getPublicRepos(),2);
    }


    @Test
    void objectMappingRelyingOnMapperType() {

        User user = RestAssured.get(URL).as(User.class, ObjectMapperType.GSON);
        assertEquals(user.getLogin(), "rest-assured");
    }





    @Test
    void objectMappingUsingSpecifiedMapper() {

//        ObjectMapper mapper = new ObjectMapper();

        io.restassured.mapper.ObjectMapper mapper = getObjectMapper();

        AnotherUser user = RestAssured.get(URL)
                .as(AnotherUser.class, mapper);

        assertEquals(user.login, "rest-assured");
    }

    public static io.restassured.mapper.ObjectMapper getObjectMapper() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }
}
