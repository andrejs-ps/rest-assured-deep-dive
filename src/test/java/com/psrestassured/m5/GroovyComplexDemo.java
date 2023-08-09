package com.psrestassured.m5;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class GroovyComplexDemo {

    static final String REPOS_EP = "https://api.github.com/search/repositories?q=java";

    @Test(description = "this test is meant to fail")
    void allResultsMustBeJava_DoneInGroovy() {
        RestAssured.get(REPOS_EP)
                .then()
                .body("items.findAll{ it.language != 'Java' }.size()", equalTo(0));
    }

    @Test(description = "this test is meant to fail")
    void allResultsMustBeJava_doneInJava() {

        List<String> list = RestAssured.get(REPOS_EP).jsonPath().getList("items.language");

        var filtered = list.stream()
                .filter(language -> !"Java".equalsIgnoreCase(language))
                .toList();

        Assert.assertEquals(filtered.size(), 0);

    }
}
