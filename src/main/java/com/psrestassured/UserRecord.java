package com.psrestassured;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRecord(

        @JsonProperty("login")
        String login,

        @JsonProperty("id")
        int id) {


        // getters, toString(), etc are generated
}
