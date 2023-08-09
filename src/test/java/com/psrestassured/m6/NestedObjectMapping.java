package com.psrestassured.m6;

import com.psrestassured.RateLimit;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedObjectMapping {

    static final String URL = "https://api.github.com/rate_limit";

    @Test
    void nestedObjectMapping() {

        RateLimit limit = RestAssured.get(URL).as(RateLimit.class);
        Assert.assertEquals(limit.getCoreLimit(), 60);
        Assert.assertEquals(limit.getSearchLimit(), 10);
    }
}
